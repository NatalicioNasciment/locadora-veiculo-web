package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.AcessorioDAO;
import com.algaworks.curso.jpa2.modelo.Acessorio;
import com.algaworks.curso.jpa2.modelolazy.LazyAcessorioDataModel;
import com.algaworks.curso.jpa2.service.AcessorioService;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaAcessorioBean implements Serializable {
	@Inject
	private AcessorioDAO acessorioDAO;
	@Inject
	AcessorioService acessorioService;
	private List<Acessorio> acessorios = new ArrayList<>();
	private Acessorio acessorioSelecionado;
	private LazyAcessorioDataModel lazyAcessorios;

	public Acessorio getAcessorioSelecionado() {
		return acessorioSelecionado;
	}

	public void setAcessorioSelecionado(Acessorio acessorioSelecionado) {
		this.acessorioSelecionado = acessorioSelecionado;
	}

	public List<Acessorio> getAcessorios() {
		return acessorios;
	}

	public LazyAcessorioDataModel getLazyAcessorios() {
		return lazyAcessorios;
	}

	@PostConstruct
	public void init() {
		this.limpar();
	}

	private void limpar() {
		this.lazyAcessorios = new LazyAcessorioDataModel(acessorioDAO);

	}

	public void excluir() {
		try {
			this.acessorioService.excluir(acessorioSelecionado);
			this.acessorios.remove(acessorioSelecionado);
			this.limpar();
			FacesUtil.addSuccessMessage("Acessorio excluido com sucesso!!!");
		} catch (NegocioException e) {
			System.out.println(e.getMessage());
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

}
