package com.senai.monitoramentoAgua.util;

import java.io.IOException;
import java.util.Objects;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class LoginPhaseListener implements PhaseListener{
	
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {

		FacesContext context = event.getFacesContext();

		boolean isLoginPage = isLoginPageRequest(context);
		boolean isUserLogged = isUserLogged();
		
		if(!isUserLogged && !isLoginPage) {
			try {
				redirectToLogin(context);
			} catch (IOException e) { 	
				e.printStackTrace();
			}
			return;
		}
		if(isLoginPage && isUserLogged) {
			try {
				redirectToIndex(context);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

	
		private boolean isUserLogged() {
		Object object = FacesContext
				.getCurrentInstance()
				.getExternalContext()
				.getSessionMap()
				.get("usuarioLogado");
		return Objects.nonNull(object);
	}
	
	private boolean isLoginPageRequest(FacesContext context) {
		String requestURI = context.getViewRoot().getViewId();
		
		return "/login.xhtml".equals(requestURI);
	}
	
	private void redirectToLogin(FacesContext context) throws IOException {
		context.getExternalContext().redirect("login.xhtml");
	}
	
	private void redirectToIndex(FacesContext context) throws IOException {
		context.getExternalContext().redirect("unidades-consumidoras.xhtml");

	}

	
	
}
