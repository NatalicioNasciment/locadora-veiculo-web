package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.modelo.Motorista;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaMotoristaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private MotoristaDAO motoristaDAO;

	private List<Motorista> motoristas = new ArrayList<>();
	private Motorista motoristaSelecionado;

	public List<Motorista> getMotoristas() {
		return motoristas;
	}

	public Motorista getMotoristaSelecionado() {
		return motoristaSelecionado;
	}

	public void setMotoristaSelecionado(Motorista motoristaSelecionado) {
		this.motoristaSelecionado = motoristaSelecionado;
	}

	@PostConstruct
	public void init() {
		this.limpar();
	}

	public void excluir() {
		this.motoristaDAO.excluir(motoristaSelecionado);
		this.motoristas.remove(motoristaSelecionado);
		FacesUtil.addSuccessMessage("Motorista excluido com sucesso!!!");
		this.limpar();
	}

	private void limpar() {
		this.motoristas = this.motoristaDAO.listarTodos();
	}
}
