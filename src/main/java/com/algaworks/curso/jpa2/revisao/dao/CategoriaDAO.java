package com.algaworks.curso.jpa2.revisao.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.revisao.modelo.Categoria;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class CategoriaDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager em;

	@Transactional
	public void salvar(Categoria categoria) {
		em.merge(categoria);
	}

	@SuppressWarnings("unchecked")
	public List<Categoria> todas() {
		return em.createNativeQuery("select * from categoria", Categoria.class).getResultList();
	}

	public Categoria buscarPorCodigo(Integer id) {
		return em.find(Categoria.class, id);
	}
}
