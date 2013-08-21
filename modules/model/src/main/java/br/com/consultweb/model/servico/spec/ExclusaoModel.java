package br.com.consultweb.model.servico.spec;

import javax.ejb.Local;

import br.com.consultweb.domain.servico.Exclusao;
import br.com.consultweb.model.spec.InterfaceConsultWebModel;

@Local
public interface ExclusaoModel extends
		InterfaceConsultWebModel<Exclusao> {
	
}
