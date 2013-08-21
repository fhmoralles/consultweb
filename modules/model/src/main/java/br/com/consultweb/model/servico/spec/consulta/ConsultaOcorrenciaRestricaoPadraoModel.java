package br.com.consultweb.model.servico.spec.consulta;

import java.util.List;

import br.com.consultweb.domain.servico.consulta.ConsultaOcorrencia;
import br.com.consultweb.domain.servico.consulta.ConsultaTipo;

public interface ConsultaOcorrenciaRestricaoPadraoModel {
	
	List<ConsultaOcorrencia> geraConsultaOcorrencias(ConsultaTipo consultaTipo);
	
}
