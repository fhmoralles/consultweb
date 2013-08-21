package br.com.consultweb.model.servico.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.consultweb.domain.types.TipoDevedor;
import br.com.consultweb.model.servico.spec.TipoDevedorModel;
import br.com.consultweb.repository.servico.spec.TipoDevedorRepository;

@Stateless(name = "TipoDevedorModel")
public class TipoDevedorModelImpl implements TipoDevedorModel {

	@EJB
	private TipoDevedorRepository tipoDevedorRepository;

	@Override
	public void create(TipoDevedor c) {
		// TODO Auto-generated method stub
	}

	@Override
	public TipoDevedor retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoDevedor update(TipoDevedor c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(TipoDevedor c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(TipoDevedor c) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<TipoDevedor> getTiposDevedor() {
		return tipoDevedorRepository.getTiposDevedor();
	}


}
