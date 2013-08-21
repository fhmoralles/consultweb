package br.com.consultweb.repository.servico.impl.consulta;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.consultweb.domain.servico.Consulta;
import br.com.consultweb.domain.servico.Restricao;
import br.com.consultweb.domain.servico.consulta.ConsultaOcorrencia;
import br.com.consultweb.domain.servico.consulta.ConsultaTipo;
import br.com.consultweb.repository.servico.spec.consulta.ConsultaOcorrenciaRepository;
import br.com.consultweb.repository.spec.AbstractConsultWebRepository;

@Stateless(name = "consultaOcorrenciaRepository")
public class ConsultaOcorrenciaRepositoryImpl extends
		AbstractConsultWebRepository<ConsultaOcorrencia> implements
		ConsultaOcorrenciaRepository {

	protected ConsultaOcorrenciaRepositoryImpl() {
		super(ConsultaOcorrencia.class);
	}

	@Override
	public ConsultaOcorrencia gerarRegistroInclusaoRestricaoPadrao(
			ConsultaTipo consultaTipo) {

		ConsultaOcorrencia consultaOcorrencia = new ConsultaOcorrencia();
		consultaOcorrencia
				.setOcorrencia("consultar.ocorrencias.resumo.label.registro.inclusao");
		consultaOcorrencia.setConsultaTipo(consultaTipo);

		/* Gerar data de Inicio e Fim */
		Calendar dataInicio = Calendar.getInstance();
		Calendar dataInvalida = Calendar.getInstance();
		dataInvalida.set(Calendar.DATE, 1);
		dataInvalida.set(Calendar.MONTH, 0);
		dataInvalida.set(Calendar.YEAR, 1900);

		/* Gerar o ultimo registro de Restricao encontrado */
		Query query = getEntityManager().createNamedQuery("queryRestricoes");
		query.setParameter("cpf", consultaTipo.getConsulta().getContraparte()
				.getCpf());
		query.setParameter("dataInicio", dataInicio.getTime());
		query.setParameter("dataFim", dataInicio.getTime());

		@SuppressWarnings(value = "unchecked")
		List<Restricao> queryRestricoes = query.getResultList();

		if (queryRestricoes.size() > 0) {
			Restricao ultimaRestricao = queryRestricoes.get(0);
			consultaOcorrencia.setDataUltimaOcorrencia(ultimaRestricao
					.getDataRestricao());
			consultaOcorrencia.setQuantidade(queryRestricoes.size());
			consultaOcorrencia.setValorUltimaOcorrencia(ultimaRestricao
					.getValorDebito());
		} else {
			consultaOcorrencia.setDataUltimaOcorrencia(dataInvalida.getTime());
			consultaOcorrencia.setQuantidade(0);
			consultaOcorrencia.setValorUltimaOcorrencia(BigDecimal.ZERO);
		}

		return consultaOcorrencia;
	}

	@Override
	public ConsultaOcorrencia gerarConsultasRealizadaRestricaoPadrao(
			ConsultaTipo consultaTipo) {

		ConsultaOcorrencia consultaOcorrencia = new ConsultaOcorrencia();
		consultaOcorrencia
				.setOcorrencia("consultar.ocorrencias.resumo.label.consulta.realizada");
		consultaOcorrencia.setConsultaTipo(consultaTipo);

		Calendar dataFim = Calendar.getInstance();
		Calendar dataInicio = Calendar.getInstance();
		dataInicio.add(Calendar.DATE, -90);

		Calendar dataInvalida = Calendar.getInstance();
		dataInvalida.set(Calendar.DATE, 1);
		dataInvalida.set(Calendar.MONTH, 0);
		dataInvalida.set(Calendar.YEAR, 1900);

		Query query = getEntityManager().createNamedQuery("queryConsultas");
		query.setParameter("cpf", consultaTipo.getConsulta().getContraparte()
				.getCpf());
		query.setParameter("dataInicio", dataInicio.getTime());
		query.setParameter("dataFim", dataFim.getTime());

		@SuppressWarnings(value = "unchecked")
		List<Consulta> queryConsultas = query.getResultList();

		if (queryConsultas.size() > 0) {
			Consulta ultimaConsulta = queryConsultas.get(0);
			consultaOcorrencia.setDataUltimaOcorrencia(ultimaConsulta
					.getDataConsulta());
			consultaOcorrencia.setQuantidade(queryConsultas.size());
			consultaOcorrencia.setValorUltimaOcorrencia(BigDecimal.ZERO);
		} else {
			consultaOcorrencia.setDataUltimaOcorrencia(dataInvalida.getTime());
			consultaOcorrencia.setQuantidade(0);
			consultaOcorrencia.setValorUltimaOcorrencia(BigDecimal.ZERO);
		}

		return consultaOcorrencia;
	}

}
