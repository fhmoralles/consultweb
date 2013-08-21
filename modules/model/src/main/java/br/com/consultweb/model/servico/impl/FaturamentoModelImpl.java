package br.com.consultweb.model.servico.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.consultweb.domain.servico.Faturamento;
import br.com.consultweb.model.servico.spec.FaturamentoModel;
import br.com.consultweb.repository.servico.spec.FaturamentoRepository;

@Stateless(name = "faturamentoModel")
public class FaturamentoModelImpl implements FaturamentoModel {

	@EJB
	private FaturamentoRepository faturamentoRepository;

	@Override
	public void create(Faturamento c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Faturamento update(Faturamento c) {
		return faturamentoRepository.update(c);
	}
	
	@Override
	public Faturamento retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Faturamento c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Faturamento c) {
		// TODO Auto-generated method stub
		
	}

}
