package br.com.consultweb.view.servico;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.LoggedIn;
import org.primefaces.context.RequestContext;

import br.com.consultweb.domain.cadastro.Associado;
import br.com.consultweb.domain.parametros.Operador;
import br.com.consultweb.domain.parametros.Parametros;
import br.com.consultweb.domain.servico.Exclusao;
import br.com.consultweb.domain.servico.Restricao;
import br.com.consultweb.domain.types.TipoOperador;
import br.com.consultweb.model.cadastro.spec.AssociadoModel;
import br.com.consultweb.model.parametros.spec.ParametrosModel;
import br.com.consultweb.model.servico.spec.ExclusaoModel;
import br.com.consultweb.model.servico.spec.RestricaoModel;
import br.com.consultweb.view.main.constants.MessagesConstants;
import br.com.consultweb.view.main.security.ConsultUser;
import br.com.consultweb.view.servico.filter.ExclusaoFilter;
import br.com.webutils.MessageUtil;
import br.com.webutils.ui.AbstractCRUD;

@Named
@SessionScoped
@LoggedIn
public class ExclusaoUI extends AbstractCRUD<Exclusao, ExclusaoFilter> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4457555462635801475L;

	private static final Logger LOG = Logger.getLogger(ExclusaoUI.class);

	private static final String EXCLUIR_RESTRICAO = "/forms/servicos/excluirRestricao.xhtml";

	/* Padrão */
	private String msgCustoOperacaoExcluir;

	private Restricao restricaoSelected;
	private List<Restricao> restricoes;

	/* Informações de Login */
	@Inject
	private Identity identity;

	/* Modelo */
	@Inject
	private AssociadoModel associadoModel;

	@Inject
	private ExclusaoModel exclusaoModel;

	@Inject
	private ParametrosModel parametrosModel;

	@Inject
	private RestricaoModel restricaoModel;

	public ExclusaoUI() {
		super(new ExclusaoFilter());
	}

	@Override
	protected void cleanUpImpl() {
		this.setRestricoes(null);
	}

	@Override
	protected void deleteImpl(Exclusao bean) {
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

		reset();

		Parametros parametros = parametrosModel.locate();

		if (identity != null) {

			ConsultUser consultUser = (ConsultUser) identity.getUser();
			Operador operador = consultUser.getOperador();

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

			} else if (operador.getTipoOperador() == TipoOperador.ASSOCIADO) {

				/* Seta os valores do Associado */
				this.getFilter().setAssociado(operador.getAssociado());
				/* Define se o campo está habilidado */
				this.getFilter().setEntidadeDisabled(true);
				this.getFilter().setAssociadoDisabled(true);

			}

		}

		DecimalFormat df = new DecimalFormat("###,###,##0.00");
		this.setMsgCustoOperacaoExcluir(MessageUtil.getMessage(
				MessagesConstants.MSG_WARN_EXCLUIR_RESTRICAO, df.format(parametros
						.getProdutoExcluir().getValor())));

		RequestContext.getCurrentInstance().update("contentConsultForm");

		return EXCLUIR_RESTRICAO;

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
	protected boolean isValidBean(Exclusao bean) {
		return true;
	}

	@Override
	public boolean isViewable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Exclusao newInstance() {
		return new Exclusao();
	}

	@Override
	protected void saveImpl(Exclusao bean) throws Exception {

		Exclusao exclusao = new Exclusao();

		try {

			/* Cria Exclusao */
			exclusao.setAssociado(this.getRestricaoSelected().getAssociado());
			exclusao.setContraparte(this.getRestricaoSelected()
					.getContraparte());
			exclusao.setDataExclusao(Calendar.getInstance().getTime());
			exclusao.setRestricao(this.getRestricaoSelected());
			exclusao = exclusaoModel.update(exclusao);

			SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm:ss");

			MessageUtil.addGlobalInfoMessage(
					MessagesConstants.MSG_INFO_RESTRICAO_EXCLUIR, exclusao
							.getContraparte().getCpf(), exclusao
							.getContraparte().getNome(), exclusao
							.getRestricao().getValorDebito());

			MessageUtil.addGlobalInfoMessage(
					MessagesConstants.MSG_INFO_PROTOCOLO_GERADO, exclusao
							.getProtocolo().getNumero().toUpperCase(),
					sdfData.format(exclusao.getProtocolo().getDataGeracao()),
					sdfHora.format(exclusao.getProtocolo().getDataGeracao()));

			/* Nova Inclusão */
			prepareCreate();

		} catch (Exception e) {

			MessageUtil.addGlobalErrorMessage(
					MessagesConstants.MSG_ERROR_RESTRICAO_INCLUIR, exclusao
							.getContraparte().getCpf(), exclusao
							.getContraparte().getNome());
			throw e;

		}

	}

	@Override
	protected List<Exclusao> searchImpl(ExclusaoFilter filter) {

		List<Exclusao> exclusaos = new ArrayList<Exclusao>();
		restricoes = restricaoModel.listagemRestricaoAssociado(filter
				.getAssociado().getCodigo());

		if (restricoes != null && restricoes.size() > 0) {
			exclusaos.add(new Exclusao());
		}

		return exclusaos;

	}

	/* Metodos especificos dessa Entidade */

	public void associadoLocate() {

		Associado associado = associadoModel.localizarPorAssociadoEEntidade(
				this.getFilter().getAssociado().getCodigo(), this.getFilter()
						.getAssociado().getEntidade());

		if (associado != null) {
			this.getFilter().setAssociado(associado);
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

	/* **************************** */

	public String getMsgCustoOperacaoExcluir() {
		return msgCustoOperacaoExcluir;
	}

	public void setMsgCustoOperacaoExcluir(String msgCustoOperacaoExcluir) {
		this.msgCustoOperacaoExcluir = msgCustoOperacaoExcluir;
	}

	public List<Restricao> getRestricoes() {
		return restricoes;
	}

	public void setRestricoes(List<Restricao> restricoes) {
		this.restricoes = restricoes;
	}

	public Restricao getRestricaoSelected() {
		return restricaoSelected;
	}

	public void setRestricaoSelected(Restricao restricaoSelected) {
		this.restricaoSelected = restricaoSelected;
	}

}
