package br.com.xy_inc.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.xy_inc.controller.ModeloController;
import br.com.xy_inc.model.Modelo;
import br.com.xy_inc.repository.ModeloRepository;

/**
 * Classe de testes para o Controller de Modelo.
 * 
 * @author Leonardo
 * @since 23/10/2015
 *
 */
public class ModeloControllerTest {

	// Acesso ao banco deve ser Mockado.
	@Mock
	private ModeloRepository modeloRepository;

	@Mock
	private Result result;

	@InjectMocks
	private ModeloController modeloController;

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);
		// modeloController = new ModeloController();

		// modeloController.setRepository(modeloRepository);
	}

	/**
	 * Verifica se o metodo de insercao do DAO foi chamado corretamente.
	 */
	@Test
	public void inserirModelo() {

		Modelo modelo = new Modelo();
		modelo.setId(1L);

		modeloController.inserir(modelo);

		Mockito.verify(modeloRepository).inserir(modelo);

	}

	/**
	 * Verifica se o metodo de listagem do DAO foi chamado corretamente.
	 */
	@Test
	public void testar_listarRest() {
		modeloController.listarRest();

		Mockito.verify(modeloRepository).buscarTodos();
	}

	/**
	 * Testa o metodo inserirRest.
	 */
	@Test
	public void testar_inserirRest() {

		Modelo modelo = new Modelo();
		modelo.setId(1L);

		modeloController.inserirRest(modelo);

		Mockito.verify(modeloRepository).inserir(modelo);
	}

	/**
	 * Testa o metodo atualizarRest
	 */
	@Test
	public void testar_atualizarRest() {

		Modelo modelo = new Modelo();
		modelo.setId(1L);

		modeloController.atualizarRest(modelo);

		Mockito.verify(modeloRepository).atualizar(modelo);
	}

	/**
	 * Testa o metodo excluirRest
	 */
	@Test
	public void testar_excluirRest() {

		Modelo modelo = new Modelo();
		modelo.setId(1L);

		modeloController.excluirRest(modelo);

		Mockito.verify(modeloRepository).excluir(modelo.getId());
	}

	/**
	 * Testa o metodo editarRest
	 */
	@Test
	public void testar_editarRest() {

		modeloController.editarRest(1L);

		Mockito.verify(modeloRepository).buscarPorId(1L);
	}

	/**
	 * Testa o metodo novo
	 */
	@Test
	public void testar_novo() {

		modeloController.novo();

	}

	/**
	 * Testa o metodo erro
	 */
	@Test
	public void testar_erro() {

		modeloController.erro();

	}

	/**
	 * Testa o metodo editar
	 */
	@Test
	public void testar_editar() {

		Modelo modelo = new Modelo();
		modelo.setId(1L);

		modeloController.editar(modelo);

		Mockito.verify(modeloRepository).buscarPorId(modelo.getId());
	}

	/**
	 * Testa o metodo verificarModelo
	 */
	@Test
	public void testar_verificarModelo() {

		when(modeloRepository.verificarModelo("teste"))
				.thenReturn(new Modelo());

		modeloController.verificarModelo("teste");

	}
}
