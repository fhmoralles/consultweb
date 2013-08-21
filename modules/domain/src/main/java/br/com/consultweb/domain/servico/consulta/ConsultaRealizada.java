package br.com.consultweb.domain.servico.consulta;

import java.io.Serializable;
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
@Table(name = "consultarealizada")
@SequenceGenerator(name = "identificador", sequenceName = "identificador", allocationSize = 1)
public class ConsultaRealizada implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2824312202831030198L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "indice", nullable = false, insertable = true, updatable = true, length = 30)
	private Integer indice;
	
	@Column(name = "dataconsulta", nullable = false, insertable = true, updatable = false)
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataConsulta;
	
	@Column(name = "credor", nullable = false, insertable = true, updatable = true, length = 30)
	private String credor;
	
	@Column(name = "localidade", nullable = false, insertable = true, updatable = true, length = 30)
	private String localidade;
	
	@Column(name = "entidade", nullable = false, insertable = true, updatable = true, length = 30)
	private String entidade;

	@ManyToOne
	@JoinColumn(name = "id_consultatipo", nullable = false, insertable = true, updatable = false)
	private ConsultaTipo consultaTipo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	public Date getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public String getCredor() {
		return credor;
	}

	public void setCredor(String credor) {
		this.credor = credor;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getEntidade() {
		return entidade;
	}

	public void setEntidade(String entidade) {
		this.entidade = entidade;
	}

	public ConsultaTipo getConsultaTipo() {
		return consultaTipo;
	}

	public void setConsultaTipo(ConsultaTipo consultaTipo) {
		this.consultaTipo = consultaTipo;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof ConsultaRealizada) {
			final ConsultaRealizada c = (ConsultaRealizada) arg0;
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
