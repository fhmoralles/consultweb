package br.com.consultweb.model.parametros.spec;

import javax.ejb.Local;

import br.com.consultweb.domain.parametros.Parametros;
import br.com.consultweb.model.spec.InterfaceConsultWebModel;

@Local
public interface ParametrosModel extends InterfaceConsultWebModel<Parametros> {

	public Parametros locate();
	
}
