package com.algaworks.curso.jpa2.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity(name="motorista")
@DiscriminatorValue("2")
public class Motorista extends Pessoa implements Serializable{
	private String numeroCNH;

	@Column(name="numero_cnh")
	public String getNumeroCNH() {
		return numeroCNH;
	}

	public void setNumeroCNH(String numeroCNH) {
		this.numeroCNH = numeroCNH;
	}
	
	
}
