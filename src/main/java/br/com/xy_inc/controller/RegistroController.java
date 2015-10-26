package br.com.xy_inc.controller;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.xy_inc.model.Atributo;
import br.com.xy_inc.model.Modelo;
import br.com.xy_inc.model.Propriedade;
import br.com.xy_inc.model.Registro;
import br.com.xy_inc.repository.RegistroRepository;

/**
 * Controller referente a Entidade {@link Registro} que e a interface de
 * comunicacao entre a View ou Servico com a camada DAO correspondente.
 * 
 * @author Leonardo
 * 
 * @since 23/10/2015
 * 
 */
@Controller
public class RegistroController implements GenericController<Registro> {

	/**
	 * DAO referente a entidade {@link Registro}
	 */
	@Inject
	private RegistroRepository repository;

	/**
	 * Controller referente ao {@link Modelo}
	 */
	@Inject
	private ModeloController modeloController;

	/**
	 * Controller referente ao {@link Atributo}
	 */
	@Inject
	private AtributoController atributoController;

	/**
	 * Controller referente ao {@link Propriedade}
	 */
	@Inject
	private PropriedadeController propriedadeController;

	@Inject
	private Logger log;

	// /**
	// * Controle de atributos
	// */
	// @Inject
	// private Result result;

	/**
	 * Busca todos os registros.
	 */
	@Get("/registro")
	public List<Registro> listarRest() {
		return this.listar();
	}

	/**
	 * Insere um {@link Registro} na base de dados.
	 */
	@Transactional
	@Post("/registro")
	public void inserirRest(Registro registro) {
		this.inserir(registro);

	}

	/**
	 * Atualiza um registro.
	 */
	@Transactional
	@Put("/registro/{registro.id}")
	public void atualizarRest(Registro registro) {
		this.atualizar(registro);

	}

	/**
	 * Exclui um {@link Registro}.
	 */
	@Transactional
	@Delete("/registro/{registro.id}")
	public void excluirRest(Registro registro) {
		this.excluir(registro);

	}

	/**
	 * Busca um {@link Registro} por id.
	 */
	@Get("/registro/{id}")
	public Registro editarRest(Long id) {

		return this.editar(id);
	}

	/**
	 * Redireciona para a pagina de inclusao de {@link Registro} requerido pela
	 * aplicacao web.
	 * 
	 */
	public void novo() {
	}

	/**
	 * Redireciona o usuario para a tela de edicao de {@link Registro}
	 * 
	 * @param registro
	 *            - {@link Registro} a ser editado.
	 * @return - retorna o {@link Registro} a ser editado.
	 */
	public Registro editar(Long id) {

		return repository.buscarPorId(id);

	}

	@Get("/registro/editar/{registro.id}")
	public Registro editar(Registro registro) {

		return repository.buscarPorId(registro.getId());

	}

	/**
	 * Atualiza um registro pois formulario HTML nao aceita PUT requerido pela
	 * aplicacao web.
	 * 
	 * @param registro
	 *            - {@link Registro} a ser atualizado.
	 */
	@Transactional
	public void atualizar(Registro registro) {
		repository.atualizar(registro);
	}

	/**
	 * Exclui um registro da base de dados.
	 * 
	 * @param registro
	 *            - {@link Registro} a ser excluido.
	 */
	@Transactional
	@Get("/registro/excluir/{registro.id}")
	public void excluir(Registro registro) {
		repository.excluir(registro.getId());
	}

	/**
	 * Lista todos registros gravados na base de dados.
	 * 
	 */
	public List<Registro> listar() {
		return repository.buscarTodos();
	}

	/**
	 * Insere o {@link Registro} na base de dados.
	 * 
	 * @param registro
	 *            - {@link Registro} a ser inserido na base de dados.
	 */
	public void inserir(Registro registro) {
		repository.inserir(registro);
	}

	/**
	 * Insere um {@link Registro} para o link enviado.
	 * 
	 * @param link
	 *            - link enviado pelo usuario.
	 * 
	 * @param listaPropriedades
	 *            - lista de {@link Propriedade} a serem inseridas.
	 * 
	 * @return Retorna o {@link Registro} inserido na base, para que o usuario
	 *         possa obter os ids para futuras acoes, retorna null caso ocorra
	 *         algum erro de validacao ou persistencia.
	 */
	@Transactional
	public Registro inserirRegistro(String link,
			List<Propriedade> listaPropriedades) {

		// Validar dados de entrada.
		if (StringUtils.isNotBlank(link)
				&& CollectionUtils.isNotEmpty(listaPropriedades)) {

			Modelo modelo = modeloController.verificarModelo(link);

			if (modelo != null) {

				List<Atributo> listaAtributos = atributoController
						.listarTodosPorModelo(modelo);

				if (CollectionUtils.isNotEmpty(listaAtributos)) {

					if (!validarAtributos(listaPropriedades, listaAtributos)) {
						return null;
					}
				}

				// Apos realizar a verificacao de integridade serao inseridos os
				// registros

				Registro registro = new Registro();

				registro.setModelo(modelo);

				repository.inserir(registro);

				// TODO Refatorar para lancar excecao na validacao, assim a
				// lista
				// sera percorrida apenas uma vez realizando as insercoes e caso
				// nao exista um atributo referente a propriedade sera lancada
				// excecao e o container realizara rollback da transacao.
				for (Propriedade propriedade : listaPropriedades) {

					propriedade.setRegistro(registro);
					propriedadeController.inserir(propriedade);

				}

				registro.setPropriedades(listaPropriedades);

				return registro;
			}
		}

		return null;
	}

