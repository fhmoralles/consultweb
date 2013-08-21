package br.com.consultweb.model.cadastro.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.consultweb.domain.types.Sexo;
import br.com.consultweb.model.spec.InterfaceConsultWebModel;

@Local
public interface SexoModel extends InterfaceConsultWebModel<Sexo> {

	List<Sexo> getSexos();
	
}
