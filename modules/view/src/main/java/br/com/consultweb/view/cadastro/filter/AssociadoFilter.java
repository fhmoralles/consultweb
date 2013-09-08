package br.com.consultweb.view.cadastro.filter;

import org.apache.log4j.Logger;

import br.com.consultweb.domain.cadastro.Entidade;
import br.com.webutils.ui.filter.Filter;

public class AssociadoFilter implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8922200893234634082L;

	private static final Logger LOG = Logger.getLogger(AssociadoFilter.class);

	/* Filtros */
	private String fantasia;
	
	@Override
	public void reset() {
		this.setFantasia(null);
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public String getValidationMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}

}
