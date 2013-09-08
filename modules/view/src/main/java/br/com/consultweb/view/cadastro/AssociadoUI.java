package br.com.consultweb.view.cadastro;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.jboss.seam.security.annotations.LoggedIn;

import br.com.consultweb.domain.cadastro.Associado;
import br.com.consultweb.model.cadastro.spec.AssociadoModel;
import br.com.consultweb.view.cadastro.filter.AssociadoFilter;
import br.com.webutils.ui.AbstractCRUD;

@Named
@SessionScoped
@LoggedIn
public class AssociadoUI extends AbstractCRUD<Associado, AssociadoFilter> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6083519175853062663L;

	private static final Logger LOG = Logger.getLogger(AssociadoUI.class);

	/* Modelo */
	@Inject
	private AssociadoModel associadoModel;
	
	public AssociadoUI() {
		super(new AssociadoFilter());
	}

	@Override
	protected void cleanUpImpl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void deleteImpl(Associado bean) {
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean isSearchOnPrepare() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean isValidBean(Associado bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isViewable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Associado newInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void saveImpl(Associado bean) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected List<Associado> searchImpl(AssociadoFilter filter) {
		return associadoModel.getAssociadosPorFantasia("%" + filter.getFantasia() + "%");
	}

}
