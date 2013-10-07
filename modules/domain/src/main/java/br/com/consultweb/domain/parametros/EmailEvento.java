package br.com.consultweb.domain.parametros;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.consultweb.domain.types.Evento;

@Entity
@Table(name = "emailevento")
@SequenceGenerator(name = "identificador", sequenceName = "identificador", allocationSize = 1)
public class EmailEvento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3649485562320055151L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "email", nullable = false, insertable = true, updatable = false, length=255)
	private String email;

	@Column(name = "evento", nullable = false, insertable = true, updatable = false, length = 1000)
	@Enumerated(EnumType.STRING)
	private Evento evento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof EmailEvento) {
			final EmailEvento c = (EmailEvento) arg0;
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
