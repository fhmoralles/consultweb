package br.com.consultweb.model.servico.impl;

import java.util.ArrayList;
import java.util.Calendar;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.consultweb.domain.servico.Consulta;
import br.com.consultweb.domain.servico.Faturamento;
import br.com.consultweb.domain.servico.Produto;
import br.com.consultweb.domain.servico.ProdutoConsulta;
import br.com.consultweb.domain.servico.Protocolo;
import br.com.consultweb.domain.servico.consulta.ConsultaOcorrencia;
import br.com.consultweb.domain.servico.consulta.ConsultaRealizada;
import br.com.consultweb.domain.servico.consulta.ConsultaRestricao;
import br.com.consultweb.domain.servico.consulta.ConsultaTipo;
import br.com.consultweb.domain.types.SituacaoFaturamento;
import br.com.consultweb.model.servico.spec.ConsultaModel;
import br.com.consultweb.model.servico.spec.FaturamentoModel;
import br.com.consultweb.model.servico.spec.ProdutoModel;
import br.com.consultweb.model.servico.spec.ProtocoloModel;
import br.com.consultweb.model.servico.spec.consulta.ConsultaOcorrenciaModel;
import br.com.consultweb.model.servico.spec.consulta.ConsultaRealizadaModel;
import br.com.consultweb.model.servico.spec.consulta.ConsultaRestricaoModel;
import br.com.consultweb.repository.servico.spec.ConsultaRepository;
import br.com.libutils.validation.MD5Digest;

@Stateless(name = "consultaModel")
public class ConsultaModelImpl implements ConsultaModel {

	@EJB
	private ConsultaRepository consultaRepository;

	@EJB
	private ProdutoModel produtoModel;

	@EJB
	private ConsultaOcorrenciaModel consultaOcorrenciaModel;

	@EJB
	private ConsultaRestricaoModel consultaRestricaoModel;

	@EJB
	private ConsultaRealizadaModel consultaRealizadaModel;

	@EJB
	private FaturamentoModel faturamentoModel;

	@EJB
	private ProtocoloModel protocoloModel;
	
	@Override
	public void create(Consulta consulta) {
		// TODO Auto-generated method stub

	}

	@Override
	public Consulta retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consulta update(Consulta consulta) throws Exception {
		// TODO Auto-generated method stub
		return consultaRepository.update(consulta);
	}

	@Override
	public void delete(Consulta consulta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(Consulta consulta) {
		// TODO Auto-generated method stub

	}

	@Override
	public Consulta gerarConsulta(Consulta consulta, Produto produto) throws Exception {

		/*
		 * Verificar quais as consulta do Produto e gerar os insumos
		 * 
		 * Itera sobre os tipos de consultas dos Produto
		 */
		if (consulta.getConsultaTipos() == null) {
			consulta.setConsultaTipos(new ArrayList<ConsultaTipo>());
		}
		consulta.setDataConsulta(Calendar.getInstance().getTime());

		try {
			/* Recupera Produto */
			produto = produtoModel.update(produto);

			for (ProdutoConsulta produtoConsulta : produto
					.getProdutoConsultas()) {

				/* Gera o tipo da Consulta */
				ConsultaTipo consultaTipo = new ConsultaTipo();
				consultaTipo.setConsulta(consulta);
				consultaTipo.setTipoConsulta(produtoConsulta.getTipoConsulta());
				consultaTipo.setTipoConsultaAbrangencia(produtoConsulta
						.getTipoConsultaAbrangencia());
				consultaTipo
						.setConsultaOcorrencias(new ArrayList<ConsultaOcorrencia>());
				consultaTipo
						.setConsultaRealizadas(new ArrayList<ConsultaRealizada>());
				consultaTipo
						.setConsultaRestricoes(new ArrayList<ConsultaRestricao>());

				/* Gera registros das ultimas ocorrencias */
				consultaTipo.getConsultaOcorrencias().addAll(
						consultaOcorrenciaModel
								.geraConsultaOcorrencias(consultaTipo));

				/* Gera registos das Consultas Realizadas */
				consultaTipo.getConsultaRealizadas().addAll(
						consultaRealizadaModel
								.geraConsultaRealizadas(consultaTipo));

				/* Gera registos das Inclusões Ativas */
				consultaTipo.getConsultaRestricoes().addAll(
						consultaRestricaoModel
								.geraConsultaRestricoes(consultaTipo));

				consulta.getConsultaTipos().add(consultaTipo);

			}

			/* Persiste a Consulta */
			consulta = this.update(consulta);
			
			/* Inserir no Faturamento */
			Faturamento faturamento = new Faturamento();
			faturamento.setDataFaturamento(consulta.getDataConsulta());
			faturamento.setSituacaoFaturamento(SituacaoFaturamento.ABERTO);
			faturamento.setValorFaturado(produto.getValor());
			faturamento.setProduto(produto);
			faturamento.setAssociado(consulta.getAssociado());
			faturamento = faturamentoModel.update(faturamento);

			/* Gera Protocolo */
			Protocolo protocolo = new Protocolo();
			protocolo.setNumero(MD5Digest.getInstance().generateDigest(
					String.valueOf(consulta.getDataConsulta().getTime())));
			protocolo.setDataGeracao(consulta.getDataConsulta());
			protocolo.setConsulta(consulta);
			protocolo.setFaturamento(faturamento);
			protocolo = protocoloModel.update(protocolo);
			
			/* Define o protocolo na restricao */
			consulta.setProtocolo(protocolo);
			
		} catch (Exception e) {
			throw e;
		}

		return consulta;
	}

}
