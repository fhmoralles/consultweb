package br.com.consultweb.domain.servico;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.consultweb.domain.cadastro.Associado;
import br.com.consultweb.domain.cadastro.Contraparte;

@Entity
@Table(name = "exclusao")
@SequenceGenerator(name = "identificador", sequenceName = "identificador", allocationSize = 1)
public class Exclusao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2690237318710435013L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_associado", nullable = false, insertable = true, updatable = false)
	private Associado associado;

	@ManyToOne
	@JoinColumn(name = "id_contraparte", nullable = false, insertable = true, updatable = false)
	private Contraparte contraparte;

	@OneToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "id_restricao", nullable = false, insertable = true, updatable = false)
	private Restricao restricao;
	
	@Column(name = "dataexclusao", nullable = false, insertable = true, updatable = false)
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataExclusao;
	
	@OneToOne(mappedBy = "exclusao")
	private Protocolo protocolo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	public Protocolo getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(Protocolo protocolo) {
		this.protocolo = protocolo;
	}

	public Restricao getRestricao() {
		return restricao;
	}

	public void setRestricao(Restricao restricao) {
		this.restricao = restricao;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof Exclusao) {
			final Exclusao c = (Exclusao) arg0;
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
