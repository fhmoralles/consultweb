package br.com.consultweb.repository.servico.spec;

import javax.ejb.Local;

import br.com.consultweb.domain.servico.Consulta;
import br.com.libutils.jpa.Repository;

@Local
public interface ConsultaRepository extends Repository<Consulta> {
	
}
