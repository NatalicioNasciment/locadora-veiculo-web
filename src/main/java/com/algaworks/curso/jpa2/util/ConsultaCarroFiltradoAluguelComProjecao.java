package com.algaworks.curso.jpa2.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.algaworks.curso.jpa2.modelo.Carro;

public class ConsultaCarroFiltradoAluguelComProjecao {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("locadoraVeiculoPU");
		EntityManager em = emf.createEntityManager();
//		agrupar as consultas por carro..saber 
//		quantos alugueis cada carro tem, o valor medio, maximo, minino, filtrar por carro que tem mais de um aluguel.
		
		String jpql="select c, count(a),avg(a.valorTotal), max(a.valorTotal), min(a.valorTotal)"
				+ " from carro c JOIN c.alugueis a group by c having count(a) > 1";
		
		List<Object[]> carrosComDataAlugueis = em.createQuery(jpql).getResultList(); 
		
		for(Object obj[] : carrosComDataAlugueis) {
			System.out.println("Modelo Carro: "+ ((Carro)obj[0]).getModeloCarro().getDescricao());
			System.out.println("Quantidade de alugueis :" + obj[1]);
			System.out.println("Valor R$ por  alugueis :" + obj[2]);
			System.out.println("Valor maximo R$ :" + obj[3]	);
			System.out.println("Valor minimo R$ :" + obj[4]);
			System.out.println();
			
		}
}
}


/*
 * 
 * String jpql="select c.modeloCarro.descricao, a.motorista.nome, a.dataEntrega, a.dataDevolucao from carro c JOIN c.alugueis a where c.modeloCarro.fabricante.nome ='honda'";
		List<Object[]> carrosComDataAlugueis = em.createQuery(jpql).getResultList(); 
		
		for(Object obj[] : carrosComDataAlugueis  ) {
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("MODELO CARRO : "+obj[0]+ " | MOTORISTA :  "+obj[1] + " | DATA ENTREGA : " + obj[2] + " | DATA DEVOLUÇÃO" + obj[3]);
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
		}
 * 
 * */