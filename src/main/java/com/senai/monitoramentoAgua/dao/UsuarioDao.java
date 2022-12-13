package com.senai.monitoramentoAgua.dao;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.senai.monitoramentoAgua.model.Usuario;

@Stateless
public class UsuarioDao extends AbstractDao<Usuario>{

	public UsuarioDao() {
		super(Usuario.class);
	}
	
	public boolean isEmailESenhaCorreto(String email, String senha) {
		
		String query = new StringBuilder()
				.append(" SELECT U FROM Usuario U ")
				.append(" WHERE U.senha = :senha AND U.email = :email ")
				.toString();
		
		TypedQuery<Usuario> tp = entityManager.createQuery(query, Usuario.class);
		tp.setParameter("senha", senha);
		tp.setParameter("email", email);
		
		boolean loginCorreto = tp.getResultList().size() == 1 ? true : false;
		
		return loginCorreto;
	}
	
	public Usuario findByEmail(String email) {
		String query = new StringBuilder()
				.append(" SELECT U FROM Usuario U ")
				.append(" WHERE U.email = :email ")
				.toString();
		
		TypedQuery<Usuario> tp = entityManager.createQuery(query, Usuario.class);
		tp.setParameter("email", email);
		
		return tp.getSingleResult();
	}
	
}
