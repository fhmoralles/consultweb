package br.com.consultweb.repository.cadastro.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import br.com.consultweb.domain.types.Estado;
import br.com.consultweb.repository.cadastro.spec.EstadoRepository;
import br.com.consultweb.repository.spec.AbstractConsultWebRepository;

@Stateless(name = "estadoRepository")
public class EstadoRepositoryImpl extends
		AbstractConsultWebRepository<Estado> implements
		EstadoRepository {

	public EstadoRepositoryImpl() {
		super(Estado.class);
	}

	@Override
	public List<Estado> getEstados() {

		List<Estado> estados = new ArrayList<Estado>();
		
		for(Estado estado : Estado.values() ) {
			estados.add(estado);
		}
		
		return estados;

	}

	
}