package br.com.consultweb.domain.servico;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.consultweb.domain.types.TipoConsulta;
import br.com.consultweb.domain.types.TipoConsultaAbrangencia;

@Entity
@Table(name = "produtoconsulta")
@SequenceGenerator(name = "identificador", sequenceName = "identificador", allocationSize = 1)
public class ProdutoConsulta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1711611394777202223L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "tipoconsulta", nullable = false, insertable = true, updatable = true)
	private TipoConsulta tipoConsulta;

	@Column(name = "tipoconsultaabrangencia", nullable = false, insertable = true, updatable = true)
	private TipoConsultaAbrangencia tipoConsultaAbrangencia;

	@ManyToOne
	@JoinColumn(name = "id_produto", nullable = false, insertable = true, updatable = true)
	private Produto produto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoConsulta getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(TipoConsulta tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public TipoConsultaAbrangencia getTipoConsultaAbrangencia() {
		return tipoConsultaAbrangencia;
	}

	public void setTipoConsultaAbrangencia(
			TipoConsultaAbrangencia tipoConsultaAbrangencia) {
		this.tipoConsultaAbrangencia = tipoConsultaAbrangencia;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof ProdutoConsulta) {
			final ProdutoConsulta c = (ProdutoConsulta) arg0;
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
