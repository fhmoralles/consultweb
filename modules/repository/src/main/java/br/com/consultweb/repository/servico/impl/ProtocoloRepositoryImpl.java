package br.com.consultweb.repository.servico.impl;

import java.util.Calendar;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.consultweb.domain.servico.Protocolo;
import br.com.consultweb.repository.servico.spec.ProtocoloRepository;
import br.com.consultweb.repository.spec.AbstractConsultWebRepository;

@Stateless(name = "protocoloRepository")
public class ProtocoloRepositoryImpl extends
		AbstractConsultWebRepository<Protocolo> implements
		ProtocoloRepository {

	public ProtocoloRepositoryImpl() {
		super(Protocolo.class);
	}

	@Override
	public Integer getQtdeProtocolosConsultaDiaAnterior(Calendar dataResumo) {
		
		Calendar dataGeracaoInicio = (Calendar)dataResumo.clone();
		dataGeracaoInicio.add(Calendar.DATE, -1);
		dataGeracaoInicio.set(Calendar.HOUR, 0);
		dataGeracaoInicio.set(Calendar.MINUTE, 0);
		dataGeracaoInicio.set(Calendar.SECOND, 0);

		Calendar dataGeracaoFim = (Calendar)dataResumo.clone();
		dataGeracaoFim.setTimeInMillis(System.currentTimeMillis());
		dataGeracaoFim.add(Calendar.DATE, -1);
		dataGeracaoFim.set(Calendar.HOUR, 23);
		dataGeracaoFim.set(Calendar.MINUTE, 59);
		dataGeracaoFim.set(Calendar.SECOND, 59);
		
		/* Gerar o ultimo registro de Restricao encontrado */
		Query query = getEntityManager().createNamedQuery("queryProtocoloConsultasDia");
		query.setParameter("dataGeracaoInicio", dataGeracaoInicio.getTime());
		query.setParameter("dataGeracaoFim", dataGeracaoFim.getTime());

		return query.getResultList().size();
	}

	@Override
	public Integer getQtdeProtocolosRestricaoDiaAnterior(Calendar dataResumo) {
	
		Calendar dataGeracaoInicio = (Calendar)dataResumo.clone();
		dataGeracaoInicio.setTimeInMillis(System.currentTimeMillis());
		dataGeracaoInicio.add(Calendar.DATE, -1);
		dataGeracaoInicio.set(Calendar.HOUR, 0);
		dataGeracaoInicio.set(Calendar.MINUTE, 0);
		dataGeracaoInicio.set(Calendar.SECOND, 0);

		Calendar dataGeracaoFim = (Calendar)dataResumo.clone();
		dataGeracaoFim.setTimeInMillis(System.currentTimeMillis());
		dataGeracaoFim.add(Calendar.DATE, -1);
		dataGeracaoFim.set(Calendar.HOUR, 23);
		dataGeracaoFim.set(Calendar.MINUTE, 59);
		dataGeracaoFim.set(Calendar.SECOND, 59);
		
		/* Gerar o ultimo registro de Restricao encontrado */
		Query query = getEntityManager().createNamedQuery("queryProtocoloRestricoesDia");
		query.setParameter("dataGeracaoInicio", dataGeracaoInicio.getTime());
		query.setParameter("dataGeracaoFim", dataGeracaoFim.getTime());

		return query.getResultList().size();
	}

	@Override
	public Integer getQtdeProtocolosExclusaoDiaAnterior(Calendar dataResumo) {

		Calendar dataGeracaoInicio = (Calendar)dataResumo.clone();
		dataGeracaoInicio.setTimeInMillis(System.currentTimeMillis());
		dataGeracaoInicio.add(Calendar.DATE, -1);
		dataGeracaoInicio.set(Calendar.HOUR, 0);
		dataGeracaoInicio.set(Calendar.MINUTE, 0);
		dataGeracaoInicio.set(Calendar.SECOND, 0);

		Calendar dataGeracaoFim = (Calendar)dataResumo.clone();
		dataGeracaoFim.setTimeInMillis(System.currentTimeMillis());
		dataGeracaoFim.add(Calendar.DATE, -1);
		dataGeracaoFim.set(Calendar.HOUR, 23);
		dataGeracaoFim.set(Calendar.MINUTE, 59);
		dataGeracaoFim.set(Calendar.SECOND, 59);
		
		/* Gerar o ultimo registro de Restricao encontrado */
		Query query = getEntityManager().createNamedQuery("queryProtocoloExclusoesDia");
		query.setParameter("dataGeracaoInicio", dataGeracaoInicio.getTime());
		query.setParameter("dataGeracaoFim", dataGeracaoFim.getTime());

		return query.getResultList().size();
	}

}
