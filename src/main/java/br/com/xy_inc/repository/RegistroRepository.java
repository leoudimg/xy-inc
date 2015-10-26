package br.com.xy_inc.repository;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.xy_inc.model.Modelo;
import br.com.xy_inc.model.Registro;

/**
 * Classe DAO referente a Entidade {@link Registro} que prove as acoes de acesso
 * a dados.
 * 
 * @author Leonardo
 * @since 23/10/2015
 * 
 */
@RequestScoped
public class RegistroRepository extends Repository<Registro> {

	/**
	 * Construtor padrao.
	 */
	public RegistroRepository() {

	}

	/**
	 * 
	 * @param modelo
	 *            {@link Modelo} que sera utilizado como filtro de pesquisa.
	 * @return Retorna todos Registros atrelados ao {@link Modelo}.
	 */
	@SuppressWarnings("unchecked")
	public List<Registro> listarTodosPorModelo(Modelo modelo) {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Registro> cq = cb.createQuery(Registro.class);

		Root e = cq.from(Registro.class);
		cq.select(e);

		cq.where(cb.equal(e.get("modelo"), modelo));

		return em.createQuery(cq).getResultList();
	}
}
