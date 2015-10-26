package br.com.xy_inc.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.xy_inc.model.Atributo;
import br.com.xy_inc.model.Propriedade;
import br.com.xy_inc.model.Registro;

/**
 * Classe de testes para o Controller de Atributo.
 * 
 * @author Leonardo
 * @since 23/10/2015
 *
 */
public class UtilTest {

	@Before
	public void init() {

	}

	/**
	 * Verifica se o metodo de insercao do DAO foi chamado corretamente.
	 */
	@Test
	public void inserirAtributo() {

		Registro r = new Registro();

		r.setId(2L);

		Atributo atDecimal = new Atributo();
		atDecimal.setId(11L);

		Propriedade decimal = new Propriedade();
		decimal.setAtributo(atDecimal);
		decimal.setDecimal(10.2);
//		decimal.setId(1L);
		decimal.setRegistro(r);

		Atributo atString = new Atributo();
		atString.setId(9L);

		Propriedade string = new Propriedade();
		string.setAtributo(atString);
		string.setString("STRING");
//		string.setId(2L);
		string.setRegistro(r);

		Atributo atTexto = new Atributo();
		atTexto.setId(9L);

		Propriedade text = new Propriedade();
		text.setAtributo(atTexto);
		text.setText("texto");
//		text.setId(1L);
		text.setRegistro(r);

		List<Propriedade> propriedades = new ArrayList<Propriedade>();
//		propriedades.add(text);
		propriedades.add(decimal);
		propriedades.add(string);
		
		Gson gson = new GsonBuilder().serializeNulls()
				.excludeFieldsWithoutExposeAnnotation().create();

		String json = gson.toJson(propriedades);
		
		System.out.println(json);

	}
}
