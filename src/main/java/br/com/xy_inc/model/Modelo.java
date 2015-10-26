package br.com.xy_inc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

/**
 * Classe responsavel por garantir o mapeamento objeto relacional
 * 
 * @author Leonardo
 * @since 23/10/2015
 * 
 */
@Entity
@Table(name = "modelo")
public class Modelo extends AppBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1449895710845386870L;

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
	 * Campo correspondente a coluna link da tabela.
	 */
	@Expose
	@Column(name = "link")
	private String link;

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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
