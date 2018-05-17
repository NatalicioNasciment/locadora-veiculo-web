package com.algaworks.curso.jpa2.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConsultaModeloFiltrandoFabricante {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("locadoraVeiculoPU");
		EntityManager em = emf.createEntityManager();
		
		String modelo="honda";

		List<String> pesquisaModelosPeloFabricante = em
				.createQuery("select m.descricao from modelo_carro m where m.fabricante.nome = :modelo", String.class).setParameter("modelo", modelo)
				.getResultList();
		for (String c : pesquisaModelosPeloFabricante) {
			System.out.println(c);
		}

		em.close();
	}

}
