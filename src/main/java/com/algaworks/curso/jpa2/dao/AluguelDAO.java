package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.algaworks.curso.jpa2.modelo.Aluguel;
import com.algaworks.curso.jpa2.modelo.ModeloCarro;
import com.algaworks.curso.jpa2.util.LibraryUtil;

public class AluguelDAO<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public void salvar(Aluguel aluguel) {
		manager.merge(aluguel);
	}

	public List<Aluguel> buscarPorDataDeEntregaEModeloCarro(Date dataEntrega, ModeloCarro modeloCarro) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Aluguel> criteriaQuery = builder.createQuery(Aluguel.class);

		Root<Aluguel> a = criteriaQuery.from(Aluguel.class);
		criteriaQuery.select(a);

		List<Predicate> predicates = new ArrayList<>();

		if (dataEntrega != null) {
			ParameterExpression<Date> dataEntregaInicial = builder.parameter(Date.class, "dataEntregaInicial");
			ParameterExpression<Date> dataEntregaFinal = builder.parameter(Date.class, "dataEntregaFinal");
			predicates.add(builder.between(a.<Date>get("dataEntrega"), dataEntregaInicial, dataEntregaFinal));
		}

		if (modeloCarro != null) {
			ParameterExpression<ModeloCarro> modeloExpression = builder.parameter(ModeloCarro.class, "modelo");
			predicates.add(builder.equal(a.get("carro").get("modeloCarro"), modeloExpression));
		}

		criteriaQuery.where(predicates.toArray(new Predicate[0]));
		TypedQuery<Aluguel> query = manager.createQuery(criteriaQuery);

		if (dataEntrega != null) {

			Calendar dataEntregaInicial = LibraryUtil.formatadorData(dataEntrega, 00, 00, 00);
			Calendar dataEntregaFinal = LibraryUtil.formatadorData(dataEntrega, 23, 59, 59);

			query.setParameter("dataEntregaInicial", dataEntregaInicial.getTime());
			query.setParameter("dataEntregaFinal", dataEntregaFinal.getTime());

		}

		if (modeloCarro != null) {
			query.setParameter("modelo", modeloCarro);
		}

		return query.getResultList();

	}
}