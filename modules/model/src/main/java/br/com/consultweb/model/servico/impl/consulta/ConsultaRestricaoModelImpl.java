package br.com.consultweb.model.servico.impl.consulta;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.consultweb.domain.servico.consulta.ConsultaRestricao;
import br.com.consultweb.domain.servico.consulta.ConsultaTipo;
import br.com.consultweb.model.servico.spec.consulta.ConsultaRestricaoModel;
import br.com.consultweb.model.servico.spec.consulta.ConsultaRestricaoRestricaoPadraoModel;

@Stateless(name = "consultaRestricaoModel")
public class ConsultaRestricaoModelImpl implements ConsultaRestricaoModel {

	@EJB
	private ConsultaRestricaoRestricaoPadraoModel consultaRestricaoRestricaoPadraoModel;
	
	@Override
	public List<ConsultaRestricao> geraConsultaRestricoes(ConsultaTipo consultaTipo) {
		
		switch (consultaTipo.getTipoConsulta()) {

		case RESTRICAOPADRAO:
			return consultaRestricaoRestricaoPadraoModel.geraConsultaRestricoes(consultaTipo);
		
		default:
			return null;
		
		}
	}
	
}
