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
import br.com.xy_inc.model.Atributo;
import br.com.xy_inc.model.Modelo;
import br.com.xy_inc.repository.AtributoRepository;

/**
 * Controller referente a Entidade {@link Atributo} que e a interface de
 * comunicacao entre a View ou Servico com a camada DAO correspondente.
 * 
 * @author Leonardo
 * 
 * @since 23/10/2015
 * 
 */
@Controller
public class AtributoController implements GenericController<Atributo> {

	/**
	 * DAO referente a entidade {@link Atributo}
	 */
	@Inject
	private AtributoRepository repository;

	/**
	 * Controller referente a entidade {@link Modelo}
	 */
	@Inject
	private ModeloController modeloController;

	/**
	 * Controle de atributos
	 */
	@Inject
	private Result result;

	/**
	 * Busca todos os atributos.
	 */
	@Get("/atributo")
	public List<Atributo> listarRest() {
		return this.listar();
	}

	/**
	 * Insere um {@link Atributo} na base de dados.
	 */
	@Transactional
	@Post("/atributo")
	public void inserirRest(Atributo atributo) {
		this.inserir(atributo);

	}

	/**
	 * Atualiza um atributo.
	 */
	@Transactional
	@Put("/atributo/{atributo.id}")
	public void atualizarRest(Atributo atributo) {
		this.atualizar(atributo);

	}

	/**
	 * Exclui um {@link Atributo}.
	 */
	@Transactional
	@Delete("/atributo/{atributo.id}")
	public void excluirRest(Atributo atributo) {
		this.excluir(atributo);

	}

	/**
	 * Busca um {@link Atributo} por id.
	 */
	@Transactional
	@Get("/atributo/{id}")
	public Atributo editarRest(Long id) {

		return this.editar(id);
	}

	/**
	 * Redireciona para a pagina de inclusao de {@link Atributo} requerido pela
	 * aplicacao web.
	 * 
	 */
	public List<Modelo> novo() {
		return modeloController.listar();
	}

	/**
	 * Redireciona o usuario para a tela de edicao de {@link Atributo}
	 * 
	 * @param id
	 *            identificador do Atributo.
	 * @return - retorna o {@link Atributo} a ser editado.
	 */
	public Atributo editar(Long id) {

		return repository.buscarPorId(id);

	}

	/**
	 * Servico REST que busca o {@link Atributo} a ser editado.
	 * 
	 * @param atributo
	 *            {@link Atributo} a ser pesquisado
	 * @return {@link Atributo} a ser editado.
	 */
	@Get("/atributo/editar/{atributo.id}")
	public Atributo editar(Atributo atributo) {

		result.include("modeloList", modeloController.listar());

		return repository.buscarPorId(atributo.getId());

	}

	/**
	 * Atualiza um atributo pois formulario HTML nao aceita PUT requerido pela
	 * aplicacao web.
	 * 
	 * @param atributo
	 *            - {@link Atributo} a ser atualizado.
	 */
	@Transactional
	public void atualizar(Atributo atributo) {
		repository.atualizar(atributo);
	}

	/**
	 * Exclui um atributo da base de dados.
	 * 
	 * @param atributo
	 *            - {@link Atributo} a ser excluido.
	 */
	@Transactional
	@Get("/atributo/excluir/{atributo.id}")
	public void excluir(Atributo atributo) {
		repository.excluir(atributo.getId());
	}

	/**
	 * Lista todos atributos gravados na base de dados.
	 * 
	 */
	public List<Atributo> listar() {
		return repository.buscarTodos();
	}

	/**
	 * Insere o {@link Atributo} na base de dados.
	 * 
	 * @param atributo
	 *            - {@link Atributo} a ser inserido na base de dados.
	 */
	@Transactional
	public void inserir(Atributo atributo) {
		repository.inserir(atributo);
	}

	/**
	 * Lista todos os atributos de um {@link Modelo}.
	 * 
	 * @param modelo
	 *            - {@link Modelo} filtro para os atributos.
	 * @return Retorna lista de {@link Atributo} que contem o {@link Modelo}
	 *         especificado.
	 */
	public List<Atributo> listarTodosPorModelo(Modelo modelo) {
		return repository.listarTodosPorModelo(modelo);
	}

//	/**
//	 * Define o repositorio do Controller metodo criado para auxiliar nos testes unitarios.
//	 * @param repository
//	 */
//	public void setRepository(AtributoRepository repository) {
//		this.repository = repository;
//	}

}
