package com.algaworks.curso.jpa2.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConsultaModeloFiltrandoFabricanteECategoria {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("locadoraVeiculoPU");
		EntityManager em = emf.createEntityManager();

		List<String> pesquisaModelosPeloFabricante = em
				.createQuery("select m.descricao from modelo_carro m where m.fabricante.nome = 'honda' and m.categoria in('HATCH_COMPACTO','UTILITARIO_COMERCIAL')", String.class)
				.getResultList();
		for (String c : pesquisaModelosPeloFabricante) {
			System.out.println(c);
		}

		em.close();
	}

}
