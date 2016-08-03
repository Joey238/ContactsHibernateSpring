package org.joey.contacts.repositories;


import org.joey.contacts.entities.Company;
import org.joey.contacts.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompanyRepository extends JpaRepository<Company,Long>{
	
	
}
