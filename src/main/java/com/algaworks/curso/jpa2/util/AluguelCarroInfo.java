package com.algaworks.curso.jpa2.util;

import java.math.BigDecimal;

import com.algaworks.curso.jpa2.modelo.Carro;

public class AluguelCarroInfo {
	private Carro carro;
	private Long totalAlugueis;
	private BigDecimal valorMinimo;
	private BigDecimal valorMedio;
	private BigDecimal valorMaximo;
	
	public AluguelCarroInfo(Carro carro, Long totalAlugueis, Number valorMinimo, Number valorMedio, Number valorMaximo) {
		this.setCarro(carro);
		this.setTotalAlugueis(totalAlugueis);
		this.setValorMinimo(BigDecimal.valueOf(valorMinimo.doubleValue()));
		this.setValorMedio(BigDecimal.valueOf(valorMedio.doubleValue()));
		this.setValorMaximo(BigDecimal.valueOf(valorMaximo.doubleValue()));
	}
	
	public Carro getCarro() {
		return carro;
	}
	public void setCarro(Carro carro) {
		this.carro = carro;
	}
	public Long getTotalAlugueis() {
		return totalAlugueis;
	}
	public void setTotalAlugueis(Long totalAlugueis) {
		this.totalAlugueis = totalAlugueis;
	}
	public BigDecimal getValorMinimo() {
		return valorMinimo;
	}
	public void setValorMinimo(BigDecimal valorMinimo) {
		this.valorMinimo = valorMinimo;
	}
	public BigDecimal getValorMedio() {
		return valorMedio;
	}
	public void setValorMedio(BigDecimal valorMedio) {
		this.valorMedio = valorMedio;
	}
	public BigDecimal getValorMaximo() {
		return valorMaximo;
	}
	public void setValorMaximo(BigDecimal valorMaximo) {
		this.valorMaximo = valorMaximo;
	}
	
	
	

}
