package br.com.consultweb.repository.servico.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import br.com.consultweb.domain.types.TipoDevedor;
import br.com.consultweb.repository.servico.spec.TipoDevedorRepository;
import br.com.consultweb.repository.spec.AbstractConsultWebRepository;

@Stateless(name = "tipoDevedorRepository")
public class TipoDevedorRepositoryImpl extends
		AbstractConsultWebRepository<TipoDevedor> implements
		TipoDevedorRepository {

	public TipoDevedorRepositoryImpl() {
		super(TipoDevedor.class);
	}

	@Override
	public List<TipoDevedor> getTiposDevedor() {

		List<TipoDevedor> tiposDevedor = new ArrayList<TipoDevedor>();
		
		for(TipoDevedor tipoDevedor : TipoDevedor.values() ) {
			tiposDevedor.add(tipoDevedor);
		}
		
		return tiposDevedor;

	}

	
}
