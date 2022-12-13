package com.senai.monitoramentoAgua.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.senai.monitoramentoAgua.Service.UnidadeConsumidoraService;
import com.senai.monitoramentoAgua.model.UnidadeConsumidora;
import com.senai.monitoramentoAgua.model.Usuario;

@Named
@ViewScoped
public class FormUnidadeConsumidoraController implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private UnidadeConsumidoraService unidadeConsumidoraService;
	
	private UnidadeConsumidora unidadeConsumidora;

	@PostConstruct
	public void init() {
		
		UnidadeConsumidora unidadeConsumidora = (UnidadeConsumidora) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("unidade");

		if(Objects.nonNull(unidadeConsumidora)) {
			this.unidadeConsumidora = unidadeConsumidora;
			
		} else {
			this.unidadeConsumidora = new UnidadeConsumidora();
			Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
			this.unidadeConsumidora.setUsuario(usuario);
		}
	}
	
	public void salvar() throws IOException {
		if(Objects.isNull(unidadeConsumidora.getId())) {
			unidadeConsumidoraService.incluirUnidade(unidadeConsumidora);
			this.unidadeConsumidora = new UnidadeConsumidora();
			FacesContext.getCurrentInstance().getExternalContext().redirect("unidades-consumidoras.xhtml");
		} else {
			unidadeConsumidoraService.atualizarUnidade(unidadeConsumidora.getId(), unidadeConsumidora);
			this.unidadeConsumidora = new UnidadeConsumidora();
			FacesContext.getCurrentInstance().getExternalContext().redirect("unidades-consumidoras.xhtml");
		}
	}
	
	public UnidadeConsumidora getUnidadeConsumidora() {
		return unidadeConsumidora;
	}

	public void setUnidadeConsumidora(UnidadeConsumidora unidadeConsumidora) {
		this.unidadeConsumidora = unidadeConsumidora;
	}
	
}
