package com.senai.monitoramentoAgua.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.senai.monitoramentoAgua.model.UnidadeConsumidora;

@Stateless
public class UnidadeConsumidoraDao extends AbstractDao<UnidadeConsumidora>{

	public UnidadeConsumidoraDao() {
		super(UnidadeConsumidora.class);
	}
	
	public List<UnidadeConsumidora> getUnidadesByIdUsuario(Long idUsuario){
		String query = new StringBuilder()
				.append(" SELECT U FROM UnidadeConsumidora U ")
				.append(" WHERE U.usuario.id = :idUsuario ")
				.toString();
		
		TypedQuery<UnidadeConsumidora> tq = entityManager.createQuery(query, UnidadeConsumidora.class);
		
		tq.setParameter("idUsuario", idUsuario);
		
		return tq.getResultList();
	}
	
}
