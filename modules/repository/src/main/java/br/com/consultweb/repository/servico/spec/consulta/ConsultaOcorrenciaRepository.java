package br.com.consultweb.repository.servico.spec.consulta;

import javax.ejb.Local;

import br.com.consultweb.domain.servico.consulta.ConsultaOcorrencia;
import br.com.consultweb.domain.servico.consulta.ConsultaTipo;

@Local
public interface ConsultaOcorrenciaRepository {
	
	ConsultaOcorrencia gerarRegistroInclusaoRestricaoPadrao(ConsultaTipo consultaTipo);
	
	ConsultaOcorrencia gerarConsultasRealizadaRestricaoPadrao(ConsultaTipo consultaTipo);
	
}
