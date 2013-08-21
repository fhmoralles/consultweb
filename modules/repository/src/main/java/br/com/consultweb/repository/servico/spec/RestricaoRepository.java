package br.com.consultweb.repository.servico.spec;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import br.com.consultweb.domain.servico.Restricao;
import br.com.libutils.jpa.Repository;

@Local
public interface RestricaoRepository extends Repository<Restricao> {

	List<Restricao> listagemRestricaoAssociado(Integer associadoCodigo, Date dataInicio, Date dataFim);	
	
}
