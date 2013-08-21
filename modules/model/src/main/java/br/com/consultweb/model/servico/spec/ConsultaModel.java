package br.com.consultweb.model.servico.spec;

import javax.ejb.Local;

import br.com.consultweb.domain.servico.Consulta;
import br.com.consultweb.domain.servico.Produto;
import br.com.consultweb.model.spec.InterfaceConsultWebModel;

@Local
public interface ConsultaModel extends
		InterfaceConsultWebModel<Consulta> {

	Consulta gerarConsulta(Consulta consulta, Produto produto) throws Exception;
	
}
