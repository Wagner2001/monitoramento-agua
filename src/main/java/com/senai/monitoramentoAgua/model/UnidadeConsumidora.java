package com.senai.monitoramentoAgua.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class UnidadeConsumidora {

	@Id
	@SequenceGenerator(name = "UNIDADE_GENERATOR", sequenceName = "idUnidadeConsumidora", allocationSize = 1)
	@GeneratedValue(generator = "UNIDADE_GENERATOR", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String endereco;
	
	@ManyToOne
	@JoinColumn(name = "unidade_usuario_fk")
	private Usuario usuario;
	
	@OneToMany(mappedBy = "unidadeConsumidora")
	private Set<Consumo> consumoUnidade;

	public UnidadeConsumidora() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<Consumo> getConsumoUnidade() {
		return consumoUnidade;
	}

	public void setConsumoUnidade(Set<Consumo> consumoUnidade) {
		this.consumoUnidade = consumoUnidade;
	}
	
}
