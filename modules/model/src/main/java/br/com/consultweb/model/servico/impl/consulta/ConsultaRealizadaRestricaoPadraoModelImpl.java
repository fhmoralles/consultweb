package br.com.consultweb.model.servico.impl.consulta;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.consultweb.domain.servico.consulta.ConsultaRealizada;
import br.com.consultweb.domain.servico.consulta.ConsultaTipo;
import br.com.consultweb.model.servico.spec.consulta.ConsultaRealizadaRestricaoPadraoModel;
import br.com.consultweb.repository.servico.spec.consulta.ConsultaRealizadaRepository;

@Stateless(name = "consultaRealizadaRestricaoPadraoModel")
public class ConsultaRealizadaRestricaoPadraoModelImpl implements
		ConsultaRealizadaRestricaoPadraoModel {

	@EJB
	private ConsultaRealizadaRepository consultaRealizadaRepository;
	
	@Override
	public List<ConsultaRealizada> geraConsultaRealizadas(
			ConsultaTipo consultaTipo) {
		
		/* Registros de Incluções para Consulta tipo RestricaoPadrao */
		return consultaRealizadaRepository.gerarConsultaRealizadasPadrao(consultaTipo);

	}

}
