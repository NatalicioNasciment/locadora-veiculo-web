package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.modelo.Acessorio;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class AcessorioDAO implements Serializable {
	@Inject
	private EntityManager em;

	@Transactional
	public void salvar(Acessorio acessorio) {
		em.merge(acessorio);
	}

	@Transactional
	public void excluir(Acessorio acessorio) {
		acessorio = em.find(Acessorio.class, acessorio.getId());
		em.remove(acessorio);
		em.flush();
	}

	public List<Acessorio> listarTodos() {
		return em.createNamedQuery("Acessorio.listarTodos", Acessorio.class).getResultList();
	}

	public Acessorio buscarPorCodigo(Integer codigo) {
		return em.createNamedQuery("Acessorio.buscarPorCodigo", Acessorio.class).setParameter("codigo", codigo)
				.getSingleResult();
	}

	public List<Acessorio> buscarComPaginacao(int first, int pageSize) {
		return em.createNamedQuery("Acessorio.listarTodos", Acessorio.class)
				.setFirstResult(first)
				.setMaxResults(pageSize).getResultList();
	}

	public Long retornarQuantidadeDeAcessorios() {
		return em.createQuery("select count(a) from acessorio a", Long.class).getSingleResult();
	}

}
