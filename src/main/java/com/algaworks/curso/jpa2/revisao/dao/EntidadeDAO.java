package com.algaworks.curso.jpa2.revisao.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.revisao.modelo.Entidade;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class EntidadeDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager em;

	@Transactional
	public void salvar(Entidade entidade) {
		em.merge(entidade);
	}
}
