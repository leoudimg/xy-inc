package br.com.xy_inc.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "registro")
public class Registro extends AppBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9024756907081384548L;
	/**
	 * Campo correspondente a coluna id_modelo da tabela.
	 */
	@Expose
	@JoinColumn(name = "id_modelo")
	@ManyToOne(targetEntity = Modelo.class)
	private Modelo modelo;

	/**
	 * Propriedades do {@link Registro}, corresponde aos {@link Atributo } do
	 * {@link Modelo}
	 */
	@Expose
	@OneToMany(mappedBy = "registro", targetEntity = Propriedade.class, fetch = FetchType.EAGER)
	private List<Propriedade> propriedades;

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public List<Propriedade> getPropriedades() {
		return propriedades;
	}

	public void setPropriedades(List<Propriedade> propriedades) {
		this.propriedades = propriedades;
	}

}
