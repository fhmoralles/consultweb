package br.com.consultweb.domain.cadastro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.consultweb.domain.servico.Produto;
import br.com.consultweb.domain.types.PorteEmpresa;
import br.com.consultweb.domain.types.Status;
import br.com.consultweb.domain.types.TipoPessoa;

@Entity
@Table(name = "associado")
@SequenceGenerator(name = "identificador", sequenceName = "identificador")
public class Associado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6159192881453992435L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "codigo", nullable = false, insertable = true, updatable = false, unique = true)
	private Integer codigo;

	@Column(name = "tipopessoa", nullable = false, insertable = true, updatable = true)
	private TipoPessoa tipoPessoa;

	@Column(name = "cnpj", nullable = false, insertable = true, updatable = true, length = 20)
	private String cnpj;

	@Column(name = "dataabertura", nullable = false, insertable = true, updatable = false)
	@Temporal(value = TemporalType.DATE)
	private Date dataAbertura;

	@Column(name = "razaosocial", nullable = false, insertable = true, updatable = true, length = 100)
	private String razaoSocial;

	@Column(name = "fantasia", nullable = false, insertable = true, updatable = true, length = 100)
	private String fantasia;

	@Column(name = "inscricaoEstadual", nullable = false, insertable = true, updatable = true, length = 100)
	private String inscricaoEstadual;

	@Column(name = "inscricaomunicipal", nullable = false, insertable = true, updatable = true, length = 100)
	private String inscricaoMunicipal;

	@Column(name = "status", nullable = false, insertable = true, updatable = true)
	private Status status;

	@Column(name = "efetuaregistro", nullable = false, insertable = true, updatable = true)
	private Boolean efetuaRegistro;

	@Column(name = "porteempresa", nullable = false, insertable = true, updatable = true)
	private PorteEmpresa porteEmpresa;

	@Column(name = "capitalsocial", nullable = false, insertable = true, updatable = true)
	private BigDecimal capitalSocial;

	@Column(name = "faturamentoanual", nullable = false, insertable = true, updatable = true)
	private BigDecimal faturamentoAnual;

	@Column(name = "anoreferencia", nullable = false, insertable = true, updatable = true)
	private Short anoReferencia;

	@Column(name = "qtdefiliais", nullable = false, insertable = true, updatable = true)
	private Short qtdeFiliais;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH })
	@JoinColumn(name = "id_entidade", nullable = false, insertable = true, updatable = true)
	private Entidade entidade;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH })
	@JoinColumn(name = "id_ramoatividade", nullable = false, insertable = true, updatable = true)
	private RamoAtividade ramoAtividade;

	@OneToMany(mappedBy = "associado", cascade = { CascadeType.ALL })
	private List<AssociadoDispositivo> associadoDispositivos;
	
    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinTable(name = "associadoproduto", joinColumns = { @JoinColumn(name = "id_associado") }, inverseJoinColumns = { @JoinColumn(name = "id_produto") })
	private List<Produto> produtos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipo(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}

	public String getInscriacaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscriacaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	public void setInscriacaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Boolean getEfetuaRegistro() {
		return efetuaRegistro;
	}

	public void setEfetuaRegistro(Boolean efetuaRegistro) {
		this.efetuaRegistro = efetuaRegistro;
	}

	public PorteEmpresa getPorteEmpresa() {
		return porteEmpresa;
	}

	public void setPorteEmpresa(PorteEmpresa porteEmpresa) {
		this.porteEmpresa = porteEmpresa;
	}

	public BigDecimal getCapitalSocial() {
		return capitalSocial;
	}

	public void setCapitalSocial(BigDecimal capitalSocial) {
		this.capitalSocial = capitalSocial;
	}

	public BigDecimal getFaturamentoAnual() {
		return faturamentoAnual;
	}

	public void setFaturamentoAnual(BigDecimal faturamentoAnual) {
		this.faturamentoAnual = faturamentoAnual;
	}

	public Short getAnoReferencia() {
		return anoReferencia;
	}

	public void setAnoReferencia(Short anoReferencia) {
		this.anoReferencia = anoReferencia;
	}

	public Short getQtdeFiliais() {
		return qtdeFiliais;
	}

	public void setQtdeFiliais(Short qtdeFiliais) {
		this.qtdeFiliais = qtdeFiliais;
	}

	public Entidade getEntidade() {
		return entidade;
	}

	public void setEntidade(Entidade entidade) {
		this.entidade = entidade;
	}

	public RamoAtividade getRamoAtividade() {
		return ramoAtividade;
	}

	public void setRamoAtividade(RamoAtividade ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}

	public List<AssociadoDispositivo> getAssociadoDispositivos() {
		return associadoDispositivos;
	}

	public void setAssociadoDispositivos(
			List<AssociadoDispositivo> associadoDispositivos) {
		this.associadoDispositivos = associadoDispositivos;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof Associado) {
			final Associado c = (Associado) arg0;
			return new EqualsBuilder().append(this.getId(), c.getId())
					.append(this.getCodigo(), c.getCodigo()).isEquals();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getId())
				.append(this.getCodigo()).toHashCode();
	}

	
}
