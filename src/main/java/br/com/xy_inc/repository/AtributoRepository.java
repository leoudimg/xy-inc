package br.com.xy_inc.repository;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.xy_inc.model.Atributo;
import br.com.xy_inc.model.Modelo;

/**
 * Classe DAO referente a Entidade {@link Atributo} que prove as acoes de acesso
 * a dados.
 * 
 * @author Leonardo
 * 
 * @since 23/10/2015
 * 
 */
@RequestScoped
public class AtributoRepository extends Repository<Atributo> {

	/**
	 * Lista todos os atributos de um {@link Modelo}.
	 * 
	 * @param modelo
	 *            - {@link Modelo} filtro para os atributos.
	 * @return Retorna lista de {@link Atributo} que contem o {@link Modelo}
	 *         especificado.
	 */
	public List<Atributo> listarTodosPorModelo(Modelo modelo) {

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Atributo> cq = cb.createQuery(Atributo.class);

		Root e = cq.from(Atributo.class);
		cq.select(e);

		cq.where(cb.equal(e.get("modelo"), modelo));

		return em.createQuery(cq).getResultList();
	}
}
