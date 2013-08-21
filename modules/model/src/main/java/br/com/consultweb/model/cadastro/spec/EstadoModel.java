package br.com.consultweb.model.cadastro.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.consultweb.domain.types.Estado;
import br.com.consultweb.model.spec.InterfaceConsultWebModel;

@Local
public interface EstadoModel extends InterfaceConsultWebModel<Estado> {

	List<Estado> getEstados();
	
}
