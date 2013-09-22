package br.com.consultweb.model.servico.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.consultweb.domain.parametros.Operador;
import br.com.consultweb.domain.servico.NaturezaInclusao;
import br.com.consultweb.model.spec.InterfaceConsultWebModel;

@Local
public interface NaturezaInclusaoModel extends
		InterfaceConsultWebModel<NaturezaInclusao> {
	
	List<NaturezaInclusao> getAll();
	
	List<NaturezaInclusao> getNaturezaInclusaoPorDescricao(String descricao);
	
	NaturezaInclusao update(NaturezaInclusao c, Operador o) throws Exception;

	void delete(NaturezaInclusao c, Operador o) throws Exception;
	
}
