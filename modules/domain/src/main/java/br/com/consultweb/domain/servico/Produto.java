package br.com.consultweb.domain.servico;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.consultweb.domain.types.TipoProduto;

@Entity
@Table(name = "produto")
@SequenceGenerator(name = "identificador", sequenceName = "identificador", allocationSize = 1)
public class Produto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5810893318080833412L;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = false)
	@GeneratedValue(generator = "identificador", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "codigo", nullable = false, insertable = true, updatable = false, unique = true)
	private Integer codigo;

	@Column(name = "descricao", nullable = false, insertable = true, updatable = true, length=255)
	private String descricao;

	@Column(name = "tipoproduto", nullable = false, insertable = true, updatable = true)
	private TipoProduto tipoProduto;

	@Column(name = "valor", nullable = false, insertable = true, updatable = false)
	private BigDecimal valor;

	@OneToMany(mappedBy = "produto", cascade = { CascadeType.ALL })
	private List<ProdutoConsulta> produtoConsultas;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public List<ProdutoConsulta> getProdutoConsultas() {
		return produtoConsultas;
	}

	public void setProdutoConsultas(List<ProdutoConsulta> produtoConsultas) {
		this.produtoConsultas = produtoConsultas;
	}

	@Override
	public boolean equals(Object arg0) {

		if (arg0 instanceof Produto) {
			final Produto c = (Produto) arg0;
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
