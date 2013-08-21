package br.com.consultweb.repository.spec;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.libutils.jpa.AbstractRepository;

public abstract class AbstractConsultWebRepository<T extends Serializable> extends
		AbstractRepository<T> {

	@PersistenceContext(unitName = "ConsultWebPU")
	private EntityManager em;

	protected AbstractConsultWebRepository(
			final Class<? extends T> entityConcreteClass) {
		super(entityConcreteClass);
	}

	@Override
	protected final EntityManager getEntityManager() {
		return em;
	}

}
