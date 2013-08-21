package br.com.consultweb.model.cadastro.impl;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.consultweb.domain.types.TipoPessoa;
import br.com.consultweb.model.cadastro.spec.TipoPessoaModel;
import br.com.consultweb.repository.cadastro.spec.TipoPessoaRepository;

@Stateless(name = "tipoPessoaModel")
public class TipoPessoaModelImpl implements TipoPessoaModel {

	@EJB
	private TipoPessoaRepository tipoPessoaRepository;

	@Override
	public void create(TipoPessoa c) {
		// TODO Auto-generated method stub
	}

	@Override
	public TipoPessoa retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoPessoa update(TipoPessoa c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(TipoPessoa c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(TipoPessoa c) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<TipoPessoa> getTiposPessoa() {
		return tipoPessoaRepository.getTiposPessoa();
	}


}
