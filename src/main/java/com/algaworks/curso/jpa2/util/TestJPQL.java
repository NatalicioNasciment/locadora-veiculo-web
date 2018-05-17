package com.algaworks.curso.jpa2.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestJPQL {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("locadoraVeiculoPU");
		EntityManager em = emf.createEntityManager();
		List<String> pesquisaModelosPeloFabricante = new ArrayList<>();
		pesquisaModelosPeloFabricante = em.createQuery("select m.fabricante.nome from modelo_carro m", String.class).getResultList();
		for (String c : pesquisaModelosPeloFabricante) {
			System.out.println(c);
		}
		
		em.close();
	}

}
