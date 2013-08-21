package br.com.consultweb.repository.servico.impl;

import java.util.List;

import javax.ejb.Stateless;

import br.com.consultweb.domain.servico.NaturezaInclusao;
import br.com.consultweb.repository.servico.spec.NaturezaInclusaoRepository;
import br.com.consultweb.repository.spec.AbstractConsultWebRepository;

@Stateless(name = "naturezaInclusaoRepository")
public class NaturezaInclusaoRepositoryImpl extends
		AbstractConsultWebRepository<NaturezaInclusao> implements
		NaturezaInclusaoRepository {

	public NaturezaInclusaoRepositoryImpl() {
		super(NaturezaInclusao.class);
	}

	@Override
	public List<NaturezaInclusao> getAll() {

		return retrieveByCriteria(null);

	}


}
