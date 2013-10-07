package br.com.consultweb.domain.servico.resumo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class MovimentacoesProtocolosDia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2482461753197182667L;
	
	private Integer restricoes;

	private Integer consultas;

	private Integer exclusoes;

	public MovimentacoesProtocolosDia(int restricoes, int consultas, int exclusoes) {
		this.setRestricoes(restricoes);
		this.setConsultas(consultas);
		this.setExclusoes(exclusoes);
	}

	public Integer getRestricoes() {
		return restricoes;
	}

	public void setRestricoes(Integer restricoes) {
		this.restricoes = restricoes;
	}

	public Integer getConsultas() {
		return consultas;
	}

	public void setConsultas(Integer consultas) {
		this.consultas = consultas;
	}

	public Integer getExclusoes() {
		return exclusoes;
	}

	public void setExclusoes(Integer exclusoes) {
		this.exclusoes = exclusoes;
	}

	public Integer getTotalMovimentacoes() {
		return (this.getRestricoes() + this.getConsultas() + this
				.getExclusoes());
	}

	public BigDecimal getPercConsultas() {
		
		BigDecimal percConsultas = BigDecimal.ZERO;
		percConsultas.setScale(2, RoundingMode.HALF_UP);
		BigDecimal big100 = new BigDecimal(100);
		
		if(this.getTotalMovimentacoes() == 0) {
			return percConsultas;
		} else {
			BigDecimal conDecimal = new BigDecimal(this.getConsultas());
			BigDecimal totalDecimal = new BigDecimal(this.getTotalMovimentacoes());
			percConsultas = conDecimal.divide(totalDecimal);
			percConsultas = percConsultas.multiply(big100);
		}
		
		return percConsultas;
	}
	
	public BigDecimal getPercRestricoes() {

		BigDecimal percRestricoes = BigDecimal.ZERO;
		percRestricoes.setScale(2, RoundingMode.HALF_UP);
		BigDecimal big100 = new BigDecimal(100);

		if(this.getTotalMovimentacoes() == 0) {
			return percRestricoes;
		} else {
			BigDecimal resDecimal = new BigDecimal(this.getRestricoes());
			BigDecimal totalDecimal = new BigDecimal(this.getTotalMovimentacoes());
			percRestricoes = resDecimal.divide(totalDecimal);
			percRestricoes = percRestricoes.multiply(big100);
		}
		
		return percRestricoes;
		
	}
	
	public BigDecimal getPercExclusoes() {
		
		BigDecimal percExclusoes = BigDecimal.ZERO;
		percExclusoes.setScale(2, RoundingMode.HALF_UP);
		BigDecimal big100 = new BigDecimal(100);
		
		if(this.getTotalMovimentacoes() == 0) {
			return percExclusoes;
		} else {
			BigDecimal excDecimal = new BigDecimal(this.getExclusoes());
			BigDecimal totalDecimal = new BigDecimal(this.getTotalMovimentacoes());
			percExclusoes = excDecimal.divide(totalDecimal);
			percExclusoes = percExclusoes.multiply(big100);
		}
		
		return percExclusoes;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "MovimentacoesProtocolosDia [Restricoes: " + restricoes + "; Consultas: " + consultas + "; Exclusoes: " + exclusoes + "]"; 
	}
	
}
