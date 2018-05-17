package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.ModeloCarroDAO;
import com.algaworks.curso.jpa2.modelo.ModeloCarro;
import com.algaworks.curso.jpa2.modelolazy.LazyModeloCarroDataModel;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaModeloCarroBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ModeloCarroDAO modeloCarroDAO;
	private List<ModeloCarro> listaModeloCarro = new ArrayList<>();

	private ModeloCarro modeloCarroSelecionado;
	private LazyModeloCarroDataModel lazyModeloCarro;

	public List<ModeloCarro> getListaModeloCarro() {
		return listaModeloCarro;
	}

	public ModeloCarro getModeloCarroSelecionado() {
		return modeloCarroSelecionado;
	}

	public void setModeloCarroSelecionado(ModeloCarro modeloCarroSelecionado) {
		this.modeloCarroSelecionado = modeloCarroSelecionado;
	}

	public LazyModeloCarroDataModel getLazyModeloCarro() {
		return lazyModeloCarro;
	}

	public void excluir() {
		try {
			modeloCarroDAO.excluir(modeloCarroSelecionado);
			this.listaModeloCarro.remove(modeloCarroSelecionado);
			FacesUtil.addSuccessMessage("Modelo Carro excluido com sucesso!!!");
			this.limpar();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	private void limpar() {
		lazyModeloCarro = new LazyModeloCarroDataModel(modeloCarroDAO);
		this.listaModeloCarro = modeloCarroDAO.listarTodos();
	}

	@PostConstruct
	public void init() {
		this.limpar();
	}

}
