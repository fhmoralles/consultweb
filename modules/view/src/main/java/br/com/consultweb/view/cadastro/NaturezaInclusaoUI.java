package br.com.consultweb.view.cadastro;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.LoggedIn;

import br.com.consultweb.domain.parametros.Operador;
import br.com.consultweb.domain.servico.NaturezaInclusao;
import br.com.consultweb.model.servico.spec.NaturezaInclusaoModel;
import br.com.consultweb.view.cadastro.filter.NaturezaInclusaoFilter;
import br.com.consultweb.view.main.security.ConsultUser;
import br.com.webutils.ui.AbstractCRUD;

@Named
@SessionScoped
@LoggedIn
public class NaturezaInclusaoUI extends AbstractCRUD<NaturezaInclusao, NaturezaInclusaoFilter> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5533016745298915585L;

	private static final String PESQUISA_NATUREZA_INCLUSAO = "/forms/cadastros/naturezaInclusaoPesquisar.xhtml";
	private static final String CADASTRO_NATUREZA_INCLUSAO = "/forms/cadastros/naturezaInclusaoCadastrar.xhtml";
	
	/* Model */
	@Inject
	private NaturezaInclusaoModel naturezaInclusaoModel;
	
	/* Informações de Login */
	@Inject
	private Identity identity;
	
	public NaturezaInclusaoUI() {
		super(new NaturezaInclusaoFilter());
	}
	
	@Override
	protected void cleanUpImpl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void deleteImpl(NaturezaInclusao bean) throws Exception {
		Operador operador = ((ConsultUser)identity.getUser()).getOperador();
		naturezaInclusaoModel.delete(bean, operador);
	}

	@Override
	protected String getMsgDelete() {
		
		if(this.getBean() != null) {
			return this.getBean().getId() + " - " + this.getBean().getDescricao();
		}
		
		return null;
	}
	
	@Override
	protected String getActionCreate() {
		return CADASTRO_NATUREZA_INCLUSAO;
	}

	@Override
	protected String getActionDetail() {
		return CADASTRO_NATUREZA_INCLUSAO;
	}

	@Override
	protected String getActionEdit() {
		return CADASTRO_NATUREZA_INCLUSAO;
	}

	@Override
	protected String getActionSearch() {
		return PESQUISA_NATUREZA_INCLUSAO;
	}

	@Override
	public boolean isDeletable() {
		return true;
	}

	@Override
	public boolean isEditable() {
		return true;
	}

	@Override
	public boolean isInsertable() {
		return true;
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
	protected boolean isValidBean(NaturezaInclusao bean) {
		return true;
	}

	@Override
	public boolean isViewable() {
		return true;
	}

	@Override
	protected NaturezaInclusao newInstance() {
		return new NaturezaInclusao();
	}

	@Override
	protected void saveImpl(NaturezaInclusao bean) throws Exception {
		
		Operador operador = ((ConsultUser)identity.getUser()).getOperador();
		naturezaInclusaoModel.update(this.getBean(), operador);
	}

	@Override
	protected List<NaturezaInclusao> searchImpl(NaturezaInclusaoFilter filter) {
		return naturezaInclusaoModel.getNaturezaInclusaoPorDescricao("%" + filter.getDescricao() + "%");
	}

}
