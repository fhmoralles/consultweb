package br.com.consultweb.view.servico;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UISelectOne;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.LoggedIn;
import org.primefaces.context.RequestContext;

import br.com.consultweb.domain.cadastro.Associado;
import br.com.consultweb.domain.cadastro.Contraparte;
import br.com.consultweb.domain.cadastro.ContraparteEndereco;
import br.com.consultweb.domain.parametros.Operador;
import br.com.consultweb.domain.servico.Consulta;
import br.com.consultweb.domain.servico.Produto;
import br.com.consultweb.domain.servico.consulta.ConsultaOcorrencia;
import br.com.consultweb.domain.servico.consulta.ConsultaRealizada;
import br.com.consultweb.domain.servico.consulta.ConsultaRestricao;
import br.com.consultweb.domain.servico.consulta.ConsultaTipo;
import br.com.consultweb.domain.types.Dispositivo;
import br.com.consultweb.domain.types.TipoOperador;
import br.com.consultweb.domain.types.TipoPessoa;
import br.com.consultweb.model.cadastro.spec.AssociadoModel;
import br.com.consultweb.model.cadastro.spec.ContraparteModel;
import br.com.consultweb.model.cadastro.spec.TipoPessoaModel;
import br.com.consultweb.model.servico.spec.ConsultaModel;
import br.com.consultweb.view.main.constants.MessagesConstants;
import br.com.consultweb.view.main.security.ConsultUser;
import br.com.consultweb.view.servico.filter.ConsultaFilter;
import br.com.libutils.exception.CnpjValidationException;
import br.com.libutils.exception.CpfValidationException;
import br.com.libutils.validation.Validacao;
import br.com.webutils.MessageUtil;
import br.com.webutils.ui.AbstractCRUD;

