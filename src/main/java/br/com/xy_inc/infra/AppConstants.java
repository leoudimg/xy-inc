package br.com.xy_inc.infra;

/**
 * Classe para armazenar as constantes utilizadas no sistema.
 * 
 * @author Leonardo
 * @since 25/10/2015
 * 
 * 
 *
 */
public class AppConstants {

	/**
	 * Constante referente ao nome da propriedade.
	 */
	public static final String PROPRIEDADE_NOME = "propriedade.nome";

	/**
	 * Constante referente ao campo do tipo String da propriedade.
	 */
	public static final String PROPRIEDADE_STRING = "propriedade.string";

	/**
	 * Constante referente ao campo do tipo Decimal da propriedade.
	 */
	public static final String PROPRIEDADE_DECIMAL = "propriedade.decimal";

	/**
	 * Constante referente ao campo do tipo Texto imal da propriedade.
	 */
	public static final String PROPRIEDADE_TEXTO = "propriedade.texto";

	/**
	 * Constante referente ao indicador do tipo do atributo.
	 */
	public static final String PROPRIEDADE_IN_TIPO_ATTR = "propriedade.in_tipo_attr";

	/**
	 * Constante referente ao tamanho dos campos do Tipo Text.
	 */
	public static final int PROPRIEDADE_LENGTH_TEXT = 65535;

	/**
	 * Constante referente ao tamanho dos campos do Tipo String.
	 */
	public static final int PROPRIEDADE_LENGTH_STRING = 3000;

	/**
	 * Erro HTTP Bad Request para quando usuario informa algum parametro
	 * invalido.
	 */
	public static final int HTTP_ERROR_400_BAD_REQUEST = 400;

}
