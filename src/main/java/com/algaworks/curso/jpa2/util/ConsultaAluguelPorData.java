package com.algaworks.curso.jpa2.util;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TemporalType;

public class ConsultaAluguelPorData {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("locadoraVeiculoPU");
		EntityManager em = emf.createEntityManager();
		
		String jpql = "select count(a) from aluguel a where a.dataPedido = :dataPedido";
		
		Calendar dataPedido = Calendar.getInstance();
		dataPedido.set(2018, 1, 9, 7, 0);
		
		
		
		Long quantidade = em.createQuery(jpql, Long.class)
				.setParameter("dataPedido",dataPedido,TemporalType.DATE)
				.getSingleResult();
		
		System.out.println(quantidade);
		
	}
}
