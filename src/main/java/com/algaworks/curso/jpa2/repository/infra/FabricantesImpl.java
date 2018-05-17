package com.algaworks.curso.jpa2.repository.infra;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.repository.Fabricante;
import com.algaworks.curso.jpa2.service.NegocioException;

public class FabricantesImpl implements Fabricante {
	@Inject
	private EntityManager em;

	@Override
	public void salvar(com.algaworks.curso.jpa2.modelo.Fabricante fabricante) throws NegocioException {
		if (fabricante.getNome() == null || fabricante.getNome().trim().equals("")) {
			throw new NegocioException("O nome do fabricante é obrigatorio!!!");
		} else {
			em.getTransaction().begin();
			em.persist(fabricante);
			em.getTransaction().commit();
		}
	}

	@Override
	public void remover(com.algaworks.curso.jpa2.modelo.Fabricante fabricante) {

	}

	@Override
	public List<com.algaworks.curso.jpa2.modelo.Fabricante> todos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.algaworks.curso.jpa2.modelo.Fabricante buscarPorCodigo(Integer codigo) {
		// TODO Auto-generated method stub
		return null;
	}

}
