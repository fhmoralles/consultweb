package br.com.consultweb.repository.servico.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.consultweb.domain.types.TipoDevedor;
import br.com.libutils.jpa.Repository;

@Local
public interface TipoDevedorRepository extends Repository<TipoDevedor> {
	
	List<TipoDevedor> getTiposDevedor();
	
}
