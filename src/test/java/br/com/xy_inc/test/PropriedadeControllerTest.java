package br.com.xy_inc.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.xy_inc.controller.ModeloController;
import br.com.xy_inc.controller.PropriedadeController;
import br.com.xy_inc.model.Propriedade;
import br.com.xy_inc.repository.PropriedadeRepository;

/**
 * Classe de testes para o Controller de Propriedade.
 * 
 * @author Leonardo
 * @since 23/10/2015
 *
 */
public class PropriedadeControllerTest {

	// Acesso ao banco deve ser Mockado.
	@Mock
	private PropriedadeRepository propriedadeRepository;

	@Mock
	private ModeloController modeloController;

	@Mock
	private Result result;

	@InjectMocks
	private PropriedadeController propriedadeController;

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);
		// propriedadeController = new PropriedadeController();

		// propriedadeController.setRepository(propriedadeRepository);
	}

	/**
	 * Verifica se o metodo de insercao do DAO foi chamado corretamente.
	 */
	@Test
	public void inserirPropriedade() {

		Propriedade propriedade = new Propriedade();
		propriedade.setId(1L);

		propriedadeController.inserir(propriedade);

		Mockito.verify(propriedadeRepository).inserir(propriedade);

	}

	/**
	 * Verifica se o metodo de listagem do DAO foi chamado corretamente.
	 */
	@Test
	public void testar_listarRest() {
		propriedadeController.listarRest();

		Mockito.verify(propriedadeRepository).buscarTodos();
	}

	/**
	 * Testa o metodo inserirRest.
	 */
	@Test
	public void testar_inserirRest() {

		Propriedade propriedade = new Propriedade();
		propriedade.setId(1L);

		propriedadeController.inserirRest(propriedade);

		Mockito.verify(propriedadeRepository).inserir(propriedade);
	}

	/**
	 * Testa o metodo atualizarRest
	 */
	@Test
	public void testar_atualizarRest() {

		Propriedade propriedade = new Propriedade();
		propriedade.setId(1L);

		propriedadeController.atualizarRest(propriedade);

		Mockito.verify(propriedadeRepository).atualizar(propriedade);
	}

	/**
	 * Testa o metodo excluirRest
	 */
	@Test
	public void testar_excluirRest() {

		Propriedade propriedade = new Propriedade();
		propriedade.setId(1L);

		propriedadeController.excluirRest(propriedade);

		Mockito.verify(propriedadeRepository).excluir(propriedade.getId());
	}

	/**
	 * Testa o metodo editarRest
	 */
	@Test
	public void testar_editarRest() {

		propriedadeController.editarRest(1L);

		Mockito.verify(propriedadeRepository).buscarPorId(1L);
	}

	/**
	 * Testa o metodo novo
	 */
	@Test
	public void testar_novo() {

		propriedadeController.novo();

	}

	/**
	 * Testa o metodo editar
	 */
	@Test
	public void testar_editar() {

		Propriedade propriedade = new Propriedade();
		propriedade.setId(1L);

		propriedadeController.editar(propriedade);

		Mockito.verify(propriedadeRepository).buscarPorId(propriedade.getId());
	}

}
