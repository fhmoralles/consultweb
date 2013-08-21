package br.com.consultweb.repository.servico.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.xml.crypto.Data;

import br.com.consultweb.domain.servico.Restricao;
import br.com.consultweb.repository.servico.spec.RestricaoRepository;
import br.com.consultweb.repository.spec.AbstractConsultWebRepository;

@Stateless(name = "restricaoRepository")
public class RestricaoRepositoryImpl extends
		AbstractConsultWebRepository<Restricao> implements
		RestricaoRepository {

	public RestricaoRepositoryImpl() {
		super(Restricao.class);
	}

	@Override
	public List<Restricao> listagemRestricaoAssociado(Integer associadoCodigo,
			Date dataInicio, Date dataFim) {

		/* Gerar o ultimo registro de Restricao encontrado */
		Query query = getEntityManager().createNamedQuery("queryRestricoesAssociado");
		query.setParameter("codigoAssociado", associadoCodigo);
		query.setParameter("dataInicio", dataInicio);
		query.setParameter("dataFim", dataFim);

		return query.getResultList();
	}
	
}
