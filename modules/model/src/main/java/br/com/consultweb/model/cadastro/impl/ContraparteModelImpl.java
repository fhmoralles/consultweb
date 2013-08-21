package br.com.consultweb.model.cadastro.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.consultweb.domain.cadastro.Contraparte;
import br.com.consultweb.model.cadastro.spec.ContraparteModel;
import br.com.consultweb.repository.cadastro.spec.ContraparteRepository;

@Stateless(name = "contraparteModel")
public class ContraparteModelImpl implements ContraparteModel {

	@EJB
	private ContraparteRepository contraparteRepository;

	@Override
	public void create(Contraparte c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Contraparte retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contraparte update(Contraparte c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Contraparte c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Contraparte c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Contraparte retrieveByCpf(String cpf) {
		return contraparteRepository.retrieveByCpf(cpf);
	}

	
}
