package com.algaworks.curso.jpa2.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.algaworks.curso.jpa2.modelo.Aluguel;
import com.algaworks.curso.jpa2.modelo.Carro;

public class ExercicioComCriteria {
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

	@Test
	public void motoristasQueMaisAlugaram() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);

		Root<Aluguel> aluguel = criteriaQuery.from(Aluguel.class);
		Root<Carro> carro = criteriaQuery.from(Carro.class);

		criteriaQuery.multiselect(aluguel.get("motorista").get("nome"), builder.count(aluguel.get("carro")));
		criteriaQuery.where(builder.equal(aluguel.get("carro").get("id"), carro.get("id")));
		criteriaQuery.groupBy(aluguel.get("motorista").get("id"));

		Order order = builder.desc(builder.count(aluguel.get("carro").get("id")));
		criteriaQuery.orderBy(order);

		TypedQuery<Object[]> query = em.createQuery(criteriaQuery);
		List<Object[]> resultado = query.getResultList();

		for (Object[] test : resultado) {
			System.out.println(test[0] + " -" + test[1]);
		}

	}

	@After
	public void fecha() {
		this.em.close();
	}
}
