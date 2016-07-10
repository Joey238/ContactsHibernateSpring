package org.joey.contacts.servlets;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.joey.contacts.entities.Address;
import org.joey.contacts.entities.Contact;
import org.joey.contacts.repositories.AddressRepository;
import org.joey.contacts.repositories.ContactRepository;

@WebListener
public class Setup implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		//instantiate a AddressRepository
		//init address table
		//go nuts!
		try {
			new AddressRepository().init();
				//new AddressRepository().create(new Address("1001 Yosko Dr","Edison","NJ","08817"));
			new ContactRepository().init();;
					//new ContactRepository().create(new Contact("Joey",1L));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		
	}

}
