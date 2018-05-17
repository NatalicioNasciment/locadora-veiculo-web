package com.algaworks.curso.jpa2.modelo;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "funcionario")
@DiscriminatorValue("1")
public class Funcionario extends Pessoa implements Serializable {

	private String matricula;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

}
