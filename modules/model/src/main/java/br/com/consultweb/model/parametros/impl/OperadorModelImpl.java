package br.com.consultweb.model.parametros.impl;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import br.com.consultweb.domain.parametros.Operador;
import br.com.consultweb.domain.parametros.OperadorDispositivo;
import br.com.consultweb.domain.parametros.OperadorHorario;
import br.com.consultweb.domain.types.Dispositivo;
import br.com.consultweb.model.exceptions.OperadorDispositivoNaoAutorizadoException;
import br.com.consultweb.model.exceptions.OperadorHorarioNaoAutorizadoException;
import br.com.consultweb.model.exceptions.OperadorSenhaInvalidaException;
import br.com.consultweb.model.parametros.spec.OperadorModel;
import br.com.consultweb.repository.parametros.spec.OperadorRepository;

@Stateless(name = "operadorModel")
public class OperadorModelImpl implements OperadorModel {

	private static final Logger LOG = Logger.getLogger(OperadorModelImpl.class);

	@EJB
	private OperadorRepository operadorRepository;

	@Override
	public void create(Operador c) {
		// TODO Auto-generated method stub
	}

	@Override
	public Operador retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Operador update(Operador c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Operador c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(Operador c) {
		// TODO Auto-generated method stub

	}

	@Override
	public Operador login(Integer codigo, String senha)
			throws OperadorDispositivoNaoAutorizadoException,
			OperadorSenhaInvalidaException,
			OperadorHorarioNaoAutorizadoException {

		// Recupera o operador
		Operador operador = operadorRepository.login(codigo);

		if (operador != null) {
			
			// Verifica se o operador pode se logar através da Web
			boolean dispositivoAutorizado = false;
			boolean hoarioAutorizado = false;

			List<OperadorDispositivo> operadorDispositivos = operador
					.getOperadorDispositivos();

			for (OperadorDispositivo operadorDispositivo : operadorDispositivos) {

				if (operadorDispositivo.getAssociadoDispositivo()
						.getDispositivo().equals(Dispositivo.INTERNET)) {

					// Operador está autorizado a entrar através do dispostivo internet
					dispositivoAutorizado = true;
					
					// Verificar a senha
					System.out.println("operadorDispositivo.getSenha(): " + operadorDispositivo.getSenha());
					LOG.info("operadorDispositivo.getSenha(): " + operadorDispositivo.getSenha());
					if (!operadorDispositivo.getSenha().equals(senha)) {
						throw new OperadorSenhaInvalidaException();
					} else {
						// Verificar se os horários condizem
						List<OperadorHorario> operadorHorarios = operador
								.getOperadorHorarios();

						// Recupera o dia da semana de hoje
						Integer diaSemana = Calendar.getInstance().get(
								Calendar.DAY_OF_WEEK);
						// Recupera o horário de agora
						Integer horario = Calendar.getInstance().get(
								Calendar.HOUR_OF_DAY);
						
						System.out.println("diaSemana: " + diaSemana + "; horario: " + horario);
						
						// Percorre os horários cadastrados para o operador
						for(OperadorHorario operadorHorario: operadorHorarios) {
							
							System.out.println("operadorHorario.getDiaSemana(): " + operadorHorario.getDiaSemana() + "; operadorHorario.getHoraInicio(): " + operadorHorario.getHoraInicio() + "; operadorHorario.getHoraTermino():" + operadorHorario.getHoraTermino());

							//Verifica se o dia da semana está cadastrado
							if(Integer.parseInt(operadorHorario.getDiaSemana().toString()) == diaSemana) {
								
								//Verifica se o horário estã dentro do intervalo permitido
								if(horario >= operadorHorario.getHoraInicio() && horario <= operadorHorario.getHoraTermino()) {
									hoarioAutorizado = true;
								}
							}
						}
					}

					// Sai do laço do for
					break;
				}
			}

			if (!dispositivoAutorizado) {
				throw new OperadorDispositivoNaoAutorizadoException();
			} else if (!hoarioAutorizado) {
				throw new OperadorHorarioNaoAutorizadoException();
			}
			
		}

		return operador;
	}

}
