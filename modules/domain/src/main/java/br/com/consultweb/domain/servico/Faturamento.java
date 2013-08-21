package br.com.consultweb.domain.servico;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

import br.com.consultweb.domain.cadastro.Associado;
import br.com.consultweb.domain.types.SituacaoFaturamento;

@Entity
@Table(name = "faturamento")
@SequenceGenerator(name = "identificador", sequenceName = "identificador", allocationSize = 1)
public class Faturamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2100795627253508503L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "datafaturamento", nullable = false, insertable = true, updatable = false)
	@Temporal(value = TemporalType.DATE)
	private Date dataFaturamento;

	@Column(name = "valorfaturado", nullable = false, insertable = true, updatable = false)
	private BigDecimal valorFaturado;

	@Column(name = "situacao", nullable = false, insertable = true, updatable = false)
	private SituacaoFaturamento situacaoFaturamento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_produto", nullable = false, insertable = true, updatable = false)
	private Produto produto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_associado", nullable = false, insertable = true, updatable = false)
	private Associado associado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataFaturamento() {
		return dataFaturamento;
	}

	public void setDataFaturamento(Date dataFaturamento) {
		this.dataFaturamento = dataFaturamento;
	}

	public BigDecimal getValorFaturado() {
		return valorFaturado;
	}

	public void setValorFaturado(BigDecimal valorFaturado) {
		this.valorFaturado = valorFaturado;
	}

	public SituacaoFaturamento getSituacaoFaturamento() {
		return situacaoFaturamento;
	}

	public void setSituacaoFaturamento(SituacaoFaturamento situacaoFaturamento) {
		this.situacaoFaturamento = situacaoFaturamento;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Associado getAssociado() {
		return associado;
	}

	public void setAssociado(Associado associado) {
		this.associado = associado;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof Faturamento) {
			final Faturamento c = (Faturamento) arg0;
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
