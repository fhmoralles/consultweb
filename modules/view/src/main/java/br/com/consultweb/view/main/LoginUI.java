package br.com.consultweb.view.main;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.seam.security.Identity;
import org.primefaces.context.RequestContext;

import br.com.consultweb.domain.parametros.Operador;
import br.com.consultweb.view.main.constants.MessagesConstants;
import br.com.consultweb.view.main.security.ConsultUser;
import br.com.webutils.MessageUtil;
import br.com.webutils.ui.AbstractFacesBean;

@Named
@RequestScoped
public class LoginUI extends AbstractFacesBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2596898919057177681L;

	@Inject
	private Identity identity;

	public String login() {

		/* invoca login */
		identity.login();

		if (identity.isLoggedIn()) {

			MessageUtil.addGlobalInfoMessage(
					MessagesConstants.MSG_INFO_LOGIN_SUCESSO,
					((ConsultUser) identity.getUser()).getOperador().getNome());

			return "/main.xhtml";
		}

		return null;
	}

	public String prepareLogin() {
		return "/login.xhtml";
	}

}
