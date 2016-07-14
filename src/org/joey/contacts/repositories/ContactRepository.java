package org.joey.contacts.repositories;


import org.joey.contacts.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContactRepository extends JpaRepository<Contact,Long>{
	
	
}
