package br.com.consultweb.repository.cadastro.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.consultweb.domain.types.TipoPessoa;
import br.com.libutils.jpa.Repository;

@Local
public interface TipoPessoaRepository extends Repository<TipoPessoa> {
	
	List<TipoPessoa> getTiposPessoa();
	
}
