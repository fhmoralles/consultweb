package br.com.consultweb.domain.parametros;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.consultweb.domain.servico.NaturezaInclusao;
import br.com.consultweb.domain.servico.Produto;

@Entity
@Table(name = "parametros")
@SequenceGenerator(name = "identificador", sequenceName = "identificador", allocationSize = 1)
public class Parametros implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 396717036576775883L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "diasrestricao", nullable = false, insertable = true, updatable = true)
	private Integer diasRestricao;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_naturezainclusao", nullable = false, insertable = true, updatable = true)
	private NaturezaInclusao naturezaInclusao;

	@Column(name = "anosvigencia", nullable = false, insertable = true, updatable = true)
	private Integer anosVigencia;

	@OneToOne
	@JoinColumn(name = "id_produtoincluir", nullable = false, insertable = true, updatable = true)
	private Produto produtoIncluir;

	@OneToOne
	@JoinColumn(name = "id_produtoexcluir", nullable = false, insertable = true, updatable = true)
	private Produto produtoExcluir;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDiasRestricao() {
		return diasRestricao;
	}

	public void setDiasRestricao(Integer diasRestricao) {
		this.diasRestricao = diasRestricao;
	}

	public NaturezaInclusao getNaturezaInclusao() {
		return naturezaInclusao;
	}

	public void setNaturezaInclusao(NaturezaInclusao naturezaInclusao) {
		this.naturezaInclusao = naturezaInclusao;
	}

	public Integer getAnosVigencia() {
		return anosVigencia;
	}

	public void setAnosVigencia(Integer anosVigencia) {
		this.anosVigencia = anosVigencia;
	}


	public Produto getProdutoIncluir() {
		return produtoIncluir;
	}

	public void setProdutoIncluir(Produto produtoIncluir) {
		this.produtoIncluir = produtoIncluir;
	}

	public Produto getProdutoExcluir() {
		return produtoExcluir;
	}

	public void setProdutoExcluir(Produto produtoExcluir) {
		this.produtoExcluir = produtoExcluir;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof Parametros) {
			final Parametros c = (Parametros) arg0;
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
