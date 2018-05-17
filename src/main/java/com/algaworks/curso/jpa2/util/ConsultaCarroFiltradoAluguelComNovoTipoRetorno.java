package com.algaworks.curso.jpa2.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConsultaCarroFiltradoAluguelComNovoTipoRetorno {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("locadoraVeiculoPU");
		EntityManager em = emf.createEntityManager();
//		agrupar as consultas por carro..saber 
//		quantos alugueis cada carro tem, o valor medio, maximo, minino, filtrar por carro que tem mais de um aluguel.
		
		String jpql="select NEW com.algaworks.curso.jpa2.util.AluguelCarroInfo(c, count(a) , min(a.valorTotal), avg(a.valorTotal), max(a.valorTotal)) from carro c JOIN c.alugueis a  group by c having count(a) >1" ;
		
		List<AluguelCarroInfo> carrosComDataAlugueis = em.createQuery(jpql).getResultList(); 
		System.out.println("---------------------------------------------------------------------------------------------");
		for(AluguelCarroInfo aluguelCarroInfo : carrosComDataAlugueis) {
			System.out.println("Carro :" + aluguelCarroInfo.getCarro().getModeloCarro().getDescricao());
//			System.out.println("Quantidade: "+ aluguelCarroInfo.getTotalAlugueis());
			System.out.println("Valor Minino: "+ aluguelCarroInfo.getValorMinimo());
			System.out.println("Valor Medio: "+ aluguelCarroInfo.getValorMedio());
			System.out.println("Valor Maximo: "+ aluguelCarroInfo.getValorMaximo());
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