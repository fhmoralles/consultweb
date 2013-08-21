package br.com.consultweb.view.servico.filter;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import br.com.webutils.ui.AbstractFacesBean;
import br.com.webutils.ui.filter.Filter;

public class RestricaoFilter extends AbstractFacesBean implements Filter {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(RestricaoFilter.class);
	
	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getValidationMessage() {
		return StringUtils.EMPTY;
	}

}
