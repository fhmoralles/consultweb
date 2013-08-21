package br.com.consultweb.model.parametros.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.consultweb.domain.parametros.Parametros;
import br.com.consultweb.model.parametros.spec.ParametrosModel;
import br.com.consultweb.repository.parametros.spec.ParametrosRepository;

@Stateless(name = "parametrosModel")
public class ParametrosModelImpl implements ParametrosModel {

	@EJB
	private ParametrosRepository parametrosRepository;
	
	@Override
	public void create(Parametros c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Parametros retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Parametros update(Parametros c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Parametros c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Parametros c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Parametros locate() {
		return parametrosRepository.retrieve(1L);
	}


}
