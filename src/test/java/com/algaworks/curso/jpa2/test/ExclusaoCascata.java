package com.algaworks.curso.jpa2.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.algaworks.curso.jpa2.modelo.Carro;

public class ExclusaoCascata {
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

//	@Test
	public void excluirCarro() {
		Carro carro = em.find(Carro.class, 3);
		
		em.getTransaction().begin();
		em.remove(carro);
		em.getTransaction().commit();
	}
	
	@Test
	public void excluirObjetoOrfao() {
		Carro carro = em.find(Carro.class, 15);
		
		em.getTransaction().begin();
		carro.getAlugueis().remove(0);
		em.getTransaction().commit();
	}

	@After
	public void fecha() {
		this.em.close();
	}
}
