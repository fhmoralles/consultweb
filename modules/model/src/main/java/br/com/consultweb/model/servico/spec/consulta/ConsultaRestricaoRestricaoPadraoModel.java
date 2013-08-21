package br.com.consultweb.model.servico.spec.consulta;

import java.util.List;

import br.com.consultweb.domain.servico.consulta.ConsultaRestricao;
import br.com.consultweb.domain.servico.consulta.ConsultaTipo;

public interface ConsultaRestricaoRestricaoPadraoModel {
	
	List<ConsultaRestricao> geraConsultaRestricoes(ConsultaTipo consultaTipo);
	
}
