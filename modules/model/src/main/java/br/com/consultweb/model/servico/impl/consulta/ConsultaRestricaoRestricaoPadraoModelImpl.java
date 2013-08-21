package br.com.consultweb.model.servico.impl.consulta;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.consultweb.domain.servico.consulta.ConsultaRestricao;
import br.com.consultweb.domain.servico.consulta.ConsultaTipo;
import br.com.consultweb.model.servico.spec.consulta.ConsultaRestricaoRestricaoPadraoModel;
import br.com.consultweb.repository.servico.spec.consulta.ConsultaRestricaoRepository;

@Stateless(name = "consultaRestricaoRestricaoPadraoModel")
public class ConsultaRestricaoRestricaoPadraoModelImpl implements
		ConsultaRestricaoRestricaoPadraoModel {

	@EJB
	private ConsultaRestricaoRepository consultaRestricaoRepository;
	
	@Override
	public List<ConsultaRestricao> geraConsultaRestricoes(
			ConsultaTipo consultaTipo) {
		
		/* Registros de Incluções para Consulta tipo RestricaoPadrao */
		return consultaRestricaoRepository.gerarRestricoesRestricaoPadrao(consultaTipo);

	}

}
