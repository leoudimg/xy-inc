package br.com.xy_inc.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.xy_inc.controller.AtributoController;
import br.com.xy_inc.controller.ModeloController;
import br.com.xy_inc.model.Atributo;
import br.com.xy_inc.model.Modelo;
import br.com.xy_inc.repository.AtributoRepository;

/**
 * Classe de testes para o Controller de Atributo.
 * 
 * @author Leonardo
 * @since 23/10/2015
 *
 */
public class AttibutoControllerTest {

	// Acesso ao banco deve ser Mockado.
	@Mock
	private AtributoRepository atributoRepository;

	@Mock
	private ModeloController modeloController;

	@Mock
	private Result result;

	@InjectMocks
	private AtributoController atributoController;

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);
		// atributoController = new AtributoController();

		// atributoController.setRepository(atributoRepository);
	}

	/**
	 * Verifica se o metodo de insercao do DAO foi chamado corretamente.
	 */
	@Test
	public void inserirAtributo() {

		Atributo atributo = new Atributo();
		atributo.setId(1L);

		atributoController.inserir(atributo);

		Mockito.verify(atributoRepository).inserir(atributo);

	}

	/**
	 * Verifica se o metodo de listagem do DAO foi chamado corretamente.
	 */
	@Test
	public void testar_listarRest() {
		atributoController.listarRest();

		Mockito.verify(atributoRepository).buscarTodos();
	}

	/**
	 * Testa o metodo inserirRest.
	 */
	@Test
	public void testar_inserirRest() {

		Atributo atributo = new Atributo();
		atributo.setId(1L);

		atributoController.inserirRest(atributo);

		Mockito.verify(atributoRepository).inserir(atributo);
	}

	/**
	 * Testa o metodo atualizarRest
	 */
	@Test
	public void testar_atualizarRest() {

		Atributo atributo = new Atributo();
		atributo.setId(1L);

		atributoController.atualizarRest(atributo);

		Mockito.verify(atributoRepository).atualizar(atributo);
	}

	/**
	 * Testa o metodo excluirRest
	 */
	@Test
	public void testar_excluirRest() {

		Atributo atributo = new Atributo();
		atributo.setId(1L);

		atributoController.excluirRest(atributo);

		Mockito.verify(atributoRepository).excluir(atributo.getId());
	}

	/**
	 * Testa o metodo editarRest
	 */
	@Test
	public void testar_editarRest() {

		atributoController.editarRest(1L);

		Mockito.verify(atributoRepository).buscarPorId(1L);
	}

	/**
	 * Testa o metodo novo
	 */
	@Test
	public void testar_novo() {

		atributoController.novo();

		Mockito.verify(modeloController).listar();
	}

	/**
	 * Testa o metodo editar
	 */
	@Test
	public void testar_editar() {

		Atributo atributo = new Atributo();
		atributo.setId(1L);

		atributoController.editar(atributo);

		Mockito.verify(atributoRepository).buscarPorId(atributo.getId());
	}

	/**
	 * Testa o metodo listarTodosPorModelo
	 */
	@Test
	public void testar_listarTodosPorModelo() {

		Modelo modelo = new Modelo();
		modelo.setId(1L);

		atributoController.listarTodosPorModelo(modelo);

		Mockito.verify(atributoRepository).listarTodosPorModelo(modelo);
	}
}
