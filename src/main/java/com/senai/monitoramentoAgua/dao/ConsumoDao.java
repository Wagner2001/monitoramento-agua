package com.senai.monitoramentoAgua.dao;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.senai.monitoramentoAgua.model.Consumo;

@Stateless
public class ConsumoDao extends AbstractDao<Consumo>{

	public ConsumoDao() {
		super(Consumo.class);
	}
	
	public List<Consumo> getConsumoPorMesEAno(Long idUnidade, Long ano, Long mes){
		
		String query = new StringBuilder()
				.append(" SELECT c FROM Consumo c ")
				.append(" WHERE EXTRACT (year FROM c.dataConsumo) = :ano ")
				.append(" AND EXTRACT (month FROM c.dataConsumo) = :mes ")
				.append(" AND c.unidadeConsumidora.id = :idUnidade ")
				.toString();
		
		TypedQuery<Consumo> tq = entityManager.createQuery(query, Consumo.class);
		tq.setParameter("ano", ano.intValue());
		tq.setParameter("mes", mes.intValue());
		tq.setParameter("idUnidade", idUnidade);
		
		return tq.getResultList();
	}
	
	public List<Consumo> getConsumoPorData(Long idUnidade, LocalDate data){
		
		String query = new StringBuilder()
				.append(" SELECT C FROM Consumo C ")
				.append(" WHERE C.dataConsumo = :data ")
				.append(" AND C.unidadeConsumidora.id = :idUnidade ")
				.toString();
		
		TypedQuery<Consumo> tq = entityManager.createQuery(query, Consumo.class);
		tq.setParameter("data", data);
		tq.setParameter("idUnidade", idUnidade);
		
		return tq.getResultList();
	}
	
}
