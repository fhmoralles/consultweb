package br.com.consultweb.repository.servico.spec;

import javax.ejb.Local;

import br.com.consultweb.domain.servico.Faturamento;
import br.com.libutils.jpa.Repository;

@Local
public interface FaturamentoRepository extends Repository<Faturamento> {
	
}
