package br.com.consultweb.repository.cadastro.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import br.com.consultweb.domain.types.EstadoCivil;
import br.com.consultweb.repository.cadastro.spec.EstadoCivilRepository;
import br.com.consultweb.repository.spec.AbstractConsultWebRepository;


@Stateless(name = "estadoCivilRepository")
public class EstadoCivilRepositoryImpl extends
		AbstractConsultWebRepository<EstadoCivil> implements
		EstadoCivilRepository {

	public EstadoCivilRepositoryImpl() {
		super(EstadoCivil.class);
	}

	@Override
	public List<EstadoCivil> getEstadosCivil() {

		List<EstadoCivil> estadosCivil = new ArrayList<EstadoCivil>();
		
		for(EstadoCivil estadoCivil : EstadoCivil.values() ) {
			estadosCivil.add(estadoCivil);
		}
		
		return estadosCivil;

	}

}
