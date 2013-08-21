package br.com.consultweb.model.servico.impl.consulta;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.consultweb.domain.servico.consulta.ConsultaRealizada;
import br.com.consultweb.domain.servico.consulta.ConsultaTipo;
import br.com.consultweb.model.servico.spec.consulta.ConsultaRealizadaModel;
import br.com.consultweb.model.servico.spec.consulta.ConsultaRealizadaRestricaoPadraoModel;

@Stateless(name = "consultaRealizadaModel")
public class ConsultaRealizadaModelImpl implements ConsultaRealizadaModel {

	@EJB
	private ConsultaRealizadaRestricaoPadraoModel consultaRealizadaRestricaoPadraoModel;
	
	@Override
	public List<ConsultaRealizada> geraConsultaRealizadas(ConsultaTipo consultaTipo) {
		
		switch (consultaTipo.getTipoConsulta()) {

		case RESTRICAOPADRAO:
			return consultaRealizadaRestricaoPadraoModel.geraConsultaRealizadas(consultaTipo);
		
		default:
			return null;
		
		}
	}
	
}
