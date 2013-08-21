package br.com.consultweb.model.servico.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.consultweb.domain.types.TipoDevedor;
import br.com.consultweb.model.spec.InterfaceConsultWebModel;

@Local
public interface TipoDevedorModel extends InterfaceConsultWebModel<TipoDevedor> {

	List<TipoDevedor> getTiposDevedor();
	
}
