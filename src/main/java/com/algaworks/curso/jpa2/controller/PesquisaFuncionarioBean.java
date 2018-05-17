package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.FuncionarioDAO;
import com.algaworks.curso.jpa2.modelo.Funcionario;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaFuncionarioBean implements Serializable {
	@Inject
	private FuncionarioDAO funcionarioDAO;
	private List<Funcionario> funcionarios = new ArrayList<>();
	private Funcionario funcionarioSelecionado;

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}

	@PostConstruct
	public void init() {
		this.limpar();
	}

	private void limpar() {
		this.funcionarios = this.funcionarioDAO.listarTodos();
	}

	public void excluir() {
		this.funcionarioDAO.excluir(funcionarioSelecionado);
		this.funcionarios.remove(funcionarioSelecionado);
		FacesUtil.addSuccessMessage("Funcionario Excluido com sucesso!!!");
		this.limpar();
	}
}
