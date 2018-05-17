package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.service.CadastroFabricanteService;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroFabricanteBean implements Serializable {
	@Inject
	private CadastroFabricanteService cadastroFabricanteService;
	private Fabricante fabricante;

	@PostConstruct
	public void init() {
		this.limpar();
	}

	private void limpar() {
		this.fabricante = new Fabricante();
	}

	public void salvar() {
		try {
			cadastroFabricanteService.cadastrar(fabricante);
			FacesUtil.addSuccessMessage("Fabricante cadastrado com sucesso!!!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		
		this.limpar();
	}
	
	public void remover() {
		
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

}
