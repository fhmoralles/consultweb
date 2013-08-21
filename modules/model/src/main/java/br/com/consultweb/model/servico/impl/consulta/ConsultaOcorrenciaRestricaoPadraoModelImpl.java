package br.com.consultweb.model.servico.impl.consulta;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.consultweb.domain.servico.consulta.ConsultaOcorrencia;
import br.com.consultweb.domain.servico.consulta.ConsultaTipo;
import br.com.consultweb.model.servico.spec.consulta.ConsultaOcorrenciaRestricaoPadraoModel;
import br.com.consultweb.repository.servico.spec.consulta.ConsultaOcorrenciaRepository;

@Stateless(name = "consultaOcorrenciaRestricaoPadraoModel")
public class ConsultaOcorrenciaRestricaoPadraoModelImpl implements
		ConsultaOcorrenciaRestricaoPadraoModel {

	@EJB
	private ConsultaOcorrenciaRepository consultaOcorrenciaRepository;
	
	@Override
	public List<ConsultaOcorrencia> geraConsultaOcorrencias(
			ConsultaTipo consultaTipo) {
		
		List<ConsultaOcorrencia> consultaOcorrencias = new ArrayList<ConsultaOcorrencia>();
		
		ConsultaOcorrencia consultaOcorrencia = null;
		
		/* Registros de Incluções para Consulta tipo RestricaoPadrao */
		consultaOcorrencia = consultaOcorrenciaRepository.gerarRegistroInclusaoRestricaoPadrao(consultaTipo);
		consultaOcorrencias.add(consultaOcorrencia);
		
		/* Registros de Consultas Realizadas para Consulta tipo RestricaoPadrao */
		consultaOcorrencia = consultaOcorrenciaRepository.gerarConsultasRealizadaRestricaoPadrao(consultaTipo);
		consultaOcorrencias.add(consultaOcorrencia);
		
		return consultaOcorrencias;
	}

}
