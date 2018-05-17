package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.modelo.ModeloCarro;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class ModeloCarroDAO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager em;

	@Transactional
	public void salvar(ModeloCarro modeloCarro) {
		em.merge(modeloCarro);
	}

	@SuppressWarnings("unchecked")
	public List<ModeloCarro> listarTodos() {
		return em.createNamedQuery("ModeloCarro.listarTodos").getResultList();

	}

	@Transactional
	public void excluir(ModeloCarro modeloCarro) throws NegocioException {
		modeloCarro = em.find(ModeloCarro.class, modeloCarro.getId());
		em.remove(modeloCarro);
		em.flush();
	}

	public ModeloCarro buscarPeloCodigo(Long codigo) {
		return (ModeloCarro) em.createNamedQuery("ModeloCarro.buscarPeloCodigo").setParameter("codigo", codigo)
				.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<ModeloCarro> buscarComPaginacao(int first, int pageSize) {
		return em.createNamedQuery("ModeloCarro.listarTodos").setFirstResult(first).setMaxResults(pageSize)
				.getResultList();
	}

	public Long retornarQuantidadeDeModeloCarro() {
		return em.createQuery("select count(mc) from modelo_carro mc ", Long.class).getSingleResult();
	}
}
