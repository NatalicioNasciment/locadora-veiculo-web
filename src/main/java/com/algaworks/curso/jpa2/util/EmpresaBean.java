package com.algaworks.curso.jpa2.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.modelo.Empresa;
import com.algaworks.curso.jpa2.modelo.RamoAtuacao;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class EmpresaBean implements Serializable {
	private Empresa empresa;
	private List<RamoAtuacao> ramos;
	@Inject
	private EmpresaDAO empresaDAO;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<RamoAtuacao> getRamos() {
		return ramos;
	}

	@PostConstruct
	public void init() {
		this.limpar();
		ramos = Arrays.asList(RamoAtuacao.values());
	}
	
	
	public void  salvar() {
		
		this.empresaDAO.salvar(empresa);
		FacesUtil.addSuccessMessage("Empresa cadastrada com sucesso!!!");
		this.limpar();
	
	}

	private void limpar() {
		empresa = new Empresa();
	}

}
