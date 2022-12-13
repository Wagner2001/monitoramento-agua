package com.senai.monitoramentoAgua.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.senai.monitoramentoAgua.Service.UsuarioService;
import com.senai.monitoramentoAgua.model.Usuario;

@Named
@ViewScoped
public class LoginController implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioService usuarioService;
	
	private Usuario usuario;

	@PostConstruct
	public void init() {
		this.usuario = new Usuario();
	}
	
	
	public void efetuarLogin() {

		try {
			usuarioService.logarUsuario(usuario);
			
			FacesContext.getCurrentInstance().getExternalContext().redirect("unidades-consumidoras.xhtml");
			
		} catch (Exception e) {
			usuario = new Usuario();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
	}
	
	public void deslogar() {
		usuarioService.deslogarUsuario();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
