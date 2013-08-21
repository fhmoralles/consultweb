package br.com.consultweb.model.cadastro.impl;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.consultweb.domain.types.Estado;
import br.com.consultweb.model.cadastro.spec.EstadoModel;
import br.com.consultweb.repository.cadastro.spec.EstadoRepository;

@Stateless(name = "estadoModel")
public class EstadoModelImpl implements EstadoModel {

	@EJB
	private EstadoRepository estadoRepository;

	@Override
	public void create(Estado c) {
		// TODO Auto-generated method stub
	}

	@Override
	public Estado retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Estado update(Estado c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Estado c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(Estado c) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Estado> getEstados() {
		return estadoRepository.getEstados();
	}


}
