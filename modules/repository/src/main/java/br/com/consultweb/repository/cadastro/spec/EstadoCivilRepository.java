package br.com.consultweb.repository.cadastro.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.consultweb.domain.types.EstadoCivil;
import br.com.libutils.jpa.Repository;

@Local
public interface EstadoCivilRepository extends Repository<EstadoCivil> {
	
	List<EstadoCivil> getEstadosCivil();
	
}
