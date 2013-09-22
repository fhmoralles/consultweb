package br.com.consultweb.view.main;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.jboss.seam.security.Identity;
import org.primefaces.context.RequestContext;

import br.com.consultweb.domain.parametros.LogOperacao;
import br.com.consultweb.domain.parametros.Operador;
import br.com.consultweb.model.parametros.spec.LogOperacaoModel;
import br.com.consultweb.view.main.security.ConsultUser;

@Named
@RequestScoped
public class LogoutUI implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(LogoutUI.class);
	
    @Inject
    private Identity identity;
	
	@EJB
	private LogOperacaoModel logOperacaoModel;
    
	public String logout() {
		
		Operador operador = ((ConsultUser)identity.getUser()).getOperador();
		
		try {
			/* Inserindo Log */
			LogOperacao logOperacao = new LogOperacao();
			logOperacao.setDescricao("Login - Operador Codigo = " + operador.getCodigo());
			logOperacao.setOperacao("LOGOUT");
			logOperacao.setOperador(operador);
			logOperacao = logOperacaoModel.update(logOperacao);
			
			/* Encerra a sessão */
			identity.logout();
			
			
		}catch (Exception e) {
			LOG.error(e, e);
		} finally {
			RequestContext.getCurrentInstance().update("mainForm");
		}
		
		return "/login.xhtml";
		
	}
}
