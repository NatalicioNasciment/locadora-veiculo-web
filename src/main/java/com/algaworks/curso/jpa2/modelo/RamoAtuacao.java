package com.algaworks.curso.jpa2.modelo;

public enum RamoAtuacao {
	TECNOLOGICO("Tecnologia"),
	ARTESANAL("Artesanal"),
	PECUARIA("Pecuaria"),
	ARTISTICO("Artistico"),
	MANUAL("Manual"),
	SEM_FINS_LUCRATIVO("Sem fins lucrativo");
	
	private String descricao;
	
	private RamoAtuacao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}

	

}
