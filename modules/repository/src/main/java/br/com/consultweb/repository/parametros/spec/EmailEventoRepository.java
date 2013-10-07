package br.com.consultweb.repository.parametros.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.consultweb.domain.parametros.EmailEvento;
import br.com.consultweb.domain.types.Evento;
import br.com.libutils.jpa.Repository;

@Local
public interface EmailEventoRepository extends Repository<EmailEvento> {
	
	List<EmailEvento> getEmailsPorEvento(Evento evento);
	
}
