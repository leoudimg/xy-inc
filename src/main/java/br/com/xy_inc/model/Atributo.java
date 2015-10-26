package br.com.xy_inc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

/**
 * Classe responsavel por garantir o mapeamento objeto relacional
 * 
 * @author Leonardo
 * 
 * @since 23/10/2015
 * 
 */
@Entity
@Table(name = "atributo")
public class Atributo extends AppBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3355147795839322331L;

	/**
	 * Campo correspondente a coluna in_tipo_atributo da tabela. Podera assumir
	 * os valores S para String ou D para decimal.
	 */
	@Expose
	@Column(name = "in_tipo_atributo")
	private String indicadorTipoAtributo;

	/**
	 * Campo correspondente a coluna nome da tabela.
	 */
	@Expose
	@Column(name = "nome")
	private String nome;

	/**
	 * Campo correspondente a coluna descricao da tabela.
	 */
	@Expose
	@Column(name = "descricao")
	private String descricao;

	/**
	 * Campo correspondente a coluna id_modelo da tabela.
	 */
	@Expose
	@JoinColumn(name = "id_modelo")
	@ManyToOne(targetEntity = Modelo.class)
	private Modelo modelo;

	public String getIndicadorTipoAtributo() {
		return indicadorTipoAtributo;
	}

	public void setIndicadorTipoAtributo(String indicadorTipoAtributo) {
		this.indicadorTipoAtributo = indicadorTipoAtributo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

}
