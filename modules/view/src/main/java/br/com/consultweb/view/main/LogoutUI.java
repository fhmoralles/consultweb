package br.com.consultweb.view.main;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.seam.security.Identity;
import org.primefaces.context.RequestContext;

@Named
@RequestScoped
public class LogoutUI implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Inject
    private Identity identity;
	
    
	public String logout() {
		
		System.out.println("outcome");
		try {
			identity.logout();
		}catch (Exception e) {
			// TODO: trartar. É bug do JBoss
		} finally {
			RequestContext.getCurrentInstance().update("mainForm");
		}
		
		return "/login.xhtml";
		
	}
}
