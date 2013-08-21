package br.com.consultweb.repository.servico.impl;

import javax.ejb.Stateless;

import br.com.consultweb.domain.servico.Protocolo;
import br.com.consultweb.repository.servico.spec.ProtocoloRepository;
import br.com.consultweb.repository.spec.AbstractConsultWebRepository;

@Stateless(name = "protocoloRepository")
public class ProtocoloRepositoryImpl extends
		AbstractConsultWebRepository<Protocolo> implements
		ProtocoloRepository {

	public ProtocoloRepositoryImpl() {
		super(Protocolo.class);
	}

}
