package br.com.consultweb.repository.servico.spec.consulta;

import java.util.List;

import javax.ejb.Local;

import br.com.consultweb.domain.servico.consulta.ConsultaRestricao;
import br.com.consultweb.domain.servico.consulta.ConsultaTipo;

@Local
public interface ConsultaRestricaoRepository {
	
	List<ConsultaRestricao> gerarRestricoesRestricaoPadrao(ConsultaTipo consultaTipo);
	
}
