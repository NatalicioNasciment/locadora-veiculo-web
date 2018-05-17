package com.algaworks.curso.jpa2.modelo;

public enum Categoria {
	
	HATCH_COMPACTO("Compacto"),
	HATCH_MEDIO("Medio"),
	SEDAN_COMPACTO("Sedan Compacto"),
	SEDAN_MEDIO("Sedan Medio"),
	SEDAN_GRANDE("Sedan Grande"),
	MINIVAN("Minivan"),
	ESPORTIVO("Esportivo"),
	UTILITARIO_COMERCIAL("Utilitario Comercial");
	
	private String descricao;
	
	Categoria(String descricao) {
		this.descricao = descricao;
	}
	
	public String getCategoria() {
		return this.descricao;
	}
}
