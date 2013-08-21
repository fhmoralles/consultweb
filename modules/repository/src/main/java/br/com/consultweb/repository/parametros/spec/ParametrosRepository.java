package br.com.consultweb.repository.parametros.spec;

import javax.ejb.Local;

import br.com.consultweb.domain.parametros.Parametros;
import br.com.libutils.jpa.Repository;

@Local
public interface ParametrosRepository extends Repository<Parametros> {
	
}
