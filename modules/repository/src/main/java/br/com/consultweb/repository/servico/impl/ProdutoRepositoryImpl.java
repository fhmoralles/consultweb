package br.com.consultweb.repository.servico.impl;

import javax.ejb.Stateless;

import br.com.consultweb.domain.servico.Produto;
import br.com.consultweb.repository.servico.spec.ProdutoRepository;
import br.com.consultweb.repository.spec.AbstractConsultWebRepository;

@Stateless(name = "produtoRepository")
public class ProdutoRepositoryImpl extends
		AbstractConsultWebRepository<Produto> implements
		ProdutoRepository {

	public ProdutoRepositoryImpl() {
		super(Produto.class);
	}

}
