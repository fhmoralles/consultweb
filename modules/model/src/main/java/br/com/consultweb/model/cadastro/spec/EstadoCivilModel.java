package br.com.consultweb.model.cadastro.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.consultweb.domain.types.EstadoCivil;
import br.com.consultweb.model.spec.InterfaceConsultWebModel;

@Local
public interface EstadoCivilModel extends InterfaceConsultWebModel<EstadoCivil> {

	List<EstadoCivil> getEstadosCivil();
	
}
