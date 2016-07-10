package org.joey.contacts.repositories;

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
	
	public void save(E entity){
		entityManager.merge(entity);
	}
	
	public void delete(E entity){
		entityManager.remove(entity);
	}
}
