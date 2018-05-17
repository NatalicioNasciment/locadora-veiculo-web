package com.algaworks.curso.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.curso.jpa2.dao.FuncionarioDAO;
import com.algaworks.curso.jpa2.modelo.Funcionario;

public class CadastroFuncionarioService implements Serializable {
	@Inject
	private FuncionarioDAO funcionarioDAO;

	public void salvar(Funcionario funcionario) throws NegocioException {
		if (funcionario != null) {
			this.funcionarioDAO.salvar(funcionario);
		} else {
			throw new NegocioException("Erro ao cadastrar funcionario");
		}
	}

	public void excluir(Funcionario funcionario) {
		if (funcionario != null) {
			this.funcionarioDAO.excluir(funcionario);
		}
	}

}
