package com.algaworks.curso.jpa2.modelo;

public enum Sexo {
	MASCULINO("Masculino"), FEMININO("Feminino");

	private String nome;

	private Sexo(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

}
