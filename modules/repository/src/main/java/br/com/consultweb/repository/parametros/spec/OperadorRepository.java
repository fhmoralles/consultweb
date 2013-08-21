package br.com.consultweb.repository.parametros.spec;

import javax.ejb.Local;

import br.com.consultweb.domain.parametros.Operador;
import br.com.libutils.jpa.Repository;

@Local
public interface OperadorRepository extends Repository<Operador> {
	
	Operador login(Integer codigo);
	
}
