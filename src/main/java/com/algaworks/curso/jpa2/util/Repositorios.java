package com.algaworks.curso.jpa2.util;

import java.io.Serializable;

import com.algaworks.curso.jpa2.repository.Fabricante;
import com.algaworks.curso.jpa2.repository.infra.FabricantesImpl;

public class Repositorios implements Serializable{
	
	public Fabricante getFabricantes() {
		return new FabricantesImpl();
	}
}
