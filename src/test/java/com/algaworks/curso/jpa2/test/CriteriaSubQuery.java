package com.algaworks.curso.jpa2.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.algaworks.curso.jpa2.modelo.Carro;

public class CriteriaSubQuery {
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
	public void carrosComValoresAcimaDaMedia() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Carro> criteriaQuery = builder.createQuery(Carro.class);
		Subquery<Double> subquery = criteriaQuery.subquery(Double.class);

		Root<Carro> carro = criteriaQuery.from(Carro.class);
		Root<Carro> carroSub = subquery.from(Carro.class);

		subquery.select(builder.avg(carroSub.<Double>get("valorDiaria")));
		criteriaQuery.where(builder.greaterThanOrEqualTo(carro.<Double>get("valorDiaria"), subquery));

		TypedQuery<Carro> query = em.createQuery(criteriaQuery);
		List<Carro> resultado = query.getResultList();

		for (Carro c : resultado) {
			System.out.println(c.getModeloCarro().getDescricao() + " - " + c.getValorDiaria());
		}

	}

	@After
	public void fecha() {
		this.em.close();
	}
}
