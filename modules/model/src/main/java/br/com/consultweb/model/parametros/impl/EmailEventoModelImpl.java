package br.com.consultweb.model.parametros.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.consultweb.domain.parametros.EmailEvento;
import br.com.consultweb.domain.types.Evento;
import br.com.consultweb.model.parametros.spec.EmailEventoModel;
import br.com.consultweb.repository.parametros.spec.EmailEventoRepository;

@Stateless(name = "emailEventoModel")
public class EmailEventoModelImpl implements EmailEventoModel {

	@EJB
	private EmailEventoRepository emailsEventosRepository;
	
	@Override
	public void create(EmailEvento c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EmailEvento retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmailEvento update(EmailEvento c) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(EmailEvento c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(EmailEvento c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EmailEvento> getEmailsPorEvento(Evento evento) {
		return emailsEventosRepository.getEmailsPorEvento(evento);
	}


}
