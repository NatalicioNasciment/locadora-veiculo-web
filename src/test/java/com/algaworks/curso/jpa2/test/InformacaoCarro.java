package com.algaworks.curso.jpa2.test;

import java.math.BigDecimal;

import com.algaworks.curso.jpa2.modelo.ModeloCarro;

public class InformacaoCarro {
	private ModeloCarro modeloCarro;
	private BigDecimal valorDiaria;

	public InformacaoCarro(ModeloCarro modeloCarro, BigDecimal valorDiaria) {
		this.setModeloCarro(modeloCarro);
		this.setValorDiaria(valorDiaria);
	}

	public ModeloCarro getModeloCarro() {
		return modeloCarro;
	}

	public void setModeloCarro(ModeloCarro modeloCarro) {
		this.modeloCarro = modeloCarro;
	}

	public BigDecimal getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(BigDecimal valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

}
