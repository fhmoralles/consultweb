package br.com.consultweb.domain.servico;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.consultweb.domain.cadastro.Associado;
import br.com.consultweb.domain.cadastro.Contraparte;
import br.com.consultweb.domain.types.TipoDevedor;

@Entity
@Table(name = "restricao")
@SequenceGenerator(name = "identificador", sequenceName = "identificador", allocationSize = 1)
@NamedQueries(
	{ 
		@NamedQuery(name = "queryRestricoes", query = "select r from Restricao r where r.contraparte.cpf = :cpf and r.dataRestricao <= :dataInicio and r.dataVigencia >= :dataFim order by r.id desc"),
		@NamedQuery(name = "queryRestricoesAssociado", query = "select r from Restricao r where r.associado.codigo = :codigoAssociado and r.dataRestricao <= :dataInicio and r.dataVigencia >= :dataFim")		
	}
)
public class Restricao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7007068264110898963L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "datavencimento", nullable = false, insertable = true, updatable = false)
	@Temporal(value = TemporalType.DATE)
	private Date dataVencimento;

	@Column(name = "datacompra", nullable = false, insertable = true, updatable = false)
	@Temporal(value = TemporalType.DATE)
	private Date dataCompra;

	@Column(name = "tipodevedor", nullable = false, insertable = true, updatable = false)
	private TipoDevedor tipoDevedor;

	@Column(name = "contrato", nullable = false, insertable = true, updatable = true, length = 30)
	private String contrato;

	@Column(name = "valordebito", nullable = false, insertable = true, updatable = false)
	private BigDecimal valorDebito;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH })
	@JoinColumn(name = "id_naturezainclusao", nullable = false, insertable = true, updatable = false)
	private NaturezaInclusao naturezaInclusao;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH })
	@JoinColumn(name = "id_associado", nullable = false, insertable = true, updatable = false)
	private Associado associado;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH })
	@JoinColumn(name = "id_contraparte", nullable = false, insertable = true, updatable = false)
	private Contraparte contraparte;

	@Column(name = "datarestricao", nullable = false, insertable = true, updatable = false)
	@Temporal(value = TemporalType.DATE)
	private Date dataRestricao;

	@Column(name = "datainclusao", nullable = false, insertable = true, updatable = false)
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataInclusao;

	@Column(name = "datavigencia", nullable = false, insertable = true, updatable = true)
	@Temporal(value = TemporalType.DATE)
	private Date dataVigencia;

	@OneToOne(mappedBy = "restricao")
	private Protocolo protocolo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public TipoDevedor getTipoDevedor() {
		return tipoDevedor;
	}

	public void setTipoDevedor(TipoDevedor tipoDevedor) {
		this.tipoDevedor = tipoDevedor;
	}

	public String getContrato() {
		return contrato;
	}

	public void setContrato(String contrato) {
		this.contrato = contrato;
	}

	public BigDecimal getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}

	public NaturezaInclusao getNaturezaInclusao() {
		return naturezaInclusao;
	}

	public void setNaturezaInclusao(NaturezaInclusao naturezaInclusao) {
		this.naturezaInclusao = naturezaInclusao;
	}

	public Associado getAssociado() {
		return associado;
	}

	public void setAssociado(Associado associado) {
		this.associado = associado;
	}

	public Contraparte getContraparte() {
		return contraparte;
	}

	public void setContraparte(Contraparte contraparte) {
		this.contraparte = contraparte;
	}

	public Date getDataRestricao() {
		return dataRestricao;
	}

	public void setDataRestricao(Date dataRestricao) {
		this.dataRestricao = dataRestricao;
	}
	
	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Date getDataVigencia() {
		return dataVigencia;
	}

	public void setDataVigencia(Date dataVigencia) {
		this.dataVigencia = dataVigencia;
	}

	public Protocolo getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(Protocolo protocolo) {
		this.protocolo = protocolo;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof Restricao) {
			final Restricao c = (Restricao) arg0;
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
