package br.com.consultweb.model.cadastro.spec;

import javax.ejb.Local;

import br.com.consultweb.domain.cadastro.Contraparte;
import br.com.consultweb.model.spec.InterfaceConsultWebModel;

@Local
public interface ContraparteModel extends InterfaceConsultWebModel<Contraparte> {
	
	public Contraparte retrieveByCpf(String cpf);
	
}
