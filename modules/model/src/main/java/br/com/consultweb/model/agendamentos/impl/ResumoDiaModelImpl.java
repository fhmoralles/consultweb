package br.com.consultweb.model.agendamentos.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timer;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import br.com.consultweb.domain.parametros.EmailEvento;
import br.com.consultweb.domain.parametros.LogOperacao;
import br.com.consultweb.domain.servico.resumo.MovimentacoesProtocolosDia;
import br.com.consultweb.domain.types.Evento;
import br.com.consultweb.model.agendamentos.spec.ResumoDiaModel;
import br.com.consultweb.model.parametros.spec.EmailEventoModel;
import br.com.consultweb.model.parametros.spec.LogOperacaoModel;
import br.com.consultweb.repository.servico.spec.ProtocoloRepository;
import br.com.libutils.email.EmailHelper;
import br.com.libutils.email.EmailHelper.TipoEmail;
import br.com.libutils.exception.EmailNotSendException;

@Stateless
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class ResumoDiaModelImpl implements ResumoDiaModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1392589637514007822L;

	private static final Logger LOG = Logger
			.getLogger(ResumoDiaModelImpl.class);

	@EJB
	private ProtocoloRepository protocoloRepository;

	@EJB
	private LogOperacaoModel logOperacaoModel;

	@EJB
	private EmailEventoModel emailsEventosModel;

	@Override
	@Schedule(dayOfMonth = "*", month = "*", year = "*", second = "1", minute = "0", hour = "0")
	public void resumoServicosDia(Timer time) {

		String msg = StringUtils.EMPTY;
		Calendar dataMovimento = Calendar.getInstance();
		dataMovimento.setTimeInMillis(System.currentTimeMillis());
		dataMovimento.set(Calendar.DATE, 9);
		dataMovimento.set(Calendar.MONTH, 8);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		dataMovimento.add(Calendar.DATE, -1);

		try {
		
			/* Recupera as movimentações */
			MovimentacoesProtocolosDia movimentacoesProtocolosDia = protocoloRepository
					.getMovimentacoesProtocolosDia(dataMovimento.getTime());
	
			if(movimentacoesProtocolosDia == null) {
				throw new EmailNotSendException("Movimento Protocolo do Dia não encontrado!!!");
			}
			
			/* Recuperar os Emails */
			StringBuilder destinos = new StringBuilder(StringUtils.EMPTY);
			List<EmailEvento> emailsEventos = emailsEventosModel
					.getEmailsPorEvento(Evento.MOVIMENTACOESPROTOCOLOSDIA);
			for (EmailEvento emailEvento : emailsEventos) {
				destinos.append(emailEvento.getEmail() + "; ");
			}
	
			/* Criar no messages */
			String assunto = "Consult: Resumo do dia "
					+ sdf.format(dataMovimento.getTime());
	
			StringBuilder mensagem = new StringBuilder();
			mensagem.append("<span style='font-family:verdana,sans-serif'>");
			mensagem.append("<u>Total de Movimentacoes: <b>"
					+ movimentacoesProtocolosDia.getTotalMovimentacoes()
					+ "</u></b><br>");
			mensagem.append("Total de Consultas: <i>"
					+ movimentacoesProtocolosDia.getConsultas() + "("
					+ movimentacoesProtocolosDia.getPercConsultas().doubleValue()
					+ "%)</i><br>");
			mensagem.append("Total de Restricoes: <i>"
					+ movimentacoesProtocolosDia.getRestricoes() + "("
					+ movimentacoesProtocolosDia.getPercRestricoes().doubleValue()
					+ "%)</i><br>");
			mensagem.append("Total de Exclusoes: <i>"
					+ movimentacoesProtocolosDia.getExclusoes() + "("
					+ movimentacoesProtocolosDia.getPercExclusoes().doubleValue()
					+ "%)</i>");
			mensagem.append("</span>");

			System.out.println(destinos.toString() + "\n" + assunto + "\n"
					+ mensagem.toString());
			if (destinos.toString().equals(StringUtils.EMPTY)) {
				throw new EmailNotSendException(
						"Sem destinos definidos paro evento "
								+ Evento.MOVIMENTACOESPROTOCOLOSDIA.toString());
			}
			// EmailHelper.enviarEmail(destinos.toString(), assunto,
			// mensagem.toString(),
			// TipoEmail.HTML, null);
			msg = "Fim Envio de Resumo do dia "
					+ sdf.format(dataMovimento.getTime()) + ": Enviado -> "
					+ movimentacoesProtocolosDia;
		} catch (EmailNotSendException e) {
			LOG.error(e);
			msg = "Fim Envio de Resumo do dia "
					+ sdf.format(dataMovimento.getTime()) + ": Não Enviado -> "
					+ e.getMessage();
		} catch (Exception e) {
			LOG.error(e);
			msg = "Fim Envio de Resumo do dia "
					+ sdf.format(dataMovimento.getTime()) + ": Não Enviado -> "
					+ e.getMessage();
		}

		/* Inserindo Log */
		LogOperacao logOperacao = new LogOperacao();
		logOperacao = new LogOperacao();
		logOperacao.setDescricao(msg);
		logOperacao.setOperacao(Evento.MOVIMENTACOESPROTOCOLOSDIA.toString());
		logOperacao.setOperador(null);
		try {
			logOperacao = logOperacaoModel.update(logOperacao);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}

	}

}
