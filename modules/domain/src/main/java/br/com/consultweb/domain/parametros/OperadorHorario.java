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

@Entity
@Table(name = "operadorhorario")
@SequenceGenerator(name = "identificador", sequenceName = "identificador", allocationSize = 1)
public class OperadorHorario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8966212866812812705L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "diasemana", nullable = false, insertable = true, updatable = false, unique = true)
	private Short diaSemana;

	@Column(name = "horainicio", nullable = false, insertable = true, updatable = false, unique = true)
	private Short horaInicio;

	@Column(name = "horatermino", nullable = false, insertable = true, updatable = false, unique = true)
	private Short horaTermino;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_operador", nullable = false, insertable = true, updatable = true)
	private Operador operador;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Short getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(Short diaSemana) {
		this.diaSemana = diaSemana;
	}

	public Short getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Short horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Short getHoraTermino() {
		return horaTermino;
	}

	public void setHoraTermino(Short horaTermino) {
		this.horaTermino = horaTermino;
	}

	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof OperadorHorario) {
			final OperadorHorario c = (OperadorHorario) arg0;
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
