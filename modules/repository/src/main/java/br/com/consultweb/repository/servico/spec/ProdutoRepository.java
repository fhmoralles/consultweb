package br.com.consultweb.repository.servico.spec;

import javax.ejb.Local;

import br.com.consultweb.domain.servico.Produto;
import br.com.libutils.jpa.Repository;

@Local
public interface ProdutoRepository extends Repository<Produto> {
	
}
