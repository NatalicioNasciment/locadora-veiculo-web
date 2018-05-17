package com.algaworks.curso.jpa2.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.algaworks.curso.jpa2.modelo.ModeloCarro;

public class ExemploCriteria {
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

	// @Test
	public void buscaModeloFabricanteComplexo() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);

		Root<ModeloCarro> modeloFabricante = criteriaQuery.from(ModeloCarro.class);

		criteriaQuery.multiselect(modeloFabricante.get("descricao"), modeloFabricante.get("fabricante").get("nome"));

		TypedQuery<Object[]> query = em.createQuery(criteriaQuery);
		List<Object[]> resultado = query.getResultList();

		for (Object[] mc : resultado) {
			System.out.println(mc[0] + " - " + mc[1]);
		}
	}

	@Test
	public void buscaModeloFabricanteComTupla() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteriaQuery = builder.createTupleQuery();
		Root<ModeloCarro> modeloCarro = criteriaQuery.from(ModeloCarro.class);

		criteriaQuery.multiselect(modeloCarro.get("descricao").alias("modeloCarroDescricao"),
				modeloCarro.get("fabricante").get("nome").alias("fabricanteNome"));

		Order order = builder.asc(modeloCarro.get("descricao"));
		criteriaQuery.orderBy(order);

		TypedQuery<Tuple> query = em.createQuery(criteriaQuery);
		List<Tuple> resultado = query.getResultList();

		for (Tuple tupla : resultado) {
			System.out.println(tupla.get("modeloCarroDescricao") + " ->" + tupla.get("fabricanteNome"));
		}
	}

	// @Test
	public void buscaModeloFabricanteComConstrutor() {
		/*
		
		*/

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<InfoModeloCarroFabricante> criteriaQuery = builder.createQuery(InfoModeloCarroFabricante.class);

		Root<ModeloCarro> modeloCarro = criteriaQuery.from(ModeloCarro.class);

		criteriaQuery
				.select(builder.construct(InfoModeloCarroFabricante.class, modeloCarro, modeloCarro.get("fabricante")));
		TypedQuery<InfoModeloCarroFabricante> query = em.createQuery(criteriaQuery);
		List<InfoModeloCarroFabricante> resultado = query.getResultList();

		System.out.println();
		System.out.println("-----------------------------------------------------------");
		for (InfoModeloCarroFabricante mc : resultado) {
			System.out.println(mc.getModeloCarro().getDescricao().toUpperCase() + " - "
					+ mc.getFabricante().getNome().toUpperCase());
		}

		System.out.println("-----------------------------------------------------------");
	}
	

	@After
	public void fecha() {
		this.em.close();
	}
}
