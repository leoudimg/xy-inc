package br.com.xy_inc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.google.gson.annotations.Expose;

import br.com.xy_inc.infra.AppConstants;

/**
 * Classe responsavel por garantir o mapeamento objeto relacional
 * 
 * @author Leonardo
 * 
 * @since 23/10/2015
 * 
 */
@Entity
@Table(name = "propriedade")
public class Propriedade extends AppBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2114955047465905872L;

	/**
	 * Campo correspondente a coluna string da tabela.
	 */
	@Expose
	@Column(name = "campo_string", length = AppConstants.PROPRIEDADE_LENGTH_STRING)
	private String string;

	/**
	 * Campo correspondente a coluna text da tabela.
	 */
	@Expose
	@Column(name = "campo_text", length = AppConstants.PROPRIEDADE_LENGTH_TEXT)
	@Type(type = "text")
	private String text;

	/**
	 * Campo correspondente a coluna decimal da tabela.
	 */
	@Expose
	@Column(name = "campo_decimal", columnDefinition = "decimal(10,2)")
	private Double decimal;

	/**
	 * Campo correspondente a coluna id_registro da tabela.
	 */
	@JoinColumn(name = "id_registro")
	@ManyToOne(targetEntity = Registro.class)
	private Registro registro;

	/**
	 * Campo correspondente a coluna id_atributo da tabela.
	 */
	@Expose
	@JoinColumn(name = "id_atributo")
	@ManyToOne(targetEntity = Atributo.class)
	private Atributo atributo;

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public Double getDecimal() {
		return decimal;
	}

	public void setDecimal(Double decimal) {
		this.decimal = decimal;
	}

	public Registro getRegistro() {
		return registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Atributo getAtributo() {
		return atributo;
	}

	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}

}
