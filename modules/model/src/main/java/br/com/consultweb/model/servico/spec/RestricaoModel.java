package br.com.consultweb.model.servico.spec;

import java.util.List;

import javax.ejb.Local;

import br.com.consultweb.domain.parametros.Operador;
import br.com.consultweb.domain.servico.Restricao;
import br.com.consultweb.domain.types.Dispositivo;
import br.com.consultweb.model.spec.InterfaceConsultWebModel;

@Local
public interface RestricaoModel extends
		InterfaceConsultWebModel<Restricao> {
	
	List<Restricao> listagemRestricaoAssociado(Integer associadoCodigo);
	
	Restricao incluirRestricao(Restricao restricao, Dispositivo dispositivo, Operador operador) throws Exception;
	
}
