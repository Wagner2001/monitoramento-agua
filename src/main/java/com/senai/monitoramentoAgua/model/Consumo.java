package com.senai.monitoramentoAgua.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Consumo {

	@Id
	@SequenceGenerator(name = "CONSUMO_GENERATOR", sequenceName = "idConsumo", allocationSize = 1)
	@GeneratedValue(generator = "CONSUMO_GENERATOR", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "consumo_unidade_fk")
	private UnidadeConsumidora unidadeConsumidora;
	
	private LocalDate dataConsumo;
	
	private Double volumeConsumo;

	public Consumo() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UnidadeConsumidora getUnidadeConsumidora() {
		return unidadeConsumidora;
	}

	public void setUnidadeConsumidora(UnidadeConsumidora unidadeConsumidora) {
		this.unidadeConsumidora = unidadeConsumidora;
	}

	public LocalDate getDataConsumo() {
		return dataConsumo;
	}

	public void setDataConsumo(LocalDate dataConsumo) {
		this.dataConsumo = dataConsumo;
	}

	public Double getVolumeConsumo() {
		return volumeConsumo;
	}

	public void setVolumeConsumo(Double volumeConsumo) {
		this.volumeConsumo = volumeConsumo;
	} 
	
}
