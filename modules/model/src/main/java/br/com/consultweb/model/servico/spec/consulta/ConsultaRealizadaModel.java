package br.com.consultweb.model.servico.spec.consulta;

import java.util.List;

import javax.ejb.Local;

import br.com.consultweb.domain.servico.consulta.ConsultaRealizada;
import br.com.consultweb.domain.servico.consulta.ConsultaTipo;

@Local
public interface ConsultaRealizadaModel {
	
	List<ConsultaRealizada> geraConsultaRealizadas(ConsultaTipo consultaTipo);
	
}
