package br.com.consultweb.domain.parametros;

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

import br.com.consultweb.domain.cadastro.AssociadoDispositivo;

@Entity
@Table(name = "operadordispositivo")
@SequenceGenerator(name = "identificador", sequenceName = "identificador", allocationSize = 1)
public class OperadorDispositivo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9185257929152760607L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "senha", nullable = false, insertable = true, updatable = true, length = 32)
	private String senha;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_operador", nullable = false, insertable = true, updatable = true)
	private Operador operador;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_associadodispositivo", nullable = false, insertable = true, updatable = true)
	private AssociadoDispositivo associadoDispositivo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	public AssociadoDispositivo getAssociadoDispositivo() {
		return associadoDispositivo;
	}

	public void setAssociadoDispositivos(
			AssociadoDispositivo associadoDispositivo) {
		this.associadoDispositivo = associadoDispositivo;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof OperadorDispositivo) {
			final OperadorDispositivo c = (OperadorDispositivo) arg0;
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
