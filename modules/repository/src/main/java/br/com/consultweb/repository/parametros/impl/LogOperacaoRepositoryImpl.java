package br.com.consultweb.repository.parametros.impl;

import javax.ejb.Stateless;

import br.com.consultweb.domain.parametros.LogOperacao;
import br.com.consultweb.repository.parametros.spec.LogOperacaoRepository;
import br.com.consultweb.repository.spec.AbstractConsultWebRepository;

@Stateless(name = "LogOperacaoRepository")
public class LogOperacaoRepositoryImpl extends
		AbstractConsultWebRepository<LogOperacao> implements
		LogOperacaoRepository {

	protected LogOperacaoRepositoryImpl() {
		super(LogOperacao.class);
	}

}
