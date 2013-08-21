package br.com.consultweb.model.cadastro.impl;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.consultweb.domain.types.EstadoCivil;
import br.com.consultweb.model.cadastro.spec.EstadoCivilModel;
import br.com.consultweb.repository.cadastro.spec.EstadoCivilRepository;

@Stateless(name = "estadoCivilModel")
public class EstadoCivilModelImpl implements EstadoCivilModel {

	@EJB
	private EstadoCivilRepository estadoCivilRepository;

	@Override
	public void create(EstadoCivil c) {
		// TODO Auto-generated method stub
	}

	@Override
	public EstadoCivil retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EstadoCivil update(EstadoCivil c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(EstadoCivil c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(EstadoCivil c) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<EstadoCivil> getEstadosCivil() {
		return estadoCivilRepository.getEstadosCivil();
	}


}
