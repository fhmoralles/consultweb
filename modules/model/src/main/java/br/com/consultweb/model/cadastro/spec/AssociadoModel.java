package br.com.consultweb.model.cadastro.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.consultweb.domain.cadastro.Associado;
import br.com.consultweb.domain.cadastro.Entidade;
import br.com.consultweb.domain.servico.Produto;
import br.com.consultweb.model.spec.InterfaceConsultWebModel;

@Local
public interface AssociadoModel extends InterfaceConsultWebModel<Associado> {
	
	public Associado localizarPorAssociadoEEntidade(Integer codigoAssociado, Entidade entidade);
	
	public List<Produto> getConsultasAssociado(Associado associado);
	
	public List<Associado> getAssociadosPorFantasia(String fantasia);
	
}
