package br.com.consultweb.model.servico.spec.consulta;

import java.util.List;

import javax.ejb.Local;

import br.com.consultweb.domain.servico.consulta.ConsultaOcorrencia;
import br.com.consultweb.domain.servico.consulta.ConsultaTipo;

@Local
public interface ConsultaOcorrenciaModel {
	
	List<ConsultaOcorrencia> geraConsultaOcorrencias(ConsultaTipo consultaTipo);
	
}
