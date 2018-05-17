package com.algaworks.curso.jpa2.util;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.modelo.Empresa;
import com.algaworks.curso.jpa2.util.jpa.Transactional;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

public class EmpresaDAO implements Serializable{
@Inject
private EntityManager em;
	@Transactional
	public void salvar(Empresa empresa) {
	try {
			em.persist(empresa);
	}catch (Exception e) {
		FacesUtil.addErrorMessage(e.getMessage());
	}
	
	}

}
