package br.com.consultweb.model.servico.spec;

import javax.ejb.Local;

import br.com.consultweb.domain.servico.Protocolo;
import br.com.consultweb.model.spec.InterfaceConsultWebModel;

@Local
public interface ProtocoloModel extends
		InterfaceConsultWebModel<Protocolo> {


}
