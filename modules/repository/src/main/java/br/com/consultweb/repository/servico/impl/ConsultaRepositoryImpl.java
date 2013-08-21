package br.com.consultweb.repository.servico.impl;

import javax.ejb.Stateless;

import br.com.consultweb.domain.servico.Consulta;
import br.com.consultweb.repository.servico.spec.ConsultaRepository;
import br.com.consultweb.repository.spec.AbstractConsultWebRepository;

@Stateless(name = "consultaRepository")
public class ConsultaRepositoryImpl extends
		AbstractConsultWebRepository<Consulta> implements
		ConsultaRepository {

	public ConsultaRepositoryImpl() {
		super(Consulta.class);
	}

}
