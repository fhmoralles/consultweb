package br.com.consultweb.domain.servico.consulta;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.consultweb.domain.servico.Restricao;

@Entity
@Table(name = "consultarestricao")
@SequenceGenerator(name = "identificador", sequenceName = "identificador", allocationSize = 1)
public class ConsultaRestricao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2165245077272563071L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "indice", nullable = false, insertable = true, updatable = true, length = 30)
	private Integer indice;

	@OneToOne
	@JoinColumn(name = "id_restricao", nullable = false, insertable = true, updatable = false)
	private Restricao restricao;
	
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

	public Restricao getRestricao() {
		return restricao;
	}

	public void setRestricao(Restricao restricao) {
		this.restricao = restricao;
	}

	public ConsultaTipo getConsultaTipo() {
		return consultaTipo;
	}

	public void setConsultaTipo(ConsultaTipo consultaTipo) {
		this.consultaTipo = consultaTipo;
	}
	
	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof ConsultaRestricao) {
			final ConsultaRestricao c = (ConsultaRestricao) arg0;
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
