package br.com.xy_inc.test;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import br.com.caelum.vraptor.Result;
import br.com.xy_inc.controller.AtributoController;
import br.com.xy_inc.controller.ModeloController;
import br.com.xy_inc.controller.PropriedadeController;
import br.com.xy_inc.controller.RegistroController;
import br.com.xy_inc.model.Atributo;
import br.com.xy_inc.model.Modelo;
import br.com.xy_inc.model.Propriedade;
import br.com.xy_inc.model.Registro;
import br.com.xy_inc.repository.RegistroRepository;

public class RegistroControllerTest {

	// Acesso ao banco deve ser Mockado.
	@Mock
	private RegistroRepository registroRepository;

	@Mock
	private ModeloController modeloController;

	@Mock
	private AtributoController atributoController;

	@Mock
	private PropriedadeController propriedadeController;

	@Mock
	private Result result;

	@Mock
	private Logger log;

	@InjectMocks
	private RegistroController registroController;

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);
		// registroController = new RegistroController();

		// registroController.setRepository(registroRepository);
	}

	/**
	 * Verifica se o metodo de insercao do DAO foi chamado corretamente.
	 */
	@Test
	public void inserirRegistro() {

		Registro registro = new Registro();
		registro.setId(1L);

		registroController.inserir(registro);

		Mockito.verify(registroRepository).inserir(registro);

	}

	/**
	 * Verifica se o metodo de listagem do DAO foi chamado corretamente.
	 */
	@Test
	public void testar_listarRest() {
		registroController.listarRest();

		Mockito.verify(registroRepository).buscarTodos();
	}

	/**
	 * Testa o metodo inserirRest.
	 */
	@Test
	public void testar_inserirRest() {

		Registro registro = new Registro();
		registro.setId(1L);

		registroController.inserirRest(registro);

		Mockito.verify(registroRepository).inserir(registro);
	}

	/**
	 * Testa o metodo atualizarRest
	 */
	@Test
	public void testar_atualizarRest() {

		Registro registro = new Registro();
		registro.setId(1L);

		registroController.atualizarRest(registro);

		Mockito.verify(registroRepository).atualizar(registro);
	}

	/**
	 * Testa o metodo excluirRest
	 */
	@Test
	public void testar_excluirRest() {

		Registro registro = new Registro();
		registro.setId(1L);

		registroController.excluirRest(registro);

		Mockito.verify(registroRepository).excluir(registro.getId());
	}

	/**
	 * Testa o metodo editarRest
	 */
	@Test
	public void testar_editarRest() {

		registroController.editarRest(1L);

		Mockito.verify(registroRepository).buscarPorId(1L);
	}

	/**
	 * Testa o metodo novo
	 */
	@Test
	public void testar_novo() {

		registroController.novo();

	}

	/**
	 * Testa o metodo editar
	 */
	@Test
	public void testar_editar() {

		Registro registro = new Registro();
		registro.setId(1L);

		registroController.editar(registro);

		Mockito.verify(registroRepository).buscarPorId(registro.getId());
	}

	/**
	 * Testa o metodo inserirRegistro
	 */
	@Test
	public void testar_inserirRegistro() {

		Registro registro = new Registro();
		registro.setId(1L);

		Registro r = registroController.inserirRegistro("", null);

		Assert.assertNull(r);

	}

	/**
	 * Testa o metodo inserirRegistro
	 */
	@Test
	public void testar_inserirRegistro2() {

		Registro registro = new Registro();
		registro.setId(1L);

		Propriedade p = new Propriedade();
		List<Propriedade> lista = new ArrayList<Propriedade>();
		lista.add(p);

		Registro r = registroController.inserirRegistro("teste", lista);

		Assert.assertNull(r);

	}

	/**
	 * Testa o metodo inserirRegistro
	 */
	@Test
	public void testar_inserirRegistro3() {

		Registro registro = new Registro();
		registro.setId(1L);

		Propriedade p = new Propriedade();
		List<Propriedade> lista = new ArrayList<Propriedade>();
		lista.add(p);

		when(modeloController.verificarModelo("teste"))
				.thenReturn(new Modelo());

		Registro r = registroController.inserirRegistro("teste", lista);

		Assert.assertNotNull(r);

	}

	/**
	 * Testa o metodo listarTodosRegistros
	 */
	@Test
	public void testar_listarTodosRegistros() {

		Registro registro = new Registro();
		registro.setId(1L);

		List<Registro> lista = registroController.listarTodosRegistros("");

		Assert.assertNull(lista);

	}

	/**
	 * Testa o metodo listarTodosRegistros
	 */
	@Test
	public void testar_listarTodosRegistros2() {

		Registro registro = new Registro();
		registro.setId(1L);

		when(modeloController.verificarModelo("teste"))
				.thenReturn(new Modelo());

		List<Registro> lista = registroController.listarTodosRegistros("teste");

		Assert.assertNotNull(lista);

	}

	/**
	 * Testa o metodo apagarRegistro
	 */
	@Test
	public void testar_apagarRegistro() {

		Registro registro = new Registro();
		registro.setId(1L);

		boolean result = registroController.apagarRegistro("", "");

		Assert.assertFalse(result);

	}

	/**
	 * Testa o metodo apagarRegistro
	 */
	@Test
	public void testar_apagarRegistro2() {

		Registro registro = new Registro();
		registro.setId(1L);

		when(modeloController.verificarModelo("teste"))
				.thenReturn(new Modelo());

		boolean result = registroController.apagarRegistro("1", "teste");

		Assert.assertFalse(result);

	}

	/**
	 * Testa o metodo apagarRegistro
	 */
	@Test
	public void testar_apagarRegistro3() {

		Registro registro = new Registro();
		registro.setId(1L);

		when(modeloController.verificarModelo("teste"))
				.thenReturn(new Modelo());

		Registro r = new Registro();

		r.setPropriedades(Arrays.asList(new Propriedade(), new Propriedade()));

		when(registroRepository.buscarPorId(1L)).thenReturn(r);

		boolean result = registroController.apagarRegistro("1", "teste");

		Assert.assertTrue(result);

	}

	/**
	 * Testa o metodo apagarRegistro
	 */
	@Test
	public void testar_apagarRegistro4() {

		Registro registro = new Registro();
		registro.setId(1L);

		when(modeloController.verificarModelo("teste"))
				.thenReturn(new Modelo());

		boolean result = registroController.apagarRegistro("letra", "teste");

		Assert.assertFalse(result);

	}

	/**
	 * Testa o metodo apagarRegistro
	 */
	@Test
	public void testar_atualizarRegistro2() {

		Registro registro = new Registro();
		registro.setId(1L);

		when(modeloController.verificarModelo("teste"))
				.thenReturn(new Modelo());

		Registro result = registroController.atualizarRegistro("teste",
				registro);

		Assert.assertNull(result);

	}

	/**
	 * Testa o metodo apagarRegistro
	 */
	@Test
	public void testar_atualizarRegistro3() {

		Registro registro = new Registro();
		registro.setId(1L);

		when(modeloController.verificarModelo("teste"))
				.thenReturn(new Modelo());

		List<Atributo> lista = Arrays.asList(new Atributo(), new Atributo());

		when(atributoController.listarTodosPorModelo(new Modelo())).thenReturn(
				lista);

		Propriedade p1 = new Propriedade();
		p1.setAtributo(new Atributo());

		registro.setPropriedades(Arrays.asList(new Propriedade(),
				new Propriedade()));

		Registro result = registroController.atualizarRegistro("teste",
				registro);

		Assert.assertNull(result);

	}

	/**
	 * Testa o metodo apagarRegistro
	 */
	@Test
	public void testar_atualizarRegistro4() {

		Registro registro = new Registro();
		registro.setId(1L);

		when(modeloController.verificarModelo("teste"))
				.thenReturn(new Modelo());

		Atributo a1 = new Atributo();
		a1.setId(1L);

		List<Atributo> lista = Arrays
				.asList(new Atributo(), new Atributo(), a1);

		when(atributoController.listarTodosPorModelo(new Modelo())).thenReturn(
				lista);

		Propriedade p1 = new Propriedade();
		p1.setAtributo(a1);

		registro.setPropriedades(Arrays.asList(p1));

		Registro result = registroController.atualizarRegistro("teste",
				registro);

		Assert.assertNull(result);

	}

	/**
	 * Testa o metodo listarRegistro
	 */
	@Test
	public void testar_listarRegistro() {

		Registro registro = new Registro();
		registro.setId(1L);

		when(modeloController.verificarModelo("teste"))
				.thenReturn(new Modelo());

		Registro result = registroController.listarRegistro("teste", "1");

		Assert.assertNull(result);

	}
	
	/**
	 * Testa o metodo listarRegistro
	 */
	@Test
	public void testar_listarRegistro2() {

		Registro registro = new Registro();
		registro.setId(1L);

		when(modeloController.verificarModelo("teste"))
				.thenReturn(new Modelo());

		Registro result = registroController.listarRegistro("teste", "letra");

		Assert.assertNull(result);

	}
}
