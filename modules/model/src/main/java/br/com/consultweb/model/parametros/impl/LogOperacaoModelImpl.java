package br.com.consultweb.model.parametros.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.consultweb.domain.parametros.LogOperacao;
import br.com.consultweb.model.parametros.spec.LogOperacaoModel;
import br.com.consultweb.repository.parametros.spec.LogOperacaoRepository;

@Stateless(name = "logOperacaoModel")
public class LogOperacaoModelImpl implements LogOperacaoModel {

	@EJB
	private LogOperacaoRepository logOperacaoRepository;
	
	@Override
	public void create(LogOperacao c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LogOperacao retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogOperacao update(LogOperacao c) throws Exception {
		return logOperacaoRepository.update(c);
	}

	@Override
	public void delete(LogOperacao c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(LogOperacao c) {
		// TODO Auto-generated method stub
		
	}


}
