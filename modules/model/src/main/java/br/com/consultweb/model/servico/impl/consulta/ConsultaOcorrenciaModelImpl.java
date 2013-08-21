package br.com.consultweb.model.servico.impl.consulta;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.consultweb.domain.servico.consulta.ConsultaOcorrencia;
import br.com.consultweb.domain.servico.consulta.ConsultaTipo;
import br.com.consultweb.model.servico.spec.consulta.ConsultaOcorrenciaModel;
import br.com.consultweb.model.servico.spec.consulta.ConsultaOcorrenciaRestricaoPadraoModel;

@Stateless(name = "consultaOcorrenciaModel")
public class ConsultaOcorrenciaModelImpl implements ConsultaOcorrenciaModel {

	@EJB
	private ConsultaOcorrenciaRestricaoPadraoModel consultaOcorrenciaRestricaoPadraoModel;
	
	@Override
	public List<ConsultaOcorrencia> geraConsultaOcorrencias(ConsultaTipo consultaTipo) {
		
		switch (consultaTipo.getTipoConsulta()) {

		case RESTRICAOPADRAO:
			return consultaOcorrenciaRestricaoPadraoModel.geraConsultaOcorrencias(consultaTipo);
		
		default:
			return null;
		
		}
	}
	
}
