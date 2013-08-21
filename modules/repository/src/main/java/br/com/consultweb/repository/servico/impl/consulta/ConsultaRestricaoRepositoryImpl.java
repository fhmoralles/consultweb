package br.com.consultweb.repository.servico.impl.consulta;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.consultweb.domain.servico.Restricao;
import br.com.consultweb.domain.servico.consulta.ConsultaRestricao;
import br.com.consultweb.domain.servico.consulta.ConsultaTipo;
import br.com.consultweb.repository.servico.spec.consulta.ConsultaRestricaoRepository;
import br.com.consultweb.repository.spec.AbstractConsultWebRepository;

@Stateless(name = "consultaRestricaoRepository")
public class ConsultaRestricaoRepositoryImpl extends
		AbstractConsultWebRepository<ConsultaRestricao> implements
		ConsultaRestricaoRepository {

	protected ConsultaRestricaoRepositoryImpl() {
		super(ConsultaRestricao.class);
	}

	@Override
	public List<ConsultaRestricao> gerarRestricoesRestricaoPadrao(
			ConsultaTipo consultaTipo) {

		List<ConsultaRestricao> consultaRestricoes = new ArrayList<ConsultaRestricao>();

		/* Gerar data de Inicio e Fim */
		Calendar dataInicio = Calendar.getInstance();
		Calendar dataInvalida = Calendar.getInstance();
		dataInvalida.set(Calendar.DATE, 1);
		dataInvalida.set(Calendar.MONTH, 0);
		dataInvalida.set(Calendar.YEAR, 1900);
		

		/* Gerar o ultimo registro de Restricao encontrado */
		Query query = getEntityManager().createNamedQuery("queryRestricoes");
		query.setParameter("cpf", consultaTipo.getConsulta().getContraparte()
				.getCpf());
		query.setParameter("dataInicio", dataInicio.getTime());
		query.setParameter("dataFim", dataInicio.getTime());

		@SuppressWarnings(value = "unchecked")
		List<Restricao> queryRestricoes = query.getResultList();
		
		if (queryRestricoes.size() > 0) {
			
			int indice = 1;
			Iterator<Restricao> ite = queryRestricoes.iterator();
			
			while(ite.hasNext()) {
				
				Restricao restricao = ite.next();
				
				ConsultaRestricao consultaRestricao = new ConsultaRestricao();
				consultaRestricao.setIndice(indice++);
				consultaRestricao.setConsultaTipo(consultaTipo);
				consultaRestricao.setRestricao(restricao);
				consultaRestricoes.add(consultaRestricao);
				
			}
			
		}

		return consultaRestricoes;
	}

}
