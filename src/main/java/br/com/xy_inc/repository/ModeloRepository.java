package br.com.xy_inc.repository;

import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.xy_inc.model.Modelo;
import br.com.xy_inc.model.Registro;

/**
 * Classe DAO referente a Entidade {@link Modelo} que prove as acoes de acesso a
 * dados.
 * 
 * @author Leonardo
 * @since 23/10/2015
 * 
 */
@RequestScoped
public class ModeloRepository extends Repository<Modelo> {

	/**
	 * Construtor padrao.
	 */
	public ModeloRepository() {

	}

	/**
	 * Verificar se existe um {@link Registro} para o link enviado.
	 * 
	 * @param link
	 *            - link enviado pelo usuario.
	 * 
	 * @return Retorna o {@link Registro} correspondente ao link passado por
	 *         parametro
	 */
	public Modelo verificarModelo(String link) {

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Modelo> cq = cb.createQuery(Modelo.class);

		Root e = cq.from(Modelo.class);
		cq.select(e);

		cq.where(cb.equal(e.get("link"), link));

		Modelo modelo;

		try {

			modelo = em.createQuery(cq).getSingleResult();
		} catch (NonUniqueResultException ex) {

			log.warn("Existe mais de um Modelo com o link " + link + ".");
			return null;
		} catch (NoResultException ex) {

			log.warn("Nao existe Modelo com o link " + link + ".");
			return null;
		}
		return modelo;
	}

}