	/**
	 * Retorna todos os registros de um {@link Modelo} com base no link da URL.
	 * 
	 * @param link
	 *            - link extraido da url de requisicao.
	 * @return Retorna todos registros de um {@link Modelo} com base no link da
	 *         URL.
	 */
	public List<Registro> listarTodosRegistros(String link) {

		// Validar dados de entrada.
		if (StringUtils.isNotBlank(link)) {

			Modelo modelo = modeloController.verificarModelo(link);

			if (modelo != null) {
				return repository.listarTodosPorModelo(modelo);
			}
		}

		return null;
	}

	/**
	 * Apaga um {@link Registro} dado o id do mesmo.
	 * 
	 * @param id
	 *            - id enviado pelo usuario.
	 * 
	 * @return Retorna true caso o registro seja inserido na base de dados e
	 *         false caso contrario.
	 */
	@Transactional
	public boolean apagarRegistro(String id, String link) {

		if (StringUtils.isNotBlank(id) && StringUtils.isNotBlank(link)) {

			Modelo modelo = modeloController.verificarModelo(link);

			if (modelo != null) {
				try {

					Long identificador = Long.valueOf(id);

					Registro registro = repository.buscarPorId(identificador);

					if (registro != null
							&& CollectionUtils.isNotEmpty(registro
									.getPropriedades())) {

						// TODO refatorar mapeamento para usar cascade
						for (Propriedade p : registro.getPropriedades()) {

							propriedadeController.excluir(p);
						}

						excluir(registro);

						return true;
					}
				} catch (NumberFormatException ex) {
					log.warn("apagarRegistro - Ocorreu um erro ao converter o id "
							+ id + " informado pelo usuario.");

					return false;
				}
			}
		}

		return false;

	}

	/**
	 * Atualiza um {@link Registro} e suas propriedades.
	 * 
	 * @param link
	 *            - link enviado pelo usuario.
	 * 
	 * @param registro
	 *            - {@link Registro} a ser atualizado.
	 * 
	 * @return Retorna o {@link Registro} atualizado na base, para que o usuario
	 *         possa obter os ids para futuras acoes, retorna null caso ocorra
	 *         algum erro de validacao ou persistencia.
	 */
	@Transactional
	public Registro atualizarRegistro(String link, Registro registro) {

		if (StringUtils.isNotBlank(link) && registro != null) {

			Modelo modelo = modeloController.verificarModelo(link);

			if (modelo != null) {

				List<Atributo> listaAtributos = atributoController
						.listarTodosPorModelo(modelo);

				if (CollectionUtils.isNotEmpty(listaAtributos)) {

					if (!validarAtributos(registro.getPropriedades(),
							listaAtributos)) {
						return null;
					}

					// TODO refatorar mapeamento para usar cascade
					for (Propriedade p : registro.getPropriedades()) {

						propriedadeController.atualizar(p);
					}

					atualizar(registro);

				}
			}

		}

		return null;
	}

	/**
	 * Verifica se as propriedades condizem com os atributos do Modelo, caso nao
	 * exista atributo cadastrado para o registro informado nao sera possivel
	 * inserir o Registro.
	 * 
	 * @param listaPropriedades
	 *            Lista de Propriedades a serem inseridas ou atualizadas.
	 * 
	 * @param listaAtributos
	 *            lista de atributos a serem comparados.
	 * 
	 * @return true quando nao existe problema de integridade false caso
	 *         contrario
	 */
	private boolean validarAtributos(List<Propriedade> listaPropriedades,
			List<Atributo> listaAtributos) {
		for (Propriedade propriedade : listaPropriedades) {

			boolean isAtributo = false;

			for (Atributo atributo : listaAtributos) {

				if (propriedade.getAtributo() != null
						&& propriedade.getAtributo().equals(atributo)) {

					isAtributo = true;
					break;
				}
			}

			// Se nao for atributo aborta a insercao
			if (!isAtributo) {
				return false;
			}

		}

		return true;
	}

	/**
	 * Retorna todos os registros de um {@link Modelo} com base no link da URL.
	 * 
	 * @param link
	 *            link extraido da url de requisicao.
	 * @param id
	 *            do {@link Registro} a ser pesquisado.
	 * @return Retorna o {@link Modelo} com base no link da URL e no id
	 *         fornecido.
	 */
	public Registro listarRegistro(String link, String id) {

		// Validar dados de entrada.
		if (StringUtils.isNotBlank(link) && StringUtils.isNotBlank(id)) {

			Modelo modelo = modeloController.verificarModelo(link);

			if (modelo != null) {

				try {

					Long identificador = Long.valueOf(id);

					return repository.buscarPorId(identificador);

				} catch (NumberFormatException ex) {
					log.warn("listarRegistro - Ocorreu um erro ao converter o id "
							+ id + " informado pelo usuario.");

					return null;
				}

			}
		}

		return null;
	}
}
