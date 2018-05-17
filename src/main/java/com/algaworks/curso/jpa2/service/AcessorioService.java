package com.algaworks.curso.jpa2.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.algaworks.curso.jpa2.dao.AcessorioDAO;
import com.algaworks.curso.jpa2.modelo.Acessorio;

public class AcessorioService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private AcessorioDAO acessorioDAO;

	public void salvar(Acessorio acessorio) throws NegocioException {
	
			this.acessorioDAO.salvar(acessorio);
	}

	public void excluir(Acessorio acessorio) throws NegocioException {
		if (acessorio == null) {
			throw new NegocioException("Acessorio está nulo");
		} else {
			this.acessorioDAO.excluir(acessorio);
		}
	}

	public List<Acessorio> listarTodos() {
		return this.acessorioDAO.listarTodos();
	}

}
