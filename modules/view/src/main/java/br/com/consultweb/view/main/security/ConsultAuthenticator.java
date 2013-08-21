package br.com.consultweb.view.main.security;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jboss.seam.security.Authenticator;
import org.jboss.seam.security.BaseAuthenticator;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;
import org.picketlink.idm.api.Credential;
import org.picketlink.idm.impl.api.PasswordCredential;

import br.com.consultweb.domain.parametros.Operador;
import br.com.consultweb.model.exceptions.OperadorDispositivoNaoAutorizadoException;
import br.com.consultweb.model.exceptions.OperadorHorarioNaoAutorizadoException;
import br.com.consultweb.model.exceptions.OperadorSenhaInvalidaException;
import br.com.consultweb.model.parametros.spec.OperadorModel;
import br.com.consultweb.view.main.constants.MessagesConstants;
import br.com.webutils.MessageUtil;

public class ConsultAuthenticator extends BaseAuthenticator implements Authenticator {

	private static final Logger LOG = Logger.getLogger(ConsultAuthenticator.class);

	@Inject
	private Credentials credentials;

	@Inject
	private Identity identity;
	
	@Inject
	private OperadorModel operadorModel;

	@Override
	public void authenticate() {

		LOG.info("User Authenticate");

		ConsultUser consultUser = null;
		Credential credential = credentials.getCredential();

		if (credential instanceof PasswordCredential) {

			String codigo = StringUtils.trim(credentials.getUsername());
			String senha = ((PasswordCredential) credential).getValue();

			LOG.info("Password Credential: " + codigo);

			try {
				
				Operador operador = operadorModel.login(Integer.parseInt(codigo), senha);

				if (operador != null) {
					consultUser = new ConsultUser(operador);
					setStatus(AuthenticationStatus.SUCCESS);
					setUser(consultUser);
					LOG.info("Autenticado: " + codigo);
					
				} else {
					setStatus(AuthenticationStatus.FAILURE);
					MessageUtil.addGlobalErrorMessage(MessagesConstants.MSG_ERROR_LOGIN_INVALIDO);
					LOG.info("Não Autenticado: " + codigo);
				}
			} catch (OperadorDispositivoNaoAutorizadoException e) {
				LOG.error(e, e);
				MessageUtil.addGlobalErrorMessage(MessagesConstants.MSG_ERROR_OPERADOR_DISPOSITIVO_NAO_AUTORIZADO);
				setStatus(AuthenticationStatus.FAILURE);
			} catch (OperadorHorarioNaoAutorizadoException e) {
				LOG.error(e, e);
				MessageUtil.addGlobalErrorMessage(MessagesConstants.MSG_ERROR_OPERADOR_HORARIO_NAO_AUTORIZADO);
				setStatus(AuthenticationStatus.FAILURE);
			} catch (OperadorSenhaInvalidaException e) {
				LOG.error(e, e);
				MessageUtil.addGlobalErrorMessage(MessagesConstants.MSG_ERROR_OPERADOR_SENHA_INVALIDA);
				setStatus(AuthenticationStatus.FAILURE);
			} catch (Exception e) {
				LOG.error(e, e);
				MessageUtil.addGlobalErrorMessage(MessagesConstants.MSG_ERROR_LOGIN_INVALIDO);
				setStatus(AuthenticationStatus.FAILURE);
			}
		} else {
			setStatus(AuthenticationStatus.FAILURE);
		}
	}
	
}
