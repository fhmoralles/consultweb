package br.com.consultweb.repository.cadastro.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.consultweb.domain.types.Estado;
import br.com.libutils.jpa.Repository;

@Local
public interface EstadoRepository extends Repository<Estado> {
	
	List<Estado> getEstados();
	
}
