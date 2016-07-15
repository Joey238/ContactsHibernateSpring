package org.joey.contacts.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;


public class Repository<E>{
	//createEntityManagerFactory(java.lang.String persistenceUnitName) 
	private final EntityManager entityManager=Persistence.createEntityManagerFactory("contacts").createEntityManager();
	private final Class<E> entityClass;
	
	public Repository(Class<E> enityClass){
		 this.entityClass=enityClass;
	}
	
	public E find(long id){
		return entityManager.find(entityClass, id);
	}
	
	public E save(E entity){
		entityManager.getTransaction().begin();
		entity=entityManager.merge(entity);
		entityManager.getTransaction().commit();
		return entity;
	}
	 
	public void delete(E entity){ 
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();

	}
	
	public List<E> findAll(){
		return entityManager.createQuery("from "+ entityClass.getSimpleName(),entityClass).getResultList();
	}
}
