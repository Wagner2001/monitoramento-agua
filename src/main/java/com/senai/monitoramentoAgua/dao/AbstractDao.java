package com.senai.monitoramentoAgua.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class AbstractDao <T> {

	@PersistenceContext
	EntityManager entityManager;
	
	private final Class<T> entityClass;
	
	public AbstractDao() {
		this.entityClass = null;
	}
	
	public AbstractDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	public void cadastrar(T entity){
		entityManager.persist(entity);
	}
	
	public void atualizar(T entity){
		entityManager.merge(entity);
	}
	
	public void excluir(T entity) {
		entityManager.remove(entity);
	}
	
	public T buscarPorId(Long id) {
		
		return entityManager.find(entityClass, id);
	}
	
	public List<T> buscarTodos(){
		String query = "SELECT e FROM " + entityClass.getSimpleName() + " e";
		TypedQuery<T> typedQuery = entityManager.createQuery(query, entityClass);
		return typedQuery.getResultList();
	}
	
}
