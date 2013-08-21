package br.com.consultweb.view.servico;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import br.com.consultweb.domain.cadastro.Associado;
import br.com.consultweb.domain.cadastro.Contraparte;
import br.com.consultweb.domain.cadastro.ContraparteEndereco;
import br.com.consultweb.domain.parametros.Operador;
import br.com.consultweb.domain.parametros.Parametros;
import br.com.consultweb.domain.servico.NaturezaInclusao;
import br.com.consultweb.domain.servico.Restricao;
import br.com.consultweb.domain.types.Estado;
import br.com.consultweb.domain.types.EstadoCivil;
import br.com.consultweb.domain.types.Sexo;
import br.com.consultweb.domain.types.TipoDevedor;
import br.com.consultweb.domain.types.TipoOperador;
import br.com.consultweb.domain.types.TipoPessoa;
import br.com.consultweb.model.cadastro.spec.AssociadoModel;
import br.com.consultweb.model.cadastro.spec.ContraparteModel;
import br.com.consultweb.model.cadastro.spec.EstadoCivilModel;
import br.com.consultweb.model.cadastro.spec.EstadoModel;
import br.com.consultweb.model.cadastro.spec.SexoModel;
import br.com.consultweb.model.cadastro.spec.TipoPessoaModel;
import br.com.consultweb.model.exceptions.ConsultException;
import br.com.consultweb.model.exceptions.RestricaoValorDebitoInvalidaException;
import br.com.consultweb.model.parametros.spec.ParametrosModel;
import br.com.consultweb.model.servico.spec.NaturezaInclusaoModel;
import br.com.consultweb.model.servico.spec.RestricaoModel;
import br.com.consultweb.model.servico.spec.TipoDevedorModel;
import br.com.consultweb.view.main.constants.MessagesConstants;
import br.com.consultweb.view.main.security.ConsultUser;
import br.com.consultweb.view.servico.filter.RestricaoFilter;
import br.com.libutils.exception.CnpjValidationException;
import br.com.libutils.exception.CpfValidationException;
import br.com.libutils.validation.Validacao;
import br.com.webutils.MessageUtil;
import br.com.webutils.ui.AbstractCRUD;

