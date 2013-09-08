package br.com.consultweb.domain.servico;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.consultweb.domain.cadastro.Associado;
import br.com.consultweb.domain.cadastro.Contraparte;
import br.com.consultweb.domain.servico.consulta.ConsultaTipo;

@Entity
@Table(name = "consulta")
@SequenceGenerator(name = "identificador", sequenceName = "identificador", allocationSize = 1)
@NamedQueries({ @NamedQuery(name = "queryConsultas", query = "select c from Consulta c where c.contraparte.cpf = :cpf and c.dataConsulta between :dataInicio and :dataFim order by c.id desc") })
public class Consulta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2690237318710435013L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "descricao", nullable = false, insertable = true, updatable = true, length=255)
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "id_associado", nullable = false, insertable = true, updatable = false)
	private Associado associado;

	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "id_contraparte", nullable = false, insertable = true, updatable = false)
	private Contraparte contraparte;

	@OneToMany(mappedBy = "consulta", cascade = { CascadeType.ALL } )
	private List<ConsultaTipo> consultaTipos;
	
	@Column(name = "dataconsulta", nullable = false, insertable = true, updatable = false)
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataConsulta;
	
	@OneToOne(mappedBy = "consulta")
	private Protocolo protocolo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public List<ConsultaTipo> getConsultaTipos() {
		return consultaTipos;
	}

	public void setConsultaTipos(List<ConsultaTipo> consultaTipos) {
		this.consultaTipos = consultaTipos;
	}

	public Date getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public Protocolo getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(Protocolo protocolo) {
		this.protocolo = protocolo;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof Consulta) {
			final Consulta c = (Consulta) arg0;
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
