package br.com.consultweb.domain.servico;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "protocolo")
@SequenceGenerator(name = "identificador", sequenceName = "identificador", allocationSize = 1)
public class Protocolo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6580840405695035584L;
	
	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "numero", nullable = false, insertable = true, updatable = true, length = 30)
	private String numero;
	
	@Column(name = "datageracao", nullable = false, insertable = true, updatable = false)
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataGeracao;
	
	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_restricao", nullable = false, insertable = true, updatable = false)
	private Restricao restricao;

	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_faturamento", nullable = false, insertable = true, updatable = false)
	private Faturamento faturamento;

	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_consulta", nullable = false, insertable = true, updatable = false)
	private Consulta consulta;

	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_exclusao", nullable = false, insertable = true, updatable = false)
	private Exclusao exclusao;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public Restricao getRestricao() {
		return restricao;
	}

	public void setRestricao(Restricao restricao) {
		this.restricao = restricao;
	}

	public Faturamento getFaturamento() {
		return faturamento;
	}

	public void setFaturamento(Faturamento faturamento) {
		this.faturamento = faturamento;
	}
	
	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	
	public Exclusao getExclusao() {
		return exclusao;
	}

	public void setExclusao(Exclusao exclusao) {
		this.exclusao = exclusao;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof Protocolo) {
			final Protocolo c = (Protocolo) arg0;
			return new EqualsBuilder().append(this.getId(), c.getId())
					.isEquals();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getId()).toHashCode();
	}
	
}
