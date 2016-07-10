package org.joey.contacts.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import org.joey.contacts.entities.Address;

public class AddressRepository {
	
	//createEntityManagerFactory(java.lang.String persistenceUnitName) 
	private final EntityManager entityManager=Persistence.createEntityManagerFactory("contacts").createEntityManager();
	
	//delete Constructor;
	//delete init();
	
	public Address find(long id) {
		//this doing  unmarchal. 
		return entityManager.find(Address.class, id);
	}
	
	public void save(Address address) {
		entityManager.merge(address);
	}
	
	public void delete(Address address) {
		entityManager.remove(address);
	}
}
