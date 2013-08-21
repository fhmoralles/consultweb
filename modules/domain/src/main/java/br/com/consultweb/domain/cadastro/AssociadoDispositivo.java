package br.com.consultweb.domain.cadastro;

import java.io.Serializable;

import javax.persistence.CascadeType;
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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.consultweb.domain.types.Dispositivo;


@Entity
@Table(name = "associadodispositivo")
@SequenceGenerator(name = "identificador", sequenceName = "identificador", allocationSize = 1)
public class AssociadoDispositivo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7699507571448054690L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "dispositivo", nullable = false, insertable = true, updatable = true, length = 20)	
	private Dispositivo dispositivo;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_associado", nullable = false, insertable = true, updatable = false)
	private Associado associado;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Dispositivo getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}

	public Associado getAssociado() {
		return associado;
	}

	public void setAssociado(Associado associado) {
		this.associado = associado;
	}
	
	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof AssociadoDispositivo) {
			final AssociadoDispositivo c = (AssociadoDispositivo) arg0;
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
