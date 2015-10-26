package br.com.xy_inc.repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.slf4j.Logger;

/**
 * Interface criada para definir as operacoes basicas de um DAO.
 * 
 * @author Leonardo
 * 
 * @since 23/10/2015
 * 
 * @param <T>
 *            Tipo da Entidade que sera realizada a acao.
 */
@Stateless
public abstract class Repository<T> {

	@PersistenceContext(unitName = "default")
	protected EntityManager em;

	@Inject
	protected Logger log;

	/**
	 * Metodo responsavel por inserir um objeto na base de dados.
	 * 
	 * @param entidade
	 *            Objeto a ser inserido na base de dados.
	 */
	public void inserir(T entidade) {
		em.persist(entidade);
		em.flush();
	}

	/**
	 * Metodo responsavel por excluir um objeto na base de dados.
	 * 
	 * @param entidade
	 *            objeto a ser excluido.
	 */
	public void excluir(T entidade) {
		if (!em.contains(entidade)) {
			em.merge(entidade);
		}
		retornaTipo();
		em.remove(entidade);
	}

	/**
	 * Metodo responsavel por excluir um objeto na base de dados.
	 * 
	 * @param entidade
	 *            objeto a ser excluido.
	 */
	public void excluir(Long id) {
		// TODO refatorar remover consulta.
		T objeto = this.buscarPorId(id);
		em.remove(objeto);

	}

	/**
	 * Busca todos os registros correspondente ao tipo informado.
	 * 
	 * @return Lista contendo todas entidades.
	 */
	@SuppressWarnings("unchecked")
	public List<T> buscarTodos() {
		CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(retornaTipo()));
		return (List<T>) em.createQuery(cq).getResultList();
	}

	/**
	 * Busca uma entidade dado o Id da mesma.
	 * 
	 * @param Classe
	 *            da entidade a ser pesquisada.
	 * 
	 * @param id
	 *            Id da entidade a ser pesquisada.
	 * @return Retorna a entidade correspondente ao Id informado, null caso
	 *         contrario
	 */
	public T buscarPorId(Long id) {
		return em.find(retornaTipo(), id);
	}

	/**
	 * Metodo responsavel por realizar um update na base de dados do registro
	 * informado.
	 * 
	 * @param objeto
	 *            Objeto a ser atualizado.
	 */
	public T atualizar(T entidade) {
		return em.merge(entidade);
	}

	/**
	 * Retorna o tipo do objeto generico.
	 * 
	 * @return tipo do objeto generico.
	 */
	@SuppressWarnings("unchecked")
	private Class<T> retornaTipo() {
		Class<?> clazz = this.getClass();

		while (!clazz.getSuperclass().equals(Repository.class)) {
			clazz = clazz.getSuperclass();
		}

		ParameterizedType tipoGenerico = (ParameterizedType) clazz
				.getGenericSuperclass();
		return (Class<T>) tipoGenerico.getActualTypeArguments()[0];
	}

}
