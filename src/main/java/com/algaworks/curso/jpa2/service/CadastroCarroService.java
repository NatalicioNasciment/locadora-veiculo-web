package com.algaworks.curso.jpa2.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.algaworks.curso.jpa2.dao.CarroDAO;
import com.algaworks.curso.jpa2.modelo.Acessorio;
import com.algaworks.curso.jpa2.modelo.Carro;

public class CadastroCarroService implements Serializable {
	@Inject
	private CarroDAO carroDAO;

	public void salvar(Carro carro) throws NegocioException {
		if (carro == null) {
			throw new NegocioException("Carro tem valor inadequado ou deixou um dos campos vazios.");
		} else {
			this.carroDAO.salvar(carro);
		}
	}
}
