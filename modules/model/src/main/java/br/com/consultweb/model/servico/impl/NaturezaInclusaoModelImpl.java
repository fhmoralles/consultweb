package br.com.consultweb.model.servico.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.consultweb.domain.servico.NaturezaInclusao;
import br.com.consultweb.model.servico.spec.NaturezaInclusaoModel;
import br.com.consultweb.repository.servico.spec.NaturezaInclusaoRepository;

@Stateless(name = "naturezaInclusaoModel")
public class NaturezaInclusaoModelImpl implements NaturezaInclusaoModel {

	@EJB
	private NaturezaInclusaoRepository naturezaInclusaoRepository;

	@Override
	public void create(NaturezaInclusao c) {
		// TODO Auto-generated method stub
	}

	@Override
	public NaturezaInclusao retrieve(Long id) {
		return naturezaInclusaoRepository.retrieve(id);
	}

	@Override
	public NaturezaInclusao update(NaturezaInclusao c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(NaturezaInclusao c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(NaturezaInclusao c) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<NaturezaInclusao> getAll() {
		return naturezaInclusaoRepository.getAll();
	}

	
}
