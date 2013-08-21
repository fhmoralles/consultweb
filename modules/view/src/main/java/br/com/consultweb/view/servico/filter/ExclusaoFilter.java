package br.com.consultweb.view.servico.filter;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import br.com.consultweb.domain.cadastro.Associado;
import br.com.consultweb.domain.cadastro.Entidade;
import br.com.webutils.ui.AbstractFacesBean;
import br.com.webutils.ui.filter.Filter;

public class ExclusaoFilter extends AbstractFacesBean implements Filter {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(ExclusaoFilter.class);
	
	/* Filtros */
	private Entidade entidade;
	private Boolean entidadeDisabled;

	private Associado associado;
	private Boolean associadoDisabled;

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public void reset() {
		this.setEntidade(null);
		this.setEntidadeDisabled(false);
		this.setAssociado(null);
		this.setAssociadoDisabled(false);
	}

	@Override
	public String getValidationMessage() {
		return StringUtils.EMPTY;
	}

	public Entidade getEntidade() {
		return entidade;
	}

	public void setEntidade(Entidade entidade) {
		this.entidade = entidade;
	}

	public Boolean getEntidadeDisabled() {
		return entidadeDisabled;
	}

	public void setEntidadeDisabled(Boolean entidadeDisabled) {
		this.entidadeDisabled = entidadeDisabled;
	}

	public Associado getAssociado() {
		return associado;
	}

	public void setAssociado(Associado associado) {
		this.associado = associado;
	}

	public Boolean getAssociadoDisabled() {
		return associadoDisabled;
	}

	public void setAssociadoDisabled(Boolean associadoDisabled) {
		this.associadoDisabled = associadoDisabled;
	}

}
