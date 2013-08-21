package br.com.consultweb.model.servico.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.consultweb.domain.servico.Produto;
import br.com.consultweb.model.servico.spec.ProdutoModel;
import br.com.consultweb.repository.servico.spec.ProdutoRepository;

@Stateless(name = "produtoModel")
public class ProdutoModelImpl implements ProdutoModel {

	@EJB
	private ProdutoRepository produtoRepository;

	@Override
	public void create(Produto produto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Produto retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produto update(Produto produto) throws Exception {
		return produtoRepository.update(produto);
	}

	@Override
	public void delete(Produto produto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Produto produto) {
		produtoRepository.refresh(produto);
	}
	
	
}
