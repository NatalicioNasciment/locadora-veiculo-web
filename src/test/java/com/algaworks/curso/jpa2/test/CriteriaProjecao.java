package com.algaworks.curso.jpa2.test;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.algaworks.curso.jpa2.modelo.Aluguel;
import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.modelo.ModeloCarro;

public class CriteriaProjecao {

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

	public void retornarPlacas() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<String> criteriaQuery = builder.createQuery(String.class);
		Root<Carro> carro = criteriaQuery.from(Carro.class);
		criteriaQuery.select(carro.<String>get("placa"));

		TypedQuery<String> query = em.createQuery(criteriaQuery);
		List<String> placas = query.getResultList();

		for (String placa : placas) {
			System.out.println(placa);
		}

	}

	// @Test
	public void funcoesAgregacaoMedia() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Double> criteriaQuery = builder.createQuery(Double.class);
		Root<Aluguel> aluguel = criteriaQuery.from(Aluguel.class);

		criteriaQuery.select(builder.avg(aluguel.<Double>get("valorTotal")));

		TypedQuery<Double> query = em.createQuery(criteriaQuery);
		Double mediaAlugueis = query.getSingleResult();

		System.out.println("MEDIA: " + mediaAlugueis);
	}

	// @Test
	public void funcoesAgregacaoSoma() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<BigDecimal> criteriaQuery = builder.createQuery(BigDecimal.class);
		Root<Aluguel> aluguel = criteriaQuery.from(Aluguel.class);

		criteriaQuery.select(builder.sum(aluguel.<BigDecimal>get("valorTotal")));

		TypedQuery<BigDecimal> query = em.createQuery(criteriaQuery);
		BigDecimal totalGeral = query.getSingleResult();

		System.out.println("SOMA : " + totalGeral);

	}

	// @Test
	public void funcoesAgregacaoMax() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<BigDecimal> criteriaQuery = builder.createQuery(BigDecimal.class);
		Root<Aluguel> aluguel = criteriaQuery.from(Aluguel.class);

		criteriaQuery.select(builder.max(aluguel.<BigDecimal>get("valorTotal")));
		TypedQuery<BigDecimal> query = em.createQuery(criteriaQuery);

		BigDecimal maiorAluguel = query.getSingleResult();

		System.out.println(" MAXIMO : " + maiorAluguel);

	}

	// @Test
	public void funcoesAgregacaoMin() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<BigDecimal> criteriaQuery = builder.createQuery(BigDecimal.class);

		Root<Aluguel> aluguel = criteriaQuery.from(Aluguel.class);
		criteriaQuery.select(builder.min(aluguel.<BigDecimal>get("valorTotal")));

		TypedQuery<BigDecimal> query = em.createQuery(criteriaQuery);

		BigDecimal valorMinino = query.getSingleResult();
		System.out.println("MINIMO: " + valorMinino);
	}

	// @Test
	public void resultadosComplexo() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
		Root<Carro> carro = criteriaQuery.from(Carro.class);

		criteriaQuery.multiselect(carro.get("modeloCarro").get("descricao"), carro.get("valorDiaria"));

		TypedQuery<Object[]> query = em.createQuery(criteriaQuery);

		List<Object[]> resultado = query.getResultList();

		for (Object[] c : resultado) {
			System.out.println(c[0] + " - " + c[1]);
		}

	}

	// @Test
	public void resultadosComTupla() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteriaQuery = builder.createTupleQuery();
		Root<Carro> carro = criteriaQuery.from(Carro.class);

		criteriaQuery.multiselect(carro.get("modeloCarro").get("descricao").alias("modelo"),
				carro.get("valorDiaria").alias("carroDiaria"));

		TypedQuery<Tuple> query = em.createQuery(criteriaQuery);
		List<Tuple> resultado = query.getResultList();

		for (Tuple tupla : resultado) {
			System.out.println(tupla.get("modelo") + " - " + tupla.get("carroDiaria"));
		}

	}

	// @Test
	public void resultadoContrutores() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<InformacaoCarro> criteriaQuery = builder.createQuery(InformacaoCarro.class);
		Root<Carro> carro = criteriaQuery.from(Carro.class);

		criteriaQuery
				.select(builder.construct(InformacaoCarro.class, carro.get("modeloCarro"), carro.get("valorDiaria")));
		TypedQuery<InformacaoCarro> query = em.createQuery(criteriaQuery);

		List<InformacaoCarro> resultado = query.getResultList();

		for (InformacaoCarro carroInf : resultado) {
			System.out.println(carroInf.getModeloCarro().getDescricao() + " - " + carroInf.getValorDiaria());
		}

	}

	// @Test
	public void exemploFuncao() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Carro> criteriaQuery = builder.createQuery(Carro.class);

		Root<Carro> carro = criteriaQuery.from(Carro.class);

		Predicate predicate = builder.equal(builder.upper(carro.<String>get("cor")), "vermelho".toUpperCase());

		criteriaQuery.select(carro);
		criteriaQuery.where(predicate);

		TypedQuery<Carro> query = em.createQuery(criteriaQuery);

		List<Carro> resultado = query.getResultList();

		for (Carro c : resultado) {
			System.out.println(c.getModeloCarro().getDescricao() + " - " + c.getCor());
		}

	}

	@Test
	public void testeOrdenacao() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Carro> criteriaQuery = builder.createQuery(Carro.class);

		Root<Carro> carro = criteriaQuery.from(Carro.class);
		criteriaQuery.select(carro);

		Join<Carro, ModeloCarro> carroEModeloCarro = (Join) carro.fetch("modeloCarro");

		Order order = builder.asc(carro.get("valorDiaria"));
		criteriaQuery.orderBy(order);

		TypedQuery<Carro> query = em.createQuery(criteriaQuery);
		List<Carro> resultado = query.getResultList();

		System.out.println();
		System.out.println("---------------------------------------------------------------");
		for (Carro c : resultado) {
			System.out.println(c.getModeloCarro().getDescricao() + " - " + c.getValorDiaria());
		}
		System.out.println("---------------------------------------------------------------");
	}

	@After
	public void fecha() {
		this.em.close();
	}

}
