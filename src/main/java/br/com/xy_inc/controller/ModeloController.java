package br.com.xy_inc.controller;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.xy_inc.model.Modelo;
import br.com.xy_inc.repository.ModeloRepository;

/**
 * Controller referente a Entidade {@link Modelo} que e a interface de
 * comunicacao entre a View ou Servico com a camada DAO correspondente.
 * 
 * @author Leonardo
 * 
 * @since 23/10/2015
 * 
 */
@Controller
public class ModeloController implements GenericController<Modelo> {

	/**
	 * DAO referente a entidade {@link Modelo}
	 */
	@Inject
	private ModeloRepository repository;

	/**
	 * Controle de atributos
	 */
	@Inject
	private Result result;

	/**
	 * Busca todos os modelos.
	 */
	@Get("/modelo")
	public List<Modelo> listarRest() {
		return this.listar();
	}

	/**
	 * Insere um {@link Modelo} na base de dados.
	 */
	@Transactional
	@Post("/modelo")
	public void inserirRest(Modelo modelo) {
		this.inserir(modelo);

	}

	/**
	 * Atualiza um modelo.
	 */
	@Transactional
	@Put("/modelo/{modelo.id}")
	public void atualizarRest(Modelo modelo) {
		this.atualizar(modelo);

	}

	/**
	 * Exclui um {@link Modelo}.
	 */
	@Transactional
	@Delete("/modelo/{modelo.id}")
	public void excluirRest(Modelo modelo) {
		this.excluir(modelo);

	}

	/**
	 * Busca um {@link Modelo} por id.
	 */
	@Get("/modelo/{id}")
	public Modelo editarRest(Long id) {

		return this.editar(id);
	}

	/**
	 * Redireciona para a pagina de inclusao de {@link Modelo} requerido pela
	 * aplicacao web.
	 * 
	 */
	public void novo() {
	}

	/**
	 * Redireciona o usuario para a tela de edicao de {@link Modelo}
	 * 
	 * @param id
	 *            Identificador do {@link Modelo} a ser editado.
	 * @return - retorna o {@link Modelo} a ser editado.
	 */
	public Modelo editar(Long id) {

		return repository.buscarPorId(id);

	}

	/**
	 * Servico REST que busca o {@link Modelo} a ser editado.
	 * 
	 * @param modelo
	 *            {@link Modelo} a ser pesquisado
	 * @return {@link Modelo} a ser editado.
	 */
	@Get("/modelo/editar/{modelo.id}")
	public Modelo editar(Modelo modelo) {

		return repository.buscarPorId(modelo.getId());

	}

	/**
	 * Atualiza um modelo pois formulario HTML nao aceita PUT requerido pela
	 * aplicacao web.
	 * 
	 * @param modelo
	 *            - {@link Modelo} a ser atualizado.
	 */
	@Transactional
	public void atualizar(Modelo modelo) {

		// Nao deve existir mais de um modelo com o mesmo link.
		Modelo m = repository.verificarModelo(modelo.getLink());

		if (m != null) {

			result.forwardTo(this).erro();
		} else {
			repository.atualizar(modelo);
		}
	}

	/**
	 * Exclui um modelo da base de dados.
	 * 
	 * @param modelo
	 *            - {@link Modelo} a ser excluido.
	 */
	@Transactional
	@Get("/modelo/excluir/{modelo.id}")
	public void excluir(Modelo modelo) {
		repository.excluir(modelo.getId());
	}

	/**
	 * Lista todos modelos gravados na base de dados.
	 * 
	 */
	public List<Modelo> listar() {
		return repository.buscarTodos();
	}

	/**
	 * Insere o {@link Modelo} na base de dados.
	 * 
	 * @param modelo
	 *            - {@link Modelo} a ser inserido na base de dados.
	 */
	@Transactional
	public void inserir(Modelo modelo) {
		// Nao deve existir mais de um modelo com o mesmo link.
		Modelo m = repository.verificarModelo(modelo.getLink());

		if (m != null) {

			result.forwardTo(this).erro();
		} else {
			repository.inserir(modelo);
		}
	}

	/**
	 * Verificar se existe um {@link Modelo} para o link enviado.
	 * 
	 * @param link
	 *            - link enviado pelo usuario.
	 * 
	 * @return Retorna o {@link Modelo} correspondente ao link passado por
	 *         parametro
	 */
	public Modelo verificarModelo(String link) {

		return repository.verificarModelo(link);

	}

	/**
	 * Redireciona para a pagina de erro.
	 */
	public void erro() {

	}

}
