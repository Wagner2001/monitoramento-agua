package com.senai.monitoramentoAgua.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Usuario {

	@Id
	@SequenceGenerator(name = "USUARIO_GENERATOR", sequenceName = "idUsuario", allocationSize = 1)
	@GeneratedValue(generator = "USUARIO_GENERATOR", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String nome;
	
	private String cpf;
	
	private String senha;
	
	private String email;
	
	@OneToMany(mappedBy = "usuario")
	private Set<UnidadeConsumidora> unidadesConsumidoras;

	public Usuario() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<UnidadeConsumidora> getUnidadesConsumidoras() {
		return unidadesConsumidoras;
	}

	public void setUnidadesConsumidoras(Set<UnidadeConsumidora> unidadesConsumidoras) {
		this.unidadesConsumidoras = unidadesConsumidoras;
	}

}
