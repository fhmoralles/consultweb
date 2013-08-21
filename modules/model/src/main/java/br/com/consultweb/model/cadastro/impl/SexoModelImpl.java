package br.com.consultweb.model.cadastro.impl;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.consultweb.domain.types.Sexo;
import br.com.consultweb.model.cadastro.spec.SexoModel;
import br.com.consultweb.repository.cadastro.spec.SexoRepository;

@Stateless(name = "sexoModel")
public class SexoModelImpl implements SexoModel {

	@EJB
	private SexoRepository sexoRepository;

	@Override
	public void create(Sexo c) {
		// TODO Auto-generated method stub
	}

	@Override
	public Sexo retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sexo update(Sexo c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Sexo c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(Sexo c) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Sexo> getSexos() {
		return sexoRepository.getSexos();
	}


}
