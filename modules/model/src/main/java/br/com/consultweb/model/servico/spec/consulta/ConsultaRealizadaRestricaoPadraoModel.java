package br.com.consultweb.model.servico.spec.consulta;

import java.util.List;

import br.com.consultweb.domain.servico.consulta.ConsultaRealizada;
import br.com.consultweb.domain.servico.consulta.ConsultaTipo;

public interface ConsultaRealizadaRestricaoPadraoModel {
	
	List<ConsultaRealizada> geraConsultaRealizadas(ConsultaTipo consultaTipo);
	
}
