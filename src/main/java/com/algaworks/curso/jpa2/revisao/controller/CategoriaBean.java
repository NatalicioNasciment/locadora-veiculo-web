package com.algaworks.curso.jpa2.revisao.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.revisao.dao.CategoriaDAO;
import com.algaworks.curso.jpa2.revisao.modelo.Categoria;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Categoria categoria;
	@Inject
	private CategoriaDAO categoriaDAO;

	public void salvar() {
		this.categoriaDAO.salvar(categoria);
		FacesUtil.addSuccessMessage("Categoria salva com sucesso!!!");
		limpar();
	}

	@PostConstruct
	public void init() {
		limpar();
	}

	private void limpar() {
		this.categoria = new Categoria();
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
