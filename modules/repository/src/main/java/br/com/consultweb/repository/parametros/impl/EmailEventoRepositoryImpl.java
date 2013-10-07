package br.com.consultweb.repository.parametros.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.consultweb.domain.parametros.EmailEvento;
import br.com.consultweb.domain.parametros.LogOperacao;
import br.com.consultweb.domain.servico.NaturezaInclusao;
import br.com.consultweb.domain.types.Evento;
import br.com.consultweb.repository.parametros.spec.EmailEventoRepository;
import br.com.consultweb.repository.parametros.spec.LogOperacaoRepository;
import br.com.consultweb.repository.spec.AbstractConsultWebRepository;

@Stateless(name = "emailsEventosRepository")
public class EmailEventoRepositoryImpl extends
		AbstractConsultWebRepository<EmailEvento> implements
		EmailEventoRepository {

	protected EmailEventoRepositoryImpl() {
		super(EmailEvento.class);
	}

	@Override
	public List<EmailEvento> getEmailsPorEvento(Evento evento) {

		final EntityManager em = getEntityManager();
		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<EmailEvento> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<EmailEvento> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquisas as informações no banco de dados */
		final Path<Evento> pathEvento = root.get("evento");
		final Predicate predicateEvento = criteriaBuilder.equal(pathEvento, evento);
		criteriaQuery.where(predicateEvento);
		
		return retrieveByCriteria(criteriaQuery);

	}

}
