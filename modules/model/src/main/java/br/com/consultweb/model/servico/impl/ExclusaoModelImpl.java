package br.com.consultweb.model.servico.impl;

import java.util.Calendar;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.consultweb.domain.parametros.Parametros;
import br.com.consultweb.domain.servico.Exclusao;
import br.com.consultweb.domain.servico.Faturamento;
import br.com.consultweb.domain.servico.Protocolo;
import br.com.consultweb.domain.servico.Restricao;
import br.com.consultweb.domain.types.SituacaoFaturamento;
import br.com.consultweb.model.parametros.spec.ParametrosModel;
import br.com.consultweb.model.servico.spec.ExclusaoModel;
import br.com.consultweb.model.servico.spec.FaturamentoModel;
import br.com.consultweb.model.servico.spec.ProtocoloModel;
import br.com.consultweb.repository.servico.spec.ExclusaoRepository;
import br.com.consultweb.repository.servico.spec.RestricaoRepository;
import br.com.libutils.validation.MD5Digest;

@Stateless(name = "exclusaoModel")
public class ExclusaoModelImpl implements ExclusaoModel {

	@EJB
	private ExclusaoRepository exclusaoRepository;

	@EJB
	private RestricaoRepository restricaoRepository;

	@EJB
	private FaturamentoModel faturamentoModel;
	
	@EJB
	private ParametrosModel parametrosModel;

	@EJB
	private ProtocoloModel protocoloModel;
	
	@Override
	public void create(Exclusao exclusao) {
		// TODO Auto-generated method stub
	}

	@Override
	public Exclusao retrieve(Long id) {
		return exclusaoRepository.retrieve(id);
	}

	@Override
	public Exclusao update(Exclusao exclusao) throws Exception {

		/* Definir das datas de Inicio e Fim de Vigencia da Restrição */
		Parametros parametros = parametrosModel.locate();
		Calendar dataAtual = Calendar.getInstance();
		
		/* Atualizar a data de Vigencia */
		Restricao restricao = exclusao.getRestricao();
		restricao.setDataVigencia(dataAtual.getTime());
		restricao = restricaoRepository.update(restricao);
		exclusao.setRestricao(restricao);
		
		/* Persiste exclusao */
		exclusao = exclusaoRepository.update(exclusao);
		
		/* Gerar faturamento */
		Faturamento faturamento = new Faturamento();
		faturamento.setDataFaturamento(exclusao.getDataExclusao());
		faturamento.setSituacaoFaturamento(SituacaoFaturamento.ABERTO);
		faturamento.setValorFaturado(parametros.getProdutoExcluir().getValor());
		faturamento.setProduto(parametros.getProdutoExcluir());
		faturamento.setAssociado(exclusao.getAssociado());
		faturamento = faturamentoModel.update(faturamento);
		
		/* Gerar protocolo */
		Protocolo protocolo = new Protocolo();
		protocolo.setNumero(MD5Digest.getInstance().generateDigest(
				String.valueOf(exclusao.getDataExclusao().getTime())));
		protocolo.setDataGeracao(dataAtual.getTime());
		protocolo.setExclusao(exclusao);
		protocolo.setFaturamento(faturamento);
		protocolo = protocoloModel.update(protocolo);
		
		exclusao.setProtocolo(protocolo);
		
		return exclusao;
	}

	@Override
	public void delete(Exclusao exclusao) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(Exclusao exclusao) {
		// TODO Auto-generated method stub

	}
	
}
