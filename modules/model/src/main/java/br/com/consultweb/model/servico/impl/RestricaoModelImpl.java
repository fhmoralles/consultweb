package br.com.consultweb.model.servico.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.consultweb.domain.parametros.Parametros;
import br.com.consultweb.domain.servico.Exclusao;
import br.com.consultweb.domain.servico.Faturamento;
import br.com.consultweb.domain.servico.Produto;
import br.com.consultweb.domain.servico.Protocolo;
import br.com.consultweb.domain.servico.Restricao;
import br.com.consultweb.domain.types.SituacaoFaturamento;
import br.com.consultweb.domain.types.TipoProduto;
import br.com.consultweb.model.exceptions.RestricaoProdutoIncluirAssociadoException;
import br.com.consultweb.model.parametros.spec.ParametrosModel;
import br.com.consultweb.model.servico.spec.ExclusaoModel;
import br.com.consultweb.model.servico.spec.FaturamentoModel;
import br.com.consultweb.model.servico.spec.ProtocoloModel;
import br.com.consultweb.model.servico.spec.RestricaoModel;
import br.com.consultweb.repository.servico.spec.RestricaoRepository;
import br.com.libutils.validation.MD5Digest;

@Stateless(name = "restricaoModel")
public class RestricaoModelImpl implements RestricaoModel {

	@EJB
	private RestricaoRepository restricaoRepository;

	@EJB
	private ParametrosModel parametrosModel;

	@EJB
	private FaturamentoModel faturamentoModel;

	@EJB
	private ProtocoloModel protocoloModel;

	@EJB
	private ExclusaoModel exclusaoModel;

	@Override
	public void create(Restricao restricao) {
		// TODO Auto-generated method stub

	}

	@Override
	public Restricao update(Restricao restricao) throws Exception {

		/* Definir das datas de Inicio e Fim de Vigencia da Restrição */
		Parametros parametros = parametrosModel.locate();
		Date dataInclusao = Calendar.getInstance().getTime();

		Calendar dataRestricao = Calendar.getInstance();
		dataRestricao.add(Calendar.DATE, parametros.getDiasRestricao());

		Calendar dataVigencia = (Calendar) dataRestricao.clone();
		dataVigencia.add(Calendar.YEAR, parametros.getAnosVigencia());

		/* Merge Restricao */
		restricao.setDataInclusao(dataInclusao);
		restricao.setDataRestricao(dataRestricao.getTime());
		restricao.setDataVigencia(dataVigencia.getTime());
		restricao = restricaoRepository.update(restricao);

		/* Encontrar o produto de Inclusão do Associado */
		Produto produtoIncluir = null;
		for (Produto aux : restricao.getAssociado().getProdutos()) {
			if (aux.getTipoProduto() == TipoProduto.INCLUSAO) {
				produtoIncluir = aux;
				break;
			}
		}

		if (produtoIncluir == null) {
			throw new RestricaoProdutoIncluirAssociadoException(
					"msg.error.restricao.produto.incluir.associado");
		}

		/* Inserir no Faturamento */
		Faturamento faturamento = new Faturamento();
		faturamento.setDataFaturamento(restricao.getDataInclusao());
		faturamento.setSituacaoFaturamento(SituacaoFaturamento.ABERTO);
		faturamento.setValorFaturado(produtoIncluir.getValor());
		faturamento.setProduto(produtoIncluir);
		faturamento.setAssociado(restricao.getAssociado());
		faturamento = faturamentoModel.update(faturamento);

		/* Gera Protocolo */
		Protocolo protocolo = new Protocolo();
		protocolo.setNumero(MD5Digest.getInstance().generateDigest(
				String.valueOf(restricao.getDataInclusao().getTime())));
		protocolo.setDataGeracao(dataInclusao);
		protocolo.setRestricao(restricao);
		protocolo.setFaturamento(faturamento);
		protocolo = protocoloModel.update(protocolo);

		/* Define o protocolo na restricao */
		restricao.setProtocolo(protocolo);

		return restricao;
	}

	@Override
	public Restricao retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Restricao restricao) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(Restricao restricao) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Restricao> listagemRestricaoAssociado(Integer associadoCodigo) {

		Calendar dataVigencia = Calendar.getInstance();
		return restricaoRepository.listagemRestricaoAssociado(associadoCodigo,
				dataVigencia.getTime());
	}

}
