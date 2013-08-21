package br.com.consultweb.repository.cadastro.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import br.com.consultweb.domain.types.Sexo;
import br.com.consultweb.repository.cadastro.spec.SexoRepository;
import br.com.consultweb.repository.spec.AbstractConsultWebRepository;

@Stateless(name = "sexoRepository")
public class SexoRepositoryImpl extends
		AbstractConsultWebRepository<Sexo> implements
		SexoRepository {

	public SexoRepositoryImpl() {
		super(Sexo.class);
	}

	@Override
	public List<Sexo> getSexos() {

		List<Sexo> sexos = new ArrayList<Sexo>();
		
		for(Sexo sexo : Sexo.values() ) {
			sexos.add(sexo);
		}
		
		return sexos;

	}

	
}
