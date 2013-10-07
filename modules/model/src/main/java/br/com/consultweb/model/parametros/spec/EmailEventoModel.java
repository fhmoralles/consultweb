package br.com.consultweb.model.parametros.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.consultweb.domain.parametros.EmailEvento;
import br.com.consultweb.domain.types.Evento;
import br.com.consultweb.model.spec.InterfaceConsultWebModel;

@Local
public interface EmailEventoModel extends InterfaceConsultWebModel<EmailEvento> {

	List<EmailEvento> getEmailsPorEvento(Evento evento);
	
}
