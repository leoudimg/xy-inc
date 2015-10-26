package br.com.xy_inc.controller;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.xy_inc.model.Propriedade;
import br.com.xy_inc.repository.PropriedadeRepository;

/**
 * Controller referente a Entidade {@link Propriedade} que e a interface de
 * comunicacao entre a View ou Servico com a camada DAO correspondente.
 * 
 * @author Leonardo
 * 
 * @since 23/10/2015
 * 
 */
@Controller
public class PropriedadeController implements GenericController<Propriedade> {

	/**
	 * DAO referente a entidade {@link Propriedade}
	 */
	@Inject
	private PropriedadeRepository repository;

	// /**
	// * Controle de atributos
	// */
	// @Inject
	// private Result result;

	/**
	 * Busca todos os propriedades.
	 */
	@Get("/propriedade")
	public List<Propriedade> listarRest() {
		return this.listar();
	}

	/**
	 * Insere um {@link Propriedade} na base de dados.
	 */
	@Transactional
	@Post("/propriedade")
	public void inserirRest(Propriedade propriedade) {
		this.inserir(propriedade);

	}

	/**
	 * Atualiza um propriedade.
	 */
	@Transactional
	@Put("/propriedade/{propriedade.id}")
	public void atualizarRest(Propriedade propriedade) {
		this.atualizar(propriedade);

	}

	/**
	 * Exclui um {@link Propriedade}.
	 */
	@Transactional
	@Delete("/propriedade/{propriedade.id}")
	public void excluirRest(Propriedade propriedade) {
		this.excluir(propriedade);

	}

	/**
	 * Busca um {@link Propriedade} por id.
	 */
	@Get("/propriedade/{id}")
	public Propriedade editarRest(Long id) {

		return this.editar(id);
	}

	/**
	 * Redireciona para a pagina de inclusao de {@link Propriedade} requerido
	 * pela aplicacao web.
	 * 
	 */
	public void novo() {
	}

	/**
	 * Redireciona o usuario para a tela de edicao de {@link Propriedade}
	 * 
	 * @param id
	 *            Identificador da {@link Propriedade} a ser editado.
	 * @return - retorna o {@link Propriedade} a ser editado.
	 */
	public Propriedade editar(Long id) {

		return repository.buscarPorId(id);

	}

	@Get("/propriedade/editar/{propriedade.id}")
	public Propriedade editar(Propriedade propriedade) {

		return repository.buscarPorId(propriedade.getId());

	}

	/**
	 * Atualiza um propriedade pois formulario HTML nao aceita PUT requerido
	 * pela aplicacao web.
	 * 
	 * @param propriedade
	 *            - {@link Propriedade} a ser atualizado.
	 */
	@Transactional
	public void atualizar(Propriedade propriedade) {
		repository.atualizar(propriedade);
	}

	/**
	 * Exclui um propriedade da base de dados.
	 * 
	 * @param propriedade
	 *            - {@link Propriedade} a ser excluido.
	 */
	@Transactional
	@Get("/propriedade/excluir/{propriedade.id}")
	public void excluir(Propriedade propriedade) {
		repository.excluir(propriedade.getId());
	}

	/**
	 * Lista todos propriedades gravados na base de dados.
	 * 
	 */
	public List<Propriedade> listar() {
		return repository.buscarTodos();
	}

	/**
	 * Insere o {@link Propriedade} na base de dados.
	 * 
	 * @param propriedade
	 *            - {@link Propriedade} a ser inserido na base de dados.
	 */
	public void inserir(Propriedade propriedade) {
		repository.inserir(propriedade);
	}

}
