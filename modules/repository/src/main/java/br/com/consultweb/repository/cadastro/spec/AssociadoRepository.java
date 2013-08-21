package br.com.consultweb.repository.cadastro.spec;

import javax.ejb.Local;

import br.com.consultweb.domain.cadastro.Associado;
import br.com.consultweb.domain.cadastro.Entidade;
import br.com.libutils.jpa.Repository;

@Local
public interface AssociadoRepository extends Repository<Associado> {

	public Associado localizarPorAssociadoEEntidade(Integer codigoAssociado, Entidade entidade);
	
}
