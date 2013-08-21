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

import br.com.consultweb.domain.types.Estado;

@Entity
@Table(name = "contraparteendereco")
@SequenceGenerator(name = "identificador", sequenceName = "identificador", allocationSize = 1)
public class ContraparteEndereco implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5723918924307899048L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "cep", nullable = false, insertable = true, updatable = true, length = 9)
	private String cep;

	@Column(name = "logradouro", nullable = false, insertable = true, updatable = true, length = 100)
	private String logradouro;

	@Column(name = "numero", nullable = false, insertable = true, updatable = true, length = 10)
	private String numero;

	@Column(name = "complemento", nullable = false, insertable = true, updatable = true, length = 100)
	private String complemento;

	@Column(name = "bairro", nullable = false, insertable = true, updatable = true, length = 50)
	private String bairro;

	@Column(name = "municipio", nullable = false, insertable = true, updatable = true, length = 100)
	private String municipio;

	@Column(name = "estado", nullable = false, insertable = true, updatable = true, length = 2)
	private Estado estado;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_contraparte", nullable = false, insertable = true, updatable = false)
	private Contraparte contraparte;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Contraparte getContraparte() {
		return contraparte;
	}

	public void setContraparte(Contraparte contraparte) {
		this.contraparte = contraparte;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof ContraparteEndereco) {
			final ContraparteEndereco c = (ContraparteEndereco) arg0;
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
