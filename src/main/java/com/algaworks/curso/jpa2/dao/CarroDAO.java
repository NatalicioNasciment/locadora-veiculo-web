package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class CarroDAO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager em;

	@Transactional
	public void salvar(Carro carro) {
		em.merge(carro);
	}

	@Transactional
	public void excluir(Carro carro) {
		carro = em.find(Carro.class, carro.getId());
		em.remove(carro);
		em.flush();
	}

	public Carro buscarPorCodigo(Integer codigo) {
		return em.find(Carro.class, codigo);
	}

	@SuppressWarnings("unchecked")
	public List<Carro> listarTodos() {
		return em.createNamedQuery("Carro.listarTodos").getResultList();
	}

	public Carro buscarCarroComAcessorios(Integer id) {
		try {
			return (Carro) em.createNamedQuery("Carro.buscarCarroComAcessorios").setParameter("codigo", id)
					.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Carro> buscarComPaginacao(int first, int pageSize) {
		return em.createNamedQuery("Carro.listarTodos").setFirstResult(first).setMaxResults(pageSize).getResultList();
	}

	public Long quantidadeDeCarros() {
		return em.createQuery("select count(c) from carro c", Long.class).getSingleResult();
	}

	public List<Carro> pesquisarPeloCodigo(Integer id) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Carro> criteriaQuery = builder.createQuery(Carro.class);
		Root<Carro> c = criteriaQuery.from(Carro.class);
		criteriaQuery.select(c);

		List<Predicate> predicates = new ArrayList<>();

		if (id != null) {
			ParameterExpression<Integer> codigoExpression = builder.parameter(Integer.class, "codigo");
			predicates.add(builder.equal(c.get("id"), codigoExpression));
		}

		criteriaQuery.where(predicates.toArray(new Predicate[0]));

		TypedQuery<Carro> query = em.createQuery(criteriaQuery);

		if (id != null) {
			query.setParameter("codigo", id);
		}

		return query.getResultList();
	}
}
