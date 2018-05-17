package com.algaworks.curso.jpa2.repository;

import java.util.List;

import com.algaworks.curso.jpa2.service.NegocioException;

public interface Fabricante {
	
	public void salvar(com.algaworks.curso.jpa2.modelo.Fabricante fabricante) throws NegocioException;
	public void remover(com.algaworks.curso.jpa2.modelo.Fabricante fabricante);
	public List<com.algaworks.curso.jpa2.modelo.Fabricante> todos();
	public com.algaworks.curso.jpa2.modelo.Fabricante buscarPorCodigo(Integer codigo);

}
