package com.algaworks.curso.jpa2.test;

import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.modelo.ModeloCarro;

public class InfoModeloCarroFabricante {
	
	private ModeloCarro modeloCarro;
	private Fabricante fabricante;
	
	public InfoModeloCarroFabricante(ModeloCarro modeloCarro, Fabricante fabricante) {
		this.setModeloCarro(modeloCarro);
		this.setFabricante(fabricante);
	}
	
	public ModeloCarro getModeloCarro() {
		return modeloCarro;
	}
	public void setModeloCarro(ModeloCarro modeloCarro) {
		this.modeloCarro = modeloCarro;
	}
	public Fabricante getFabricante() {
		return fabricante;
	}
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	
	

}
