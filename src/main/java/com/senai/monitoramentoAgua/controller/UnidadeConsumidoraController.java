package com.senai.monitoramentoAgua.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

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
public class UnidadeConsumidoraController implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private UnidadeConsumidoraService unidadeConsumidoraService;
	
	List<UnidadeConsumidora> unidadesConsumidoras;
	
	private Long idExclusao;
	private Long idUsuarioLogado;
	
	@PostConstruct
	public void init() {
		Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
		
		this.idUsuarioLogado = usuario.getId();

		carregarUnidades();
	}

	public void editar(UnidadeConsumidora unidadeConsumidora) throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("unidade", unidadeConsumidora);
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("form-unidade-consumidora.xhtml");
	}
	
	public void excluir() {
		unidadeConsumidoraService.excluirUnidade(idExclusao);
		carregarUnidades();
	}
	
	public void visualizarDashboard(UnidadeConsumidora unidadeConsumidora) throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("unidade", unidadeConsumidora);
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("dashboard-consumo.xhtml");
	}
	
	private void carregarUnidades() {
		this.unidadesConsumidoras = unidadeConsumidoraService.getUnidadesByIdUsuario(idUsuarioLogado);
	}
	
	public List<UnidadeConsumidora> getUnidadesConsumidoras() {
		return unidadesConsumidoras;
	}

	public void setUnidadesConsumidoras(List<UnidadeConsumidora> unidadesConsumidoras) {
		this.unidadesConsumidoras = unidadesConsumidoras;
	}

	public Long getIdExclusao() {
		return idExclusao;
	}

	public void setIdExclusao(Long idExclusao) {
		this.idExclusao = idExclusao;
	}

	public Long getIdUsuarioLogado() {
		return idUsuarioLogado;
	}

	public void setIdUsuarioLogado(Long idUsuarioLogado) {
		this.idUsuarioLogado = idUsuarioLogado;
	}
	
}
