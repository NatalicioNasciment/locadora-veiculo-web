package com.algaworks.curso.jpa2.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.algaworks.curso.jpa2.modelo.Acessorio;
import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.modelo.Categ;
import com.algaworks.curso.jpa2.modelo.Categoria;
import com.algaworks.curso.jpa2.modelo.ModeloCarro;

public class ExemploCascata {

	private static EntityManagerFactory emf;
	private EntityManager em;

	@BeforeClass
	public static void init() {
		emf = Persistence.createEntityManagerFactory("locadoraVeiculoPU");
	}

	@Before
	public void setUp() {
		this.em = emf.createEntityManager();
	}

	@Test
	public void adicionarCarro() {
		List<Acessorio> acessorios = new ArrayList<>();
//		acessorios.add(new Acessorio("Cadeira Macia"));
//		acessorios.add(new Acessorio("Arcondicionado"));
//		acessorios.add(new Acessorio("Som de alta qualidade"));
//		acessorios.add(new Acessorio("Porta automatica"));
		
		ModeloCarro modeloCarro = new ModeloCarro();
		modeloCarro.setDescricao("Ferrari");
		modeloCarro.setCategoria(Categoria.ESPORTIVO);

		Carro carro = new Carro();
		carro.setCor("Verde");
		carro.setPlaca("CC-1818");
		carro.setModeloCarro(modeloCarro);
		carro.setAcessorios(acessorios);

		this.em.getTransaction().begin();
		em.persist(carro);
		this.em.getTransaction().commit();
	}

	@After
	public void fecha() {
		this.em.close();
	}

}
