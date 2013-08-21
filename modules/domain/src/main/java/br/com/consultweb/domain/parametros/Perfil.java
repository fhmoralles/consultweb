package br.com.consultweb.domain.parametros;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "perfil")
@SequenceGenerator(name = "identificador", sequenceName = "identificador", allocationSize = 1)
public class Perfil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 396717036576775883L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "descricao", nullable = false, insertable = true, updatable = true, length = 100)
	private String descricao;

	@Column(name = "incluir", nullable = false, insertable = true, updatable = true)
	private Boolean incluir;

	@Column(name = "excluir", nullable = false, insertable = true, updatable = true)
	private Boolean excluir;

	@Column(name = "consultar", nullable = false, insertable = true, updatable = true)
	private Boolean consultar;

	@Column(name = "carta", nullable = false, insertable = true, updatable = true)
	private Boolean carta;

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

	public Boolean getIncluir() {
		return incluir;
	}

	public void setInserir(Boolean incluir) {
		this.incluir = incluir;
	}

	public Boolean getExcluir() {
		return excluir;
	}

	public void setExcluir(Boolean excluir) {
		this.excluir = excluir;
	}

	public Boolean getConsultar() {
		return consultar;
	}

	public void setConsultar(Boolean consultar) {
		this.consultar = consultar;
	}

	public Boolean getCarta() {
		return carta;
	}

	public void setCarta(Boolean carta) {
		this.carta = carta;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof Perfil) {
			final Perfil c = (Perfil) arg0;
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
