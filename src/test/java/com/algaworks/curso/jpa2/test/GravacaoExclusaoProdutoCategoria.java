package com.algaworks.curso.jpa2.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.algaworks.curso.jpa2.modelo.Categ;
import com.algaworks.curso.jpa2.modelo.Produto;

public class GravacaoExclusaoProdutoCategoria {
	private static EntityManagerFactory emf;
	private static EntityManager em;

	@BeforeClass
	public static void configPU() {
		emf = Persistence.createEntityManagerFactory("locadoraVeiculoPU");
	}

	@Before
	public void configEntityManager() {
		em = emf.createEntityManager();
	}

	// @Test
	public void inclusaoEmCascata() {
		Categ categoria = em.find(Categ.class, 4L);
		// Categ categoria= new Categ();
		// categoria.setNome("Limpeza");
		categoria.setNome(categoria.toString());

		Produto produto = new Produto();
		produto.setNome("Desodorante");
		produto.setCategoria(categoria);

		em.getTransaction().begin();
		em.persist(produto);
		em.getTransaction().commit();

	}

	// @Test
	public void exclusaoEmCascata() {
		Produto produto = em.find(Produto.class, 4L);
		em.getTransaction().begin();
		em.remove(produto);
		em.getTransaction().commit();

	}

	@Test
	public void exclusaoEmCascataComObjetoOrfao() {
		Categ categ = em.find(Categ.class, 5L);

		em.getTransaction().begin();
		categ.getProdutos().remove(0);
		em.getTransaction().commit();

	}

	@After
	public void close() {
		em.close();
	}

}
