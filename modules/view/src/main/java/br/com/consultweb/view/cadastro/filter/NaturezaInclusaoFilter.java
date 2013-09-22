package br.com.consultweb.view.cadastro.filter;

import org.apache.commons.lang.StringUtils;

import br.com.webutils.ui.filter.Filter;

public class NaturezaInclusaoFilter implements Filter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6178943602875397379L;
	
	private String descricao;
	
	@Override
	public void reset() {
		this.setDescricao(StringUtils.EMPTY);
	}

	@Override
	public boolean isValid() {
		return !(this.getDescricao().equals(StringUtils.EMPTY));
	}

	@Override
	public String getValidationMessage() {
		return null;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
}