@Named
@SessionScoped
@LoggedIn
public class RestricaoUI extends AbstractCRUD<Restricao, RestricaoFilter> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4457555462635801475L;

	private static final Logger LOG = Logger.getLogger(RestricaoUI.class);

	private static final String INSERIR_RESTRICAO = "/forms/servicos/incluirRestricao.xhtml";
	private static final String LISTAGEM_RESTRICAO = "/forms/servicos/listagemRestricao.xhtml";

	/* Valores de Domíno */
	private Operador operador;

	private Boolean entidadeDisabled;
	private Boolean associadoDisabled;
	private TipoPessoa tipoPessoa;
	private TipoDevedor tipoDevedor;

	private ContraparteEndereco contraparteEndereco;
	private Estado rgEstado;
	private Sexo sexo;
	private EstadoCivil estadoCivil;
	private Estado estado;

	/* Padrão */
	private String cpfPattern;
	private String labelCpfPattern;
	private String labelContraparteNome;
	private Boolean pfSelectec;
	private String msgCustoOperacaoIncluir;
	private String msgCustoOperacaoExcluir;

	/* Informações de Login */
	@Inject
	private Identity identity;

	/* Modelo */
	@Inject
	private AssociadoModel associadoModel;

	@Inject
	private NaturezaInclusaoModel naturezaInclusaoModel;

	@Inject
	private TipoPessoaModel tipoPessoaModel;

	@Inject
	private SexoModel sexoModel;

	@Inject
	private TipoDevedorModel tipoDevedorModel;

	@Inject
	private EstadoModel estadoModel;

	@Inject
	private EstadoCivilModel estadoCivilModel;

	@Inject
	private RestricaoModel restricaoModel;

	@Inject
	private ParametrosModel parametrosModel;

	@Inject
	private ContraparteModel contraparteModel;

	public RestricaoUI() {
		super(new RestricaoFilter());
	}

	@Override
	protected void cleanUpImpl() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void deleteImpl(Restricao bean) {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getActionCreate() {

		/* Criação da Contraparte */
		this.getBean().setContraparte(new Contraparte());
		this.getBean().getContraparte()
				.setContraparteEnderecos(new ArrayList<ContraparteEndereco>());

		this.setContraparteEndereco(new ContraparteEndereco());

		/* Pattern padrão: Definir nos parametros */
		this.setTipoPessoa(TipoPessoa.FISICA);
		this.setCpfPattern("999.999.999-99");
		this.setPfSelectec(true);
		this.setLabelCpfPattern(MessageUtil
				.getMessage("restricao.contraparte.cpf"));
		this.setLabelContraparteNome(MessageUtil
				.getMessage("restricao.contraparte.nome"));
		this.setTipoDevedor(TipoDevedor.COMPRADOR);
		this.getBean().setValorDebito(BigDecimal.ZERO);

		Parametros parametros = parametrosModel.locate();
		this.getBean().setNaturezaInclusao(parametros.getNaturezaInclusao());

		if (identity != null) {

			ConsultUser consultUser = (ConsultUser) identity.getUser();
			this.setOperador(consultUser.getOperador());

			/* Regras para preenchimento */
			/*
			 * Caso Operador seja Entidade, preencher a Entidade e deixar
			 * Associado
			 */
			if (operador.getTipoOperador() == TipoOperador.ENTIDADE) {

				/* Seta os valores do Associado */
				this.getBean().setAssociado(new Associado());
				this.getBean().getAssociado()
						.setEntidade(operador.getAssociado().getEntidade());
				/* Define se o campo está habilidado */
				this.setEntidadeDisabled(true);
				this.setAssociadoDisabled(false);

			} else if (operador.getTipoOperador() == TipoOperador.ASSOCIADO) {

				/* Seta os valores do Associado */
				this.getBean().setAssociado(operador.getAssociado());
				/* Define se o campo está habilidado */
				this.setEntidadeDisabled(true);
				this.setAssociadoDisabled(true);

			}

		}

		this.setMsgCustoOperacaoIncluir(MessageUtil.getMessage(
				MessagesConstants.MSG_WARN_INCLUIR_RESTRICAO, parametros
						.getProdutoIncluir().getValor()));

		return INSERIR_RESTRICAO;
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
		// TODO Auto-generated method stub
		return null;
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
		return false;
	}

	@Override
	protected boolean isValidBean(Restricao bean) {

		try {

			if (bean.getValorDebito() == null
					|| bean.getValorDebito().doubleValue() <= BigDecimal.ZERO
							.doubleValue()) {

				throw new RestricaoValorDebitoInvalidaException(
						MessagesConstants.MSG_ERROR_RESTRICAO_VALORDEBITO_INVALIDA);

			}

			return true;

		} catch (ConsultException e) {

			MessageUtil.addGlobalErrorMessage(e.getMessage());
			return false;

		}

	}

	@Override
	public boolean isViewable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Restricao newInstance() {
		return new Restricao();
	}

	@Override
	protected void saveImpl(Restricao bean) throws Exception {

		try {

			/* Cria Restricao */
			bean.getAssociado().setTipo(this.getTipoPessoa());
			bean.setTipoDevedor(this.getTipoDevedor());
			bean.getContraparte().setRgEstado(this.getRgEstado());
			bean.getContraparte().setSexo(this.getSexo());
			bean.getContraparte().setEstadocivil(this.getEstadoCivil());

			/* Define a Contraparte - Endereço */
			this.getContraparteEndereco().setEstado(this.getEstado());
			this.getContraparteEndereco().setContraparte(bean.getContraparte());

			ContraparteEndereco aux = bean
					.getContraparte()
					.getContraparteEnderecos()
					.get(bean.getContraparte().getContraparteEnderecos().size() - 1);

			if (!this.getContraparteEndereco().getCep().equals(aux.getCep())
					|| !this.getContraparteEndereco().getLogradouro()
							.equals(aux.getLogradouro())
					|| !this.getContraparteEndereco().getBairro()
							.equals(aux.getBairro())
					|| !this.getContraparteEndereco().getMunicipio()
							.equals(aux.getMunicipio())
					|| !this.getContraparteEndereco().getNumero()
							.equals(aux.getNumero())
					|| this.getContraparteEndereco().getEstado() != aux
							.getEstado()
					|| !this.getContraparteEndereco().getComplemento()
							.equals(aux.getComplemento())) {

				bean.getContraparte().getContraparteEnderecos()
						.add(this.getContraparteEndereco());
			}

			/* Envia para o Modelo */
			Restricao c = restricaoModel.update(bean);

			SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm:ss");

			MessageUtil.addGlobalInfoMessage(
					MessagesConstants.MSG_INFO_RESTRICAO_INCLUIR, c
							.getContraparte().getCpf(), c.getContraparte()
							.getNome(), c.getValorDebito());

			MessageUtil.addGlobalInfoMessage(
					MessagesConstants.MSG_INFO_PROTOCOLO_GERADO, c
							.getProtocolo().getNumero().toUpperCase(),
					sdfData.format(c.getProtocolo().getDataGeracao()),
					sdfHora.format(c.getProtocolo().getDataGeracao()));

			/* Nova Inclusão */
			prepareCreate();

		} catch (Exception e) {

			MessageUtil.addGlobalErrorMessage(
					MessagesConstants.MSG_ERROR_RESTRICAO_INCLUIR, bean
							.getAssociado().getCodigo(), bean.getAssociado()
							.getRazaoSocial());
			throw e;

		}

	}

	@Override
	protected List<Restricao> searchImpl(RestricaoFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	/* Metodos especificos dessa Entidade */

	/* Validação de Padrao de CPF ou CNPJ de acordo com a Pessoa */
	public void changePattern(AjaxBehaviorEvent event) {

		UISelectOne uiSelectOne = (UISelectOne) event.getSource();
		TipoPessoa tipoPessoa = (TipoPessoa) uiSelectOne.getValue();

		if (tipoPessoa == TipoPessoa.FISICA) {
			this.setCpfPattern("999.999.999-99");
			this.setPfSelectec(true);
			this.setLabelCpfPattern(MessageUtil
					.getMessage("restricao.contraparte.cpf"));
			this.setLabelContraparteNome(MessageUtil
					.getMessage("restricao.contraparte.nome"));
		} else {
			this.setCpfPattern("99.999.999/9999-99");
			this.setPfSelectec(false);
			this.setLabelCpfPattern(MessageUtil
					.getMessage("restricao.contraparte.cnpj"));
			this.setLabelContraparteNome(MessageUtil
					.getMessage("restricao.contraparte.razao"));
		}

	}

	public void associadoLocate() {

		Associado associado = associadoModel.localizarPorAssociadoEEntidade(
				this.getBean().getAssociado().getCodigo(), this.getBean()
						.getAssociado().getEntidade());

		if (associado != null) {
			this.getBean().setAssociado(associado);
		} else {
			MessageUtil.addComponentErrorMessage(
					"contentConsultForm_associadoCodigoText",
					MessagesConstants.MSG_ERROR_ASSOCIADO_NAO_LOCALIZADO, this
							.getBean().getAssociado().getCodigo());
			this.getBean().getAssociado().setId(-1L);
			this.getBean().getAssociado().setCodigo(null);
			this.getBean().getAssociado().setRazaoSocial(StringUtils.EMPTY);
		}

	}

	public void contraparteLocate() {

		/* Validação de CPF e/ou CNPJ */
		try {

			if (this.getTipoPessoa() == TipoPessoa.FISICA) {
				Validacao.validaCPF(this.getBean().getContraparte().getCpf());
			} else {
				Validacao.validaCNPJ(this.getBean().getContraparte().getCpf());
			}

			Contraparte contraparte = contraparteModel.retrieveByCpf(this
					.getBean().getContraparte().getCpf());

			if (contraparte != null) {
				this.getBean().setContraparte(contraparte);
				ContraparteEndereco aux = contraparte.getContraparteEnderecos()
						.get(contraparte.getContraparteEnderecos().size() - 1);

				this.getContraparteEndereco().setCep(aux.getCep());
				this.getContraparteEndereco()
						.setLogradouro(aux.getLogradouro());
				this.getContraparteEndereco().setBairro(aux.getBairro());
				this.getContraparteEndereco().setMunicipio(aux.getMunicipio());
				this.getContraparteEndereco().setNumero(aux.getNumero());
				this.getContraparteEndereco().setEstado(aux.getEstado());
				this.getContraparteEndereco().setComplemento(
						aux.getComplemento());

				this.setRgEstado(contraparte.getRgEstado());
				this.setSexo(contraparte.getSexo());
				this.setEstadoCivil(contraparte.getEstadoCivil());
				this.setEstado(contraparteEndereco.getEstado());

			}

		} catch (CpfValidationException e) {

			LOG.error(e.getMessage(), e);
			MessageUtil.addComponentErrorMessage(
					"contentConsultForm_contraparteCpfText",
					MessagesConstants.MSG_ERROR_CPF_INVALIDO, this.getBean()
							.getContraparte().getCpf());
			this.getBean().getContraparte().setCpf(StringUtils.EMPTY);

		} catch (CnpjValidationException e) {

			LOG.error(e.getMessage(), e);
			MessageUtil.addComponentErrorMessage(
					"contentConsultForm_contraparteCpfText",
					MessagesConstants.MSG_ERROR_CPF_INVALIDO, this.getBean()
							.getContraparte().getCpf());
			this.getBean().getContraparte().setCpf(StringUtils.EMPTY);

		}

	}

	public List<TipoPessoa> getTiposPessoa() {
		return tipoPessoaModel.getTiposPessoa();
	}

	public List<Sexo> getSexos() {
		return sexoModel.getSexos();
	}

	public List<TipoDevedor> getTiposDevedor() {
		return tipoDevedorModel.getTiposDevedor();
	}

	public List<Estado> getEstados() {
		return estadoModel.getEstados();
	}

	public List<NaturezaInclusao> getNaturezasInclusao() {
		return naturezaInclusaoModel.getAll();
	}

	public List<EstadoCivil> getEstadosCivil() {
		return estadoCivilModel.getEstadosCivil();
	}

	/* **************************** */

	public Boolean getEntidadeDisabled() {
		return entidadeDisabled;
	}

	public void setEntidadeDisabled(Boolean entidadeDisabled) {
		this.entidadeDisabled = entidadeDisabled;
	}

	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	public Boolean getAssociadoDisabled() {
		return associadoDisabled;
	}

	public void setAssociadoDisabled(Boolean associadoDisabled) {
		this.associadoDisabled = associadoDisabled;
	}

	public String getCpfPattern() {
		return cpfPattern;
	}

	public void setCpfPattern(String cpfPattern) {
		this.cpfPattern = cpfPattern;
	}

	public String getLabelCpfPattern() {
		return labelCpfPattern;
	}

	public void setLabelCpfPattern(String labelCpfPattern) {
		this.labelCpfPattern = labelCpfPattern;
	}

	public String getLabelContraparteNome() {
		return labelContraparteNome;
	}

	public void setLabelContraparteNome(String labelContraparteNome) {
		this.labelContraparteNome = labelContraparteNome;
	}

	public Boolean getPfSelectec() {
		return pfSelectec;
	}

	public void setPfSelectec(Boolean pfSelectec) {
		this.pfSelectec = pfSelectec;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public TipoDevedor getTipoDevedor() {
		return tipoDevedor;
	}

	public void setTipoDevedor(TipoDevedor tipoDevedor) {
		this.tipoDevedor = tipoDevedor;
	}

	public ContraparteEndereco getContraparteEndereco() {
		return contraparteEndereco;
	}

	public void setContraparteEndereco(ContraparteEndereco contraparteEndereco) {
		this.contraparteEndereco = contraparteEndereco;
	}

	public Estado getRgEstado() {
		return rgEstado;
	}

	public void setRgEstado(Estado rgEstado) {
		this.rgEstado = rgEstado;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getMsgCustoOperacaoIncluir() {
		return msgCustoOperacaoIncluir;
	}

	public void setMsgCustoOperacaoIncluir(String msgCustoOperacaoIncluir) {
		this.msgCustoOperacaoIncluir = msgCustoOperacaoIncluir;
	}

	public String getMsgCustoOperacaoExcluir() {
		return msgCustoOperacaoExcluir;
	}

	public void setMsgCustoOperacaoExcluir(String msgCustoOperacaoExcluir) {
		this.msgCustoOperacaoExcluir = msgCustoOperacaoExcluir;
	}

}
