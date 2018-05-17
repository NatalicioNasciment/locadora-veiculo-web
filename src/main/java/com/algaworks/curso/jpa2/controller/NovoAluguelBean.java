package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.CarroDAO;
import com.algaworks.curso.jpa2.modelo.Aluguel;
import com.algaworks.curso.jpa2.modelo.ApoliceSeguro;
import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.modelo.Motorista;
import com.algaworks.curso.jpa2.service.CadastroAluguelService;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class NovoAluguelBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Carro> carros;
	private Aluguel aluguel;
	@Inject
	private CarroDAO carroDAO;
	@Inject
	private CadastroAluguelService cadastroAluguelService;
	@Inject
	private MotoristaDAO motoristaDAO;
	private List<Motorista> motoristas = new ArrayList<>();

	public List<Carro> getCarros() {
		return carros;
	}

	public Aluguel getAluguel() {
		return aluguel;
	}

	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}

	public List<Motorista> getMotoristas() {
		return motoristas;
	}

	@PostConstruct
	public void init() {
		this.limpar();
		this.aluguel.setApoliceSeguro(new ApoliceSeguro());
		this.carros = this.carroDAO.listarTodos();
		this.motoristas = motoristaDAO.listarTodos();
	}

	public void salvar() {
		if (this.aluguel == null) {
			FacesUtil.addErrorMessage("Não foi possivel cadastrar o aluguel!!!");
		} else {
			this.cadastroAluguelService.salvar(aluguel);
			FacesUtil.addSuccessMessage("Aluguel salvo com sucesso!!!");
			this.limpar();
		}
	}

	private void limpar() {
		this.aluguel = new Aluguel();
	}

}