package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class FabricanteDAO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager em;

	@Transactional
	public void salvar(Fabricante fabricante) {
		em.merge(fabricante);

	}

	public List<Fabricante> listarTodos() {
		return em.createNamedQuery("Fabricante.listarTodos", Fabricante.class).getResultList();

	}

	@Transactional
	public void excluir(Fabricante fabricante) throws NegocioException {
		fabricante = em.find(Fabricante.class, fabricante.getId());
		em.remove(fabricante);
		em.flush();
	}

	public Fabricante buscarPeloCodigo(Integer codigo) {
		return em.createNamedQuery("Fabricante.buscarPeloCodigo", Fabricante.class).setParameter("codigo", codigo)
				.getSingleResult();
	}

	public List<Fabricante> buscarComPaginacao(int first, int pageSize) {
		return em.createNamedQuery("Fabricante.listarTodos", Fabricante.class)
				.setFirstResult(first)
				.setMaxResults(pageSize)
				.getResultList();
	}

	public Long calcularQuantidadeDeCarros() {
		return em.createQuery("select count(f) from fabricante f", Long.class).getSingleResult();
	}
}
