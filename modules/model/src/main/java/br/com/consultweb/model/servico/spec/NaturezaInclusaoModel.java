package br.com.consultweb.model.servico.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.consultweb.domain.servico.NaturezaInclusao;
import br.com.consultweb.model.spec.InterfaceConsultWebModel;

@Local
public interface NaturezaInclusaoModel extends
		InterfaceConsultWebModel<NaturezaInclusao> {
	
	List<NaturezaInclusao> getAll();
	
}
