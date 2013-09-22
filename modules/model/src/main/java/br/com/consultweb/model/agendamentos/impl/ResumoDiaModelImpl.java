package br.com.consultweb.model.agendamentos.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timer;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import br.com.consultweb.domain.parametros.LogOperacao;
import br.com.consultweb.model.agendamentos.spec.ResumoDiaModel;
import br.com.consultweb.model.parametros.spec.LogOperacaoModel;
import br.com.consultweb.model.servico.impl.RestricaoModelImpl;
import br.com.consultweb.repository.servico.spec.ProtocoloRepository;
import br.com.libutils.email.EmailHelper;
import br.com.libutils.exception.EmailNotSendException;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class ResumoDiaModelImpl implements ResumoDiaModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1392589637514007822L;
	
	private static final Logger LOG = Logger.getLogger(ResumoDiaModelImpl.class);
	
	@EJB
	private ProtocoloRepository protocoloRepository;
	
	@EJB
	private LogOperacaoModel logOperacaoModel;
	
	@Override
	@Schedule(dayOfMonth = "*", month = "*", year = "*", second = "0",
			minute = "*", hour = "*")
	public void resumoServicosDia(Timer time) {
		
		String msg = StringUtils.EMPTY;
		Calendar dataResumo = Calendar.getInstance();
		dataResumo.setTimeInMillis(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		dataResumo.add(Calendar.DATE, -1);
		
		/* Inserindo Log */
		LogOperacao logOperacao = new LogOperacao();
		logOperacao.setDescricao("Inicio Envio de Resumo do dia " + sdf.format(dataResumo.getTime()));
		logOperacao.setOperacao("RESUMODIA");
		logOperacao.setOperador(null);
		try {
			logOperacao = logOperacaoModel.update(logOperacao);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		
		Integer consultas = protocoloRepository.getQtdeProtocolosConsultaDiaAnterior(dataResumo);
		Integer restricoes = protocoloRepository.getQtdeProtocolosRestricaoDiaAnterior(dataResumo);
		Integer exclusoes = protocoloRepository.getQtdeProtocolosExclusaoDiaAnterior(dataResumo);
		
		String destino = "fhmoralles@gmail.com";
		String assunto = "Consult: Resumo do dia " + sdf.format(dataResumo.getTime());
		String mensagem = "Total de Movimentações: " + (consultas + restricoes + exclusoes) + "\n" +
				"Total de Consultas: " + consultas + "\n" +
				"Total de Restrições: " + restricoes + "\n" +
				"Total de Exclusões: " + exclusoes;
		
		try {
			LOG.info(mensagem);
			EmailHelper.enviarEmail(destino, assunto, mensagem, null);
			msg = "Fim Envio de Resumo do dia " + sdf.format(dataResumo.getTime()) + ": Enviado";
		} catch (EmailNotSendException e) {
			LOG.error(e.getMessage(), e);
			msg = "Fim Envio de Resumo do dia " + sdf.format(dataResumo.getTime()) + ": Não Enviado";
		}
		
		/* Inserindo Log */
		logOperacao = new LogOperacao();
		logOperacao.setDescricao(msg);
		logOperacao.setOperacao("RESUMODIA");
		logOperacao.setOperador(null);
		try {
			logOperacao = logOperacaoModel.update(logOperacao);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		
	}

}
