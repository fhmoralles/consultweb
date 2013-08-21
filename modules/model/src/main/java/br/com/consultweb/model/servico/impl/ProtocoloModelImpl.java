package br.com.consultweb.model.servico.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.consultweb.domain.servico.Protocolo;
import br.com.consultweb.model.servico.spec.ProtocoloModel;
import br.com.consultweb.repository.servico.spec.ProtocoloRepository;

@Stateless(name = "protocoloModel")
public class ProtocoloModelImpl implements ProtocoloModel {

	@EJB
	private ProtocoloRepository protocoloRepository;

	@Override
	public void create(Protocolo c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Protocolo update(Protocolo c) {
		return protocoloRepository.update(c);
	}
	
	@Override
	public Protocolo retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Protocolo c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Protocolo c) {
		// TODO Auto-generated method stub
		
	}

}
