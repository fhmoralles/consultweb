package br.com.consultweb.domain.parametros;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.consultweb.domain.cadastro.Associado;
import br.com.consultweb.domain.types.Status;
import br.com.consultweb.domain.types.TipoOperador;

@Entity
@Table(name = "operador")
@SequenceGenerator(name = "identificador", sequenceName = "identificador", allocationSize = 1)
public class Operador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2266613622775318461L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "codigo", nullable = false, insertable = true, updatable = false, unique = true)
	private Integer codigo;

	@Column(name = "nome", nullable = false, insertable = true, updatable = true, length = 100)
	private String nome;

	@Column(name = "tipooperador", nullable = false, insertable = true, updatable = true)
	private TipoOperador tipoOperador;

	@Column(name = "status", nullable = false, insertable = true, updatable = true)
	private Status status;

	@Column(name = "emailcorporativo", nullable = false, insertable = true, updatable = true, length = 100)
	private String emailCorporativo;

	@Column(name = "telefonecorporativo", nullable = false, insertable = true, updatable = true, length = 20)
	private String telefoneCorporativo;

	@Column(name = "ramalcorporativo", nullable = false, insertable = true, updatable = true, length = 10)
	private String ramalCorporativo;

	@Column(name = "exibeConsultas", nullable = false, insertable = true, updatable = true)
	private Boolean exibeConsultas;

	@Column(name = "validoate", nullable = false, insertable = true, updatable = false)
	@Temporal(value = TemporalType.DATE)
	private Date validoAte;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_associado", nullable = false, insertable = true, updatable = true)
	private Associado associado;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_perfil", nullable = false, insertable = true, updatable = true)
	private Perfil perfil;

	@OneToMany(mappedBy = "operador", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private List<OperadorDispositivo> operadorDispositivos;

	@OneToMany(mappedBy = "operador", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private List<OperadorHorario> operadorHorarios;

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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoOperador getTipoOperador() {
		return tipoOperador;
	}

	public void setTipoOperador(TipoOperador tipoOperador) {
		this.tipoOperador = tipoOperador;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getEmailCorporativo() {
		return emailCorporativo;
	}

	public void setEmailCorporativo(String emailCorporativo) {
		this.emailCorporativo = emailCorporativo;
	}

	public String getTelefoneCorporativo() {
		return telefoneCorporativo;
	}

	public void setTelefoneCorporativo(String telefoneCorporativo) {
		this.telefoneCorporativo = telefoneCorporativo;
	}

	public String getRamalCorporativo() {
		return ramalCorporativo;
	}

	public void setRamalCorporativo(String ramalCorporativo) {
		this.ramalCorporativo = ramalCorporativo;
	}

	public Boolean getExibeConsultas() {
		return exibeConsultas;
	}

	public void setExibeConsultas(Boolean exibeConsultas) {
		this.exibeConsultas = exibeConsultas;
	}

	public Date getValidoAte() {
		return validoAte;
	}

	public void setValidoAte(Date validoAte) {
		this.validoAte = validoAte;
	}

	public Associado getAssociado() {
		return associado;
	}

	public void setAssociado(Associado associado) {
		this.associado = associado;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<OperadorDispositivo> getOperadorDispositivos() {
		return operadorDispositivos;
	}

	public void setOperadorDispositivos(
			List<OperadorDispositivo> operadorDispositivos) {
		this.operadorDispositivos = operadorDispositivos;
	}

	public List<OperadorHorario> getOperadorHorarios() {
		return operadorHorarios;
	}

	public void setOperadorHorarios(List<OperadorHorario> operadorHorarios) {
		this.operadorHorarios = operadorHorarios;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof Operador) {
			final Operador c = (Operador) arg0;
			return new EqualsBuilder().append(this.getId(), c.getId())
					.append(this.getCodigo(), c.getCodigo()).isEquals();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getId())
				.append(this.getCodigo()).toHashCode();
	}

}
