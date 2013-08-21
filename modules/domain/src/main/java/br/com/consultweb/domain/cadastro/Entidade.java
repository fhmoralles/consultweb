package br.com.consultweb.domain.cadastro;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "entidade")
@SequenceGenerator(name = "identificador", sequenceName = "identificador", allocationSize = 1)
public class Entidade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5275722209395760207L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "codigo", nullable = false, insertable = true, updatable = false, unique = true)
	private Integer codigo;

	@Column(name = "razaosocial", nullable = false, insertable = true, updatable = true, length = 100)
	private String razaoSocial;

	@Column(name = "fantasia", nullable = false, insertable = true, updatable = true, length = 100)
	private String fantasia;

	@Column(name = "cnpj", nullable = false, insertable = true, updatable = true, length = 18, unique = true)
	private String cnpj;

	@Column(name = "inscricao", nullable = false, insertable = true, updatable = true, length = 20)
	private String inscricao;

	@Column(name = "cep", nullable = false, insertable = true, updatable = true, length = 9)
	private String cep;

	@Column(name = "endereco", nullable = false, insertable = true, updatable = true, length = 255)
	private String endereco;

	@Column(name = "numero", nullable = false, insertable = true, updatable = true, length = 10)
	private String numero;

	@Column(name = "complemento", nullable = false, insertable = true, updatable = true, length = 100)
	private String complemento;

	@Column(name = "bairro", nullable = false, insertable = true, updatable = true, length = 100)
	private String bairro;

	@Column(name = "cidade", nullable = false, insertable = true, updatable = true, length = 50)
	private String cidade;

	@Column(name = "estado", nullable = false, insertable = true, updatable = true, length = 2)
	private String estado;

	@Column(name = "telefone", nullable = false, insertable = true, updatable = true, length = 20)
	private String telefone;

	@Column(name = "fax", nullable = false, insertable = true, updatable = true, length = 20)
	private String fax;

	@Column(name = "datacadastro", nullable = false, insertable = true, updatable = false)
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataCadastro;

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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
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

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof Entidade) {
			final Entidade c = (Entidade) arg0;
			return new EqualsBuilder().append(this.getId(), c.getId())
					.append(this.getCodigo(), c.getCodigo()).isEquals();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getCodigo())
				.append(this.getId()).toHashCode();
	}

}
