package com.algaworks.curso.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.curso.jpa2.controller.MotoristaDAO;
import com.algaworks.curso.jpa2.modelo.Motorista;

public class CadastroMotoristaService implements Serializable {
	@Inject
	private MotoristaDAO motoristaDAO;

	public void salvar(Motorista motorista) throws NegocioException {
		if (motorista != null) {
			motoristaDAO.salvar(motorista);
		}
	}

	public void excluir(Motorista motorista) throws NegocioException {
		if (motorista != null) {
			motoristaDAO.excluir(motorista);
		}
	}

}
