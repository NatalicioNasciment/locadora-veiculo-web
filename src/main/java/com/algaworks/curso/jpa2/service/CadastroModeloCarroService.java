package com.algaworks.curso.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.curso.jpa2.dao.ModeloCarroDAO;
import com.algaworks.curso.jpa2.modelo.ModeloCarro;

public class CadastroModeloCarroService implements Serializable {
	@Inject
	ModeloCarroDAO modeloCarroDAO;

	public void salvar(ModeloCarro modeloCarro) throws NegocioException {
		if (modeloCarro.getDescricao() == null || modeloCarro.getDescricao().trim().equals("")) {
			throw new NegocioException("O campo  descrição é obrigatório");
		}
		if (modeloCarro.getFabricante() == null) {
			throw new NegocioException("O campo fabricante  é obrigatório");
		}

		modeloCarroDAO.salvar(modeloCarro);

	}
	
}
