package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.modelo.Funcionario;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class FuncionarioDAO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager em;

	@Transactional
	public void salvar(Funcionario funcionario) {
		em.merge(funcionario);

	}

	@Transactional
	public void excluir(Funcionario funcionario) {
		em.remove(em.find(Funcionario.class, funcionario.getId()));
	}

	public List<Funcionario> listarTodos() {
		return em.createQuery("from funcionario", Funcionario.class).getResultList();
	}

	public Funcionario buscarPeloCodigo(Integer codigo) {
		return em.find(Funcionario.class, codigo);
	}

}
