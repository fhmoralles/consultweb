package br.com.consultweb.repository.cadastro.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.consultweb.domain.cadastro.Contraparte;
import br.com.consultweb.repository.cadastro.spec.ContraparteRepository;
import br.com.consultweb.repository.spec.AbstractConsultWebRepository;

@Stateless(name = "contraparteRepository")
public class ContraparteRepositoryImpl extends
		AbstractConsultWebRepository<Contraparte> implements
		ContraparteRepository {

	public ContraparteRepositoryImpl() {
		super(Contraparte.class);
	}

	@Override
	public Contraparte retrieveByCpf(String cpf) {
		
		final EntityManager em = getEntityManager();
		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<Contraparte> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<Contraparte> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquias as informações no banco de dados
		 * Filtro por cpf */
		final Path<String> pathCpf = root.get("cpf");
		final Predicate predicateCpf = criteriaBuilder.equal(pathCpf, cpf);
		criteriaQuery.where(predicateCpf);

		criteriaQuery.where(predicateCpf);
		
		final TypedQuery<Contraparte> typedQuery = em.createQuery(criteriaQuery);
		
		return ( (typedQuery.getResultList().size()) == 0 ? null : typedQuery.getResultList().get(0) );

	}

	
}
