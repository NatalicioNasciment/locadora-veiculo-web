package com.algaworks.curso.jpa2.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConsultaCarroFiltradoComProjecao {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("locadoraVeiculoPU");
		EntityManager em = emf.createEntityManager();

//		String jqpl = "select c.valorDiaria, c.modeloCarro.fabricante.nome from carro c where c.modeloCarro.fabricante.nome ='honda'";
		 String jqpl = "select a.nome from carro c JOIN c.acessorios a where c.modeloCarro.fabricante.nome = 'Volkesvagen'";
		@SuppressWarnings("unchecked")
		List<String> lista = em.createQuery(jqpl).getResultList();
		
		for (String acessorio : lista) {
			System.out.println(acessorio);
		}
		em.close();
	}

}