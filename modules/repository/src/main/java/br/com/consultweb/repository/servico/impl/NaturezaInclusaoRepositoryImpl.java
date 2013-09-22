package br.com.consultweb.repository.servico.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.consultweb.domain.servico.NaturezaInclusao;
import br.com.consultweb.repository.servico.spec.NaturezaInclusaoRepository;
import br.com.consultweb.repository.spec.AbstractConsultWebRepository;

@Stateless(name = "naturezaInclusaoRepository")
public class NaturezaInclusaoRepositoryImpl extends
		AbstractConsultWebRepository<NaturezaInclusao> implements
		NaturezaInclusaoRepository {

	public NaturezaInclusaoRepositoryImpl() {
		super(NaturezaInclusao.class);
	}

	@Override
	public List<NaturezaInclusao> getAll() {

		return retrieveByCriteria(null);

	}

	@Override
	public List<NaturezaInclusao> getNaturezaInclusaoPorDescricao(
			String descricao) {
		
		final EntityManager em = getEntityManager();
		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<NaturezaInclusao> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<NaturezaInclusao> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquisas as informações no banco de dados */
		final Path<String> pathCodigoDescricao = root.get("descricao");
		final Predicate predicateDescricao = criteriaBuilder.like(pathCodigoDescricao, descricao);
		criteriaQuery.where(predicateDescricao);
		
		return retrieveByCriteria(criteriaQuery);
	}


}
