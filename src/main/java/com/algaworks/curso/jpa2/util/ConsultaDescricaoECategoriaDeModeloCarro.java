package com.algaworks.curso.jpa2.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConsultaDescricaoECategoriaDeModeloCarro {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("locadoraVeiculoPU");
		EntityManager em = emf.createEntityManager();
		
		String jqpl ="select mc.descricao, mc.categoria.descricao from modelo_carro mc";
		List<Object[]> resultList = em.createQuery(jqpl).getResultList();
		
		for (Object o[]: resultList) {
			System.out.println("| DESCRICAO: " + o[0]+ " |  CATEGORIA: "+ o[1]+ " |");
		}

		em.close();
	}

}
