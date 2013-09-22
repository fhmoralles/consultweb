package br.com.consultweb.repository.cadastro.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.consultweb.domain.cadastro.Associado;
import br.com.consultweb.domain.cadastro.Entidade;
import br.com.consultweb.repository.cadastro.spec.AssociadoRepository;
import br.com.consultweb.repository.spec.AbstractConsultWebRepository;

@Stateless(name = "associadoRepository")
public class AssociadoRepositoryImpl extends
		AbstractConsultWebRepository<Associado> implements
		AssociadoRepository {

	public AssociadoRepositoryImpl() {
		super(Associado.class);
	}

	@Override
	public Associado localizarPorAssociadoEEntidade(Integer codigoAssociado, Entidade entidade) {
		
		final EntityManager em = getEntityManager();
		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<Associado> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<Associado> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquisas as informações no banco de dados */
		/* Filtro por codigo de Associado */
		final Path<Integer> pathCodigoAssociado = root.get("codigo");
		final Predicate predicateCodigoAssociado = criteriaBuilder.equal(pathCodigoAssociado, codigoAssociado);

		/* Filtro por codigo de Entidade */
		final Path<Entidade> pathEntidade = root.get("entidade");
		final Predicate predicateEntidade = criteriaBuilder.equal(pathEntidade, entidade);
		
		criteriaQuery.where(predicateCodigoAssociado, predicateEntidade);
		
		final TypedQuery<Associado> typedQuery = em.createQuery(criteriaQuery);
		
		return ( (typedQuery.getResultList().size()) == 0 ? null : typedQuery.getResultList().get(0) );

	}

	@Override
	public List<Associado> getAssociadosPorFantasia(String fantasia) {

		final EntityManager em = getEntityManager();
		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<Associado> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<Associado> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquisas as informações no banco de dados */
		/* Filtro por codigo de Associado */
		final Path<String> pathFantasia = root.get("fantasia");
		final Predicate predicateFantasia = criteriaBuilder.like(pathFantasia, fantasia);
		criteriaQuery.where(predicateFantasia);
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("fantasia")));
		
		final TypedQuery<Associado> typedQuery = em.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
		
	}

	
}
