package br.com.consultweb.domain.servico.consulta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "consultaocorrencia")
@SequenceGenerator(name = "identificador", sequenceName = "identificador", allocationSize = 1)
public class ConsultaOcorrencia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8249704875520857454L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "ocorrencia", nullable = false, insertable = true, updatable = false)
	private String ocorrencia;

	@Column(name = "quantidade", nullable = false, insertable = true, updatable = false)
	private Integer quantidade;

	@Column(name = "dataultimaocorrencia", nullable = false, insertable = true, updatable = false)
	@Temporal(value = TemporalType.DATE)
	private Date dataUltimaOcorrencia;

	@Column(name = "valorultimaocorrencia", nullable = false, insertable = true, updatable = false)
	private BigDecimal valorUltimaOcorrencia;

	@ManyToOne
	@JoinColumn(name = "id_consultatipo", nullable = false, insertable = true, updatable = false)
	private ConsultaTipo consultaTipo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(String ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Date getDataUltimaOcorrencia() {
		return dataUltimaOcorrencia;
	}

	public void setDataUltimaOcorrencia(Date dataUltimaOcorrencia) {
		this.dataUltimaOcorrencia = dataUltimaOcorrencia;
	}

	public BigDecimal getValorUltimaOcorrencia() {
		return valorUltimaOcorrencia;
	}

	public void setValorUltimaOcorrencia(BigDecimal valorUltimaOcorrencia) {
		this.valorUltimaOcorrencia = valorUltimaOcorrencia;
	}

	public ConsultaTipo getConsultaTipo() {
		return consultaTipo;
	}

	public void setConsultaTipo(ConsultaTipo consultaTipo) {
		this.consultaTipo = consultaTipo;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof ConsultaOcorrencia) {
			final ConsultaOcorrencia c = (ConsultaOcorrencia) arg0;
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
