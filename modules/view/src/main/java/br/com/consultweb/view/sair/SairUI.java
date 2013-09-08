package br.com.consultweb.view.sair;


import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.webutils.ui.AbstractFacesBean;

@Named
@RequestScoped
public class SairUI extends AbstractFacesBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2596898919057177681L;

	public String sobre() {

		return "/forms/sobre/sobreFrm.xhtml";

	}

	public String release() {

		return "/forms/sobre/releaseNotesFrm.xhtml";

	}

}
