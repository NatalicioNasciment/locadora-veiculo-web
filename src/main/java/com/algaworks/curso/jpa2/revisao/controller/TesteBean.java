package com.algaworks.curso.jpa2.revisao.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.revisao.dao.CategoriaDAO;
import com.algaworks.curso.jpa2.revisao.dao.EntidadeDAO;
import com.algaworks.curso.jpa2.revisao.modelo.Categoria;
import com.algaworks.curso.jpa2.revisao.modelo.Entidade;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class TesteBean implements Serializable {
	
	@Inject
	private EntidadeDAO entidadeDAO;
	@Inject
	private CategoriaDAO categoriaDAO;

	private static final long serialVersionUID = 1L;
	private Entidade entidade;
	private List<Categoria> categorias;
	
	@PostConstruct
	public void init() {
		limpar();
	}

	public void salvar() {
		this.entidadeDAO.salvar(entidade);
		FacesUtil.addSuccessMessage("Entidade salva com sucesso!!!");
		limpar();
	}

	public Entidade getEntidade() {
		return entidade;
	}

	public void setEntidade(Entidade entidade) {
		this.entidade = entidade;
	}
	
	public List<Categoria> getCategorias() {
		return categorias;
	}

	private void limpar() {
		this.categorias = categoriaDAO.todas();
		this.entidade = new Entidade();
	}

}