@Named
@SessionScoped
@LoggedIn
public class ConsultarUI extends AbstractCRUD<Consulta, ConsultaFilter> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3410771585799391384L;

	private static final Logger LOG = Logger.getLogger(RestricaoUI.class);

	private static final String SEARCH_CONSULTAR = "/forms/servicos/consultarRestricao.xhtml";

	/* Informações de Login */
	@Inject
	private Identity identity;

	/* Valores de Domíno */
	private Operador operador;
	
	private String msgCusstoOperacao;
	private String situacaoDocumento;

	private ContraparteEndereco ultimoContraparteEndereco;
	private List<Produto> consultasAssociado;
	private List<ConsultaOcorrencia> consultaOcorrencias;
	private List<ConsultaRestricao> consultaRestricoes;
	private List<ConsultaRealizada> consultaRealizadas;

	/* Dispositivo */
	private Dispositivo dispositivo;
	
	/* Modelo */
	@Inject
	private AssociadoModel associadoModel;

	@Inject
	private ContraparteModel contraparteModel;

	@Inject
	private TipoPessoaModel tipoPessoaModel;

	@Inject
	private ConsultaModel consultaModel;

	public ConsultarUI() {
		super(new ConsultaFilter());
	}

	@Override
	protected void cleanUpImpl() {

		ultimoContraparteEndereco = null;
		consultasAssociado = Collections.emptyList();
		consultaOcorrencias = Collections.emptyList();
		consultaRestricoes = Collections.emptyList();
		consultaRealizadas = Collections.emptyList();

	}

	@Override
	protected void deleteImpl(Consulta bean) {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getActionCreate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getActionDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getActionEdit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getActionSearch() {

		/* Criação da Contraparte */
		reset();
		this.getFilter().setContraparte(new Contraparte());

		/* Pattern padrão: Definir nos parametros */
		this.getFilter().setTipoPessoa(TipoPessoa.FISICA);
		this.getFilter().setCpfPattern("999.999.999-99");
		this.getFilter().setLabelCpfPattern(
				MessageUtil.getMessage("restricao.contraparte.cpf"));
		this.getFilter().setLabelContraparteNome(
				MessageUtil.getMessage("restricao.contraparte.nome"));
		this.setSituacaoDocumento(MessageUtil.getMessage(
				"consultar.identificacao.situacao.documento", this.getFilter()
						.getLabelCpfPattern()));

		if (identity != null) {

			ConsultUser consultUser = (ConsultUser) identity.getUser();
			Operador operador = consultUser.getOperador();
			this.setOperador(operador);

			/* Regras para preenchimento */
			/*
			 * Caso Operador seja Entidade, preencher a Entidade e deixar
			 * Associado
			 */
			if (operador.getTipoOperador() == TipoOperador.ENTIDADE) {

				/* Seta os valores do Associado */
				this.getFilter().setAssociado(new Associado());
				this.getFilter().getAssociado()
						.setEntidade(operador.getAssociado().getEntidade());
				/* Define se o campo está habilidado */
				this.getFilter().setEntidadeDisabled(true);
				this.getFilter().setAssociadoDisabled(false);
				this.setDispositivo(Dispositivo.CALLCENTER);

			} else if (operador.getTipoOperador() == TipoOperador.ASSOCIADO) {

				/* Seta os valores do Associado */
				this.getFilter().setAssociado(operador.getAssociado());
				/* Define se o campo está habilidado */
				this.getFilter().setEntidadeDisabled(true);
				this.getFilter().setAssociadoDisabled(true);
				this.setDispositivo(Dispositivo.INTERNET);

				this.setConsultasAssociado(associadoModel
						.getConsultasAssociado(this.getFilter().getAssociado()));

			}

		}

		this.setMsgCusstoOperacao(MessageUtil
				.getMessage(MessagesConstants.MSG_WARN_CONSULTAR_RESTRICAO));

		RequestContext.getCurrentInstance().update("contentConsultForm");

		return SEARCH_CONSULTAR;
	}

	@Override
	public boolean isDeletable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEditable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInsertable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSearchable() {
		return true;
	}

	@Override
	protected boolean isSearchOnPrepare() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean isValidBean(Consulta bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isViewable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Consulta newInstance() {
		return new Consulta();
	}

	@Override
	protected void saveImpl(Consulta bean) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected List<Consulta> searchImpl(ConsultaFilter filter) {

		List<Consulta> consultas = new ArrayList<Consulta>();

		try {

			/* Seta bean com as Informações para pesquisa */
			this.setBean(this.newInstance());
			this.getBean().setDescricao(
					this.getFilter().getProduto().getDescricao());
			this.getBean().setAssociado(filter.getAssociado());
			this.getBean().setContraparte(filter.getContraparte());

			/* Define a Consulta com as informaões consultadas */
			this.setBean(consultaModel.gerarConsulta(this.getBean(), this
					.getFilter().getProduto(), this.getDispositivo(), this.getOperador()));

			/* Define as abas e preenche as tabelas */
			/*
			 * 1. Itera sobre os tipos para juntar as inforçaões de Resumo,
			 * Registros e Consultas
			 */
			this.setConsultaOcorrencias(new ArrayList<ConsultaOcorrencia>());
			this.setConsultaRestricoes(new ArrayList<ConsultaRestricao>());
			this.setConsultaRealizadas(new ArrayList<ConsultaRealizada>());

			for (ConsultaTipo consultaTipo : this.getBean().getConsultaTipos()) {
				this.getConsultaOcorrencias().addAll(
						consultaTipo.getConsultaOcorrencias());

				this.getConsultaRestricoes().addAll(
						consultaTipo.getConsultaRestricoes());

				this.getConsultaRealizadas().addAll(
						consultaTipo.getConsultaRealizadas());

			}

			/* Adiciona na coleção para retornar */
			consultas.add(this.getBean());

			/* Emite mensagem de sucesso */
			MessageUtil.addGlobalInfoMessage(
					MessagesConstants.MSG_INFO_CONSULTA_REALIZADA, this
							.getFilter().getAssociado().getCodigo());

			/* Emite de sansões a divulgação de informações para CPF */
			MessageUtil.addComponentWarnMessage(
					"contentConsultForm_idCpfConsultado",
					MessagesConstants.MSG_WARN_CONSULTAR_DADOS_INFORMADOS);

			/* Emite de se o usuário quer mais consultas */
			MessageUtil.addComponentWarnMessage(
					"contentConsultForm_consultasDataTable",
					MessagesConstants.MSG_WARN_CONSULTAR_CONSULTAS_INFORMADAS);

			/* Protocolo */
			SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm:ss");

			MessageUtil.addGlobalInfoMessage(
					MessagesConstants.MSG_INFO_PROTOCOLO_GERADO,
					this.getBean().getProtocolo().getNumero().toUpperCase(),
					sdfData.format(this.getBean().getProtocolo()
							.getDataGeracao()),
					sdfHora.format(this.getBean().getProtocolo()
							.getDataGeracao()));

		} catch (Exception e) {

			LOG.error(e.getMessage(), e);
			MessageUtil.addGlobalErrorMessage(
					MessagesConstants.MSG_ERROR_CONSULTA_NAO_REALIZADA, this
							.getFilter().getAssociado().getCodigo());

		}

		return consultas;

	}

	/* Metodos especificos dessa Entidade */
	/* Validação de Padrao de CPF ou CNPJ de acordo com a Pessoa */
	public void changePattern(AjaxBehaviorEvent event) {

		UISelectOne uiSelectOne = (UISelectOne) event.getSource();
		TipoPessoa tipoPessoa = (TipoPessoa) uiSelectOne.getValue();

		if (tipoPessoa == TipoPessoa.FISICA) {
			this.getFilter().setCpfPattern("999.999.999-99");
			this.getFilter().setLabelCpfPattern(
					MessageUtil.getMessage("restricao.contraparte.cpf"));
		} else {
			this.getFilter().setCpfPattern("99.999.999/9999-99");
			this.getFilter().setLabelCpfPattern(
					MessageUtil.getMessage("restricao.contraparte.cnpj"));
		}

		/* Montagem do label de Situação */
		this.setSituacaoDocumento(MessageUtil.getMessage(
				"consultar.identificacao.situacao.documento", this.getFilter()
						.getLabelCpfPattern()));

	}

	public void associadoLocate() {

		LOG.info("Associado Locate: "
				+ this.getFilter().getAssociado().getCodigo() + " - "
				+ this.getFilter().getAssociado().getEntidade());
		Associado associado = associadoModel.localizarPorAssociadoEEntidade(
				this.getFilter().getAssociado().getCodigo(), this.getFilter()
						.getAssociado().getEntidade());

		if (associado != null) {
			this.getFilter().setAssociado(associado);

			this.setConsultasAssociado(associadoModel
					.getConsultasAssociado(this.getFilter().getAssociado()));

		} else {
			MessageUtil.addComponentErrorMessage(
					"contentConsultForm_associadoCodigoText",
					MessagesConstants.MSG_ERROR_ASSOCIADO_NAO_LOCALIZADO, this
							.getFilter().getAssociado().getCodigo());
			this.getFilter().getAssociado().setId(-1L);
			this.getFilter().getAssociado().setCodigo(null);
			this.getFilter().getAssociado().setRazaoSocial(StringUtils.EMPTY);
		}

	}

	public void contraparteLocate() {

		/* Validação de CPF e/ou CNPJ */
		try {

			if (this.getFilter().getTipoPessoa() == TipoPessoa.FISICA) {
				Validacao.validaCPF(this.getFilter().getContraparte().getCpf());
			} else {
				Validacao
						.validaCNPJ(this.getFilter().getContraparte().getCpf());
			}

			Contraparte contraparte = contraparteModel.retrieveByCpf(this
					.getFilter().getContraparte().getCpf());

			if (contraparte != null) {
				this.getFilter().setContraparte(contraparte);

				if (contraparte.getContraparteEnderecos() != null
						&& contraparte.getContraparteEnderecos().size() > 0) {
					this.setUltimoContraparteEndereco(contraparte
							.getContraparteEnderecos().get(
									contraparte.getContraparteEnderecos()
											.size() - 1));
				}
			}

		} catch (CpfValidationException e) {

			LOG.error(e.getMessage(), e);
			MessageUtil.addComponentErrorMessage(
					"contentConsultForm_contraparteCpfText",
					MessagesConstants.MSG_ERROR_CPF_INVALIDO, this.getFilter()
							.getContraparte().getCpf());
			this.getFilter().getContraparte().setCpf(StringUtils.EMPTY);

		} catch (CnpjValidationException e) {

			LOG.error(e.getMessage(), e);
			MessageUtil.addComponentErrorMessage(
					"contentConsultForm_contraparteCpfText",
					MessagesConstants.MSG_ERROR_CPF_INVALIDO, this.getFilter()
							.getContraparte().getCpf());
			this.getFilter().getContraparte().setCpf(StringUtils.EMPTY);

		}

	}

	public String formatValor(BigDecimal valor) {

		DecimalFormat df = new DecimalFormat("###,###,##0.00");
		return df.format(valor);

	}

	public void setSelectAssociadoSearch() {
		System.out.println("Selectionado");
	}

	public List<TipoPessoa> getTiposPessoa() {
		return tipoPessoaModel.getTiposPessoa();
	}

	/* ************ */

	public String getMsgCusstoOperacao() {
		return msgCusstoOperacao;
	}

	public void setMsgCusstoOperacao(String msgCusstoOperacao) {
		this.msgCusstoOperacao = msgCusstoOperacao;
	}

	public List<Produto> getConsultasAssociado() {
		return consultasAssociado;
	}

	public void setConsultasAssociado(List<Produto> consultasAssociado) {
		this.consultasAssociado = consultasAssociado;
	}

	public List<ConsultaOcorrencia> getConsultaOcorrencias() {
		return consultaOcorrencias;
	}

	public void setConsultaOcorrencias(
			List<ConsultaOcorrencia> consultaOcorrencias) {
		this.consultaOcorrencias = consultaOcorrencias;
	}

	public List<ConsultaRestricao> getConsultaRestricoes() {
		return consultaRestricoes;
	}

	public void setConsultaRestricoes(List<ConsultaRestricao> consultaRestricoes) {
		this.consultaRestricoes = consultaRestricoes;
	}

	public List<ConsultaRealizada> getConsultaRealizadas() {
		return consultaRealizadas;
	}

	public void setConsultaRealizadas(List<ConsultaRealizada> consultaRealizadas) {
		this.consultaRealizadas = consultaRealizadas;
	}

	public String getSituacaoDocumento() {
		return situacaoDocumento;
	}

	public void setSituacaoDocumento(String situacaoDocumento) {
		this.situacaoDocumento = situacaoDocumento;
	}

	public ContraparteEndereco getUltimoContraparteEndereco() {
		return ultimoContraparteEndereco;
	}

	public void setUltimoContraparteEndereco(
			ContraparteEndereco ultimoContraparteEndereco) {
		this.ultimoContraparteEndereco = ultimoContraparteEndereco;
	}

	public Dispositivo getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}

	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	@Override
	protected String getMsgDelete() {
		// TODO Auto-generated method stub
		return null;
	}

}

