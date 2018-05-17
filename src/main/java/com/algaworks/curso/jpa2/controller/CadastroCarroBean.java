package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import com.algaworks.curso.jpa2.dao.AcessorioDAO;
import com.algaworks.curso.jpa2.dao.ModeloCarroDAO;
import com.algaworks.curso.jpa2.modelo.Acessorio;
import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.modelo.ModeloCarro;
import com.algaworks.curso.jpa2.service.CadastroCarroService;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCarroBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Carro carro;
	private List<ModeloCarro> modelosCarros;
	private List<Acessorio> acessorios;

	@Inject
	CadastroCarroService cadastroCarroService;

	@Inject
	AcessorioDAO acessorioDAO;

	@Inject
	ModeloCarroDAO modeloCarroDAO;

	private Carro carroSelecionado;
	private UploadedFile uploadedFile;

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public Carro getCarroSelecionado() {
		return carroSelecionado;
	}

	public void setCarroSelecionado(Carro carroSelecionado) {
		this.carroSelecionado = carroSelecionado;
	}

	public List<ModeloCarro> getModelosCarros() {
		return modelosCarros;
	}

	public void setModelosCarros(List<ModeloCarro> modelosCarros) {
		this.modelosCarros = modelosCarros;
	}

	public List<Acessorio> getAcessorios() {
		return acessorios;
	}

	public void setAcessorios(List<Acessorio> acessorios) {
		this.acessorios = acessorios;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	@PostConstruct
	public void init() {
		this.limpar();
		this.modelosCarros = modeloCarroDAO.listarTodos();
		this.acessorios = acessorioDAO.listarTodos();
	}

	public void salvar() {
		try {
			if(this.uploadedFile != null) {
				this.carro.setFoto(this.uploadedFile.getContents());
			}
			this.cadastroCarroService.salvar(carro);
			FacesUtil.addSuccessMessage("Carro cadastrado com sucesso!!!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage() + "deu erro foi aqui");
		}

		this.limpar();
	}

	private void limpar() {
		this.carro = new Carro();
	}

	/*
	 * ADVERTÊNCIA: #{cadastroCarroBean.salvar}:=>occurred calling getter of
	 * com.algaworks.curso.jpa2.modelo.Acessorio.id
	 * 
	 * object is not an instance of declaring class
	 * 
	 * 
	 */

}
