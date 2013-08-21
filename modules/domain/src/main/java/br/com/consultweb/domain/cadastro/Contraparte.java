package br.com.consultweb.domain.cadastro;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.consultweb.domain.types.Estado;
import br.com.consultweb.domain.types.EstadoCivil;
import br.com.consultweb.domain.types.Sexo;

@Entity
@Table(name = "contraparte")
@SequenceGenerator(name = "identificador", sequenceName = "identificador", allocationSize = 1)
public class Contraparte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5723918924307899048L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "cpf", nullable = false, insertable = true, updatable = false, length = 14)
	private String cpf;

	@Column(name = "cpfregiao", nullable = false, insertable = true, updatable = true, length = 10)
	private String cpfRegiao;

	@Column(name = "nome", nullable = false, insertable = true, updatable = true, length = 100)
	private String nome;
	
	@Column(name = "nomecomercial", nullable = false, insertable = true, updatable = true, length = 100)
	private String nomeComercial;
	
	@Column(name = "rg", nullable = false, insertable = true, updatable = true, length = 20)
	private String rg;
	
	@Column(name = "rgestado", nullable = false, insertable = true, updatable = true)
	private Estado rgEstado;
	
	@Column(name = "sexo", nullable = false, insertable = true, updatable = true)
	private Sexo sexo;
	
	@Column(name = "datanascimento", nullable = false, insertable = true, updatable = true)
	@Temporal(value = TemporalType.DATE)
	private Date dataNascimento;
	
	@Column(name = "nomepai", nullable = false, insertable = true, updatable = true, length = 100)
	private String nomePai;
	
	@Column(name = "nomemae", nullable = false, insertable = true, updatable = true, length = 100)
	private String nomeMae;
	
	@Column(name = "email", nullable = false, insertable = true, updatable = true, length = 100)
	private String eMail;
	
	@Column(name = "telefone", nullable = false, insertable = true, updatable = true, length = 20)
	private String telefone;
	
	@Column(name = "estadocivil", nullable = false, insertable = true, updatable = true)
	@Enumerated(value = EnumType.ORDINAL)
	private EstadoCivil estadoCivil;
	
	@OneToMany(mappedBy = "contraparte", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER )
	@OrderBy(value = "id")
	private List<ContraparteEndereco> contraparteEnderecos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCpfRegiao() {
		return cpfRegiao;
	}

	public void setCpfRegiao(String cpfRegiao) {
		this.cpfRegiao = cpfRegiao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeComercial() {
		return nomeComercial;
	}

	public void setNomeComercial(String nomeComercial) {
		this.nomeComercial = nomeComercial;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Estado getRgEstado() {
		return rgEstado;
	}

	public void setRgEstado(Estado rgEstado) {
		this.rgEstado = rgEstado;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadocivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public List<ContraparteEndereco> getContraparteEnderecos() {
		return contraparteEnderecos;
	}

	public void setContraparteEnderecos(
			List<ContraparteEndereco> contraparteEnderecos) {
		this.contraparteEnderecos = contraparteEnderecos;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof Contraparte) {
			final Contraparte c = (Contraparte) arg0;
			return new EqualsBuilder().append(this.getId(), c.getId())
					.append(this.getCpf(), c.getCpf()).isEquals();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getId())
				.append(this.getCpf()).toHashCode();
	}

}
