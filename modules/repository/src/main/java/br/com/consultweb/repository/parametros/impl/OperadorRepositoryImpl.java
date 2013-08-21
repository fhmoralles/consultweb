package br.com.consultweb.repository.parametros.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.consultweb.domain.parametros.Operador;
import br.com.consultweb.repository.parametros.spec.OperadorRepository;
import br.com.consultweb.repository.spec.AbstractConsultWebRepository;

@Stateless(name = "operadorRepository")
public class OperadorRepositoryImpl extends AbstractConsultWebRepository<Operador> implements
		OperadorRepository {

    public OperadorRepositoryImpl() {
    	super(Operador.class);
    }

	@Override
	public Operador login(Integer codigo) {
		
		final EntityManager em = getEntityManager();
		final CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
		final CriteriaQuery<Operador> criteriaQuery = createCriteriaQuery(criteriaBuilder);
		final Root<Operador> root = createRoot(criteriaQuery);
		
		/* Repository -> Apenas pesquias as informações no banco de dados
		 * Filtro por codigo */
		final Path<Integer> pathCodigo = root.get("codigo");
		final Predicate predicateCodigo = criteriaBuilder.equal(pathCodigo, codigo);
		criteriaQuery.where(predicateCodigo);

		criteriaQuery.where(predicateCodigo);
		
		final TypedQuery<Operador> typedQuery = em.createQuery(criteriaQuery);
		
		return ( (typedQuery.getResultList().size()) == 0 ? null : typedQuery.getResultList().get(0) );
		
	}
	   
    
}
