package br.com.consultweb.model.cadastro.spec;


import java.util.List;

import javax.ejb.Local;

import br.com.consultweb.domain.types.TipoPessoa;
import br.com.consultweb.model.spec.InterfaceConsultWebModel;

@Local
public interface TipoPessoaModel extends InterfaceConsultWebModel<TipoPessoa> {

	List<TipoPessoa> getTiposPessoa();
	
}
