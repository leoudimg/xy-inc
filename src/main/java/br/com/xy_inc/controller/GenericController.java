package br.com.xy_inc.controller;

import java.util.List;

/**
 * Interface criada para definir as operacoes basicas de um Controller.
 * 
 * @author Leonardo
 * 
 * @since 23/10/2015
 * 
 * @param <T>
 *            Tipo da Entidade que sera realizada a acao.
 */
public interface GenericController<T> {

	/**
	 * Busca uma entidade dado o Id da mesma.
	 * 
	 * @param id
	 *            Id da entidade a ser pesquisada.
	 * @return Retorna a entidade correspondente ao Id informado, null caso
	 *         contrario
	 */
	public T editarRest(Long id);

	/**
	 * Busca todos os registros correspondente ao tipo informado.
	 * 
	 * @return Lista contendo todas entidades.
	 */
	public List<T> listarRest();

	/**
	 * Metodo responsável por inserir um objeto na base de dados.
	 * 
	 * @param objeto
	 *            Objeto a ser inserido na base de dados.
	 */
	public void inserirRest(T objeto);

	/**
	 * Metodo responsavel por realizar um update na base de dados do registro
	 * informado.
	 * 
	 * @param objeto
	 *            Objeto a ser atualizado.
	 */
	public void atualizarRest(T objeto);

	/**
	 * Metodo responsavel por excluir um objeto na base de dados.
	 * 
	 * @param objeto
	 *            objeto a ser excluido.
	 */
	public void excluirRest(T objeto);

	/**
	 * Metodo responsavel por redirecionar o usuario para a tela de edicao de
	 * conteudo.
	 * 
	 * @param id
	 *            - id do conteudo a ser editado.
	 * @return retorna conteudo a ser editado
	 */
	public T editar(Long id);

	/**
	 * Metodo responsavel por redirecionar o usuario para a tela de edicao de
	 * conteudo.
	 * 
	 * @param objeto
	 *            - conteudo a ser excluido.
	 */
	public void excluir(T objeto);

	/**
	 * Metodo responsavel por redirecionar o usuario para a tela de listagem de
	 * conteudo.
	 * 
	 * @return Retorna todos objetos.
	 */
	public List<T> listar();

	/**
	 * Metodo responsavel por inserir o conteudo na base de dados.
	 * 
	 * @param objeto
	 *            - conteudo a ser inserido.
	 */
	public void inserir(T objeto);

}
