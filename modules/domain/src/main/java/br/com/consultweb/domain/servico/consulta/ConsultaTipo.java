package br.com.consultweb.domain.servico.consulta;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.consultweb.domain.servico.Consulta;
import br.com.consultweb.domain.types.TipoConsulta;
import br.com.consultweb.domain.types.TipoConsultaAbrangencia;

@Entity
@Table(name = "consultatipo")
@SequenceGenerator(name = "identificador", sequenceName = "identificador", allocationSize = 1)
public class ConsultaTipo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -460496181677084688L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "tipoConsulta", nullable = false, insertable = true, updatable = true)
	private TipoConsulta tipoConsulta;

	@Column(name = "tipoconsultaabrangencia", nullable = false, insertable = true, updatable = true)
	private TipoConsultaAbrangencia tipoConsultaAbrangencia;
	
	@ManyToOne
	@JoinColumn(name = "id_consulta", nullable = false, insertable = true, updatable = true)
	private Consulta consulta;
	
	@OneToMany(mappedBy = "consultaTipo", cascade = { CascadeType.ALL } )
	private List<ConsultaOcorrencia> consultaOcorrencias;

	@OneToMany(mappedBy = "consultaTipo", cascade = { CascadeType.ALL } )
	private List<ConsultaRestricao> consultaRestricoes;
	
	@OneToMany(mappedBy = "consultaTipo", cascade = { CascadeType.ALL } )
	private List<ConsultaRealizada> consultaRealizadas;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoConsulta getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(TipoConsulta tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	public TipoConsultaAbrangencia getTipoConsultaAbrangencia() {
		return tipoConsultaAbrangencia;
	}

	public void setTipoConsultaAbrangencia(
			TipoConsultaAbrangencia tipoConsultaAbrangencia) {
		this.tipoConsultaAbrangencia = tipoConsultaAbrangencia;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public List<ConsultaOcorrencia> getConsultaOcorrencias() {
		return consultaOcorrencias;
	}

	public void setConsultaOcorrencias(List<ConsultaOcorrencia> consultaOcorrencias) {
		this.consultaOcorrencias = consultaOcorrencias;
	}

	public List<ConsultaRestricao> getConsultaRestricoes() {
		return consultaRestricoes;
	}

	public void setConsultaRestricoes(List<ConsultaRestricao> consultaRestricoes) {
		this.consultaRestricoes = consultaRestricoes;
	}

	public List<ConsultaRealizada> getConsultaRealizadas() {
		return consultaRealizadas;
	}

	public void setConsultaRealizadas(List<ConsultaRealizada> consultaRealizadas) {
		this.consultaRealizadas = consultaRealizadas;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof ConsultaTipo) {
			final ConsultaTipo c = (ConsultaTipo) arg0;
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
