package com.senai.monitoramentoAgua.Service;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.senai.monitoramentoAgua.dao.UsuarioDao;
import com.senai.monitoramentoAgua.model.Usuario;

@Stateless
public class UsuarioService {

	@Inject
	private UsuarioDao usuarioDao;
	
	public void logarUsuario(Usuario usuario) {
		boolean logadoComSucesso = usuarioDao.isEmailESenhaCorreto(usuario.getEmail(), usuario.getSenha());
	
		if(logadoComSucesso){
			Usuario usuarioLogado = usuarioDao.findByEmail(usuario.getEmail());
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", usuarioLogado);
		} else {
			throw new RuntimeException("Email ou senha incorretos!");
		}
	
	}
	
	public void deslogarUsuario() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuarioLogado");
	}
	
}
