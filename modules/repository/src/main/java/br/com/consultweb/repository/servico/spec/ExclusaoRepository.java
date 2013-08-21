package br.com.consultweb.repository.servico.spec;

import javax.ejb.Local;

import br.com.consultweb.domain.servico.Exclusao;
import br.com.libutils.jpa.Repository;

@Local
public interface ExclusaoRepository extends Repository<Exclusao> {
	
}
