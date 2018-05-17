package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.modelo.Motorista;
import com.algaworks.curso.jpa2.modelo.Sexo;
import com.algaworks.curso.jpa2.service.CadastroMotoristaService;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroMotoristaBean implements Serializable {
	private Motorista motorista;
	private List<Sexo> sexo;
	@Inject
	private CadastroMotoristaService cadastroMotoristaService;

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public List<Sexo> getSexo() {
		return sexo;
	}

	@PostConstruct
	public void init() {
		this.limpar();
		sexo = Arrays.asList(Sexo.values());
	}

	public void salvar() throws NegocioException {
		this.cadastroMotoristaService.salvar(motorista);
		FacesUtil.addSuccessMessage("Motorista salvo com sucesso!!!!");
		this.limpar();
	}

	private void limpar() {
		this.motorista = new Motorista();
	}

}
