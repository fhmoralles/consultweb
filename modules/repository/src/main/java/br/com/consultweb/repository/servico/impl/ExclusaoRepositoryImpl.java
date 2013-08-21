package br.com.consultweb.repository.servico.impl;

import javax.ejb.Stateless;

import br.com.consultweb.domain.servico.Exclusao;
import br.com.consultweb.repository.servico.spec.ExclusaoRepository;
import br.com.consultweb.repository.spec.AbstractConsultWebRepository;

@Stateless(name = "exclusaoRepository")
public class ExclusaoRepositoryImpl extends
		AbstractConsultWebRepository<Exclusao> implements
		ExclusaoRepository {

	public ExclusaoRepositoryImpl() {
		super(Exclusao.class);
	}

}
