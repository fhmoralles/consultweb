package br.com.consultweb.repository.servico.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.consultweb.domain.servico.NaturezaInclusao;
import br.com.libutils.jpa.Repository;

@Local
public interface NaturezaInclusaoRepository extends Repository<NaturezaInclusao> {
	
	List<NaturezaInclusao> getAll();
	
}
