package br.com.consultweb.model.parametros.spec;

import javax.ejb.Local;

import br.com.consultweb.domain.parametros.Operador;
import br.com.consultweb.model.exceptions.OperadorDispositivoNaoAutorizadoException;
import br.com.consultweb.model.exceptions.OperadorHorarioNaoAutorizadoException;
import br.com.consultweb.model.exceptions.OperadorSenhaInvalidaException;
import br.com.consultweb.model.spec.InterfaceConsultWebModel;

@Local
public interface OperadorModel extends InterfaceConsultWebModel<Operador> {

	Operador login(Integer codigo, String senha)
			throws OperadorDispositivoNaoAutorizadoException,
			OperadorSenhaInvalidaException,
			OperadorHorarioNaoAutorizadoException;

}
