package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.modelo.Motorista;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class MotoristaDAO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Motorista> listarTodos() {
		return em.createQuery("from motorista").getResultList();
	}

	@Transactional
	public void salvar(Motorista motorista) {
		em.merge(motorista);
	}

	@Transactional
	public void excluir(Motorista motorista) {
		em.remove(em.find(Motorista.class, motorista.getId()));
	}

	public Motorista buscarPeloCodigo(Integer codigo) {
		return em.find(Motorista.class, codigo);
	}

}
