package br.com.consultweb.view.main.converter;

import javax.faces.convert.FacesConverter;

import br.com.consultweb.domain.servico.Produto;

@FacesConverter(value = "produtoConverter", forClass = Produto.class)
public class ProdutoConverter extends
		AbstractEntityConverter<Produto> {

	public ProdutoConverter() {
		super(Produto.class);
	}

}
