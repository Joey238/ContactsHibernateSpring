package org.joey.contacts.repositories;


import org.joey.contacts.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepository extends JpaRepository<Person,Long>{
	
	
}
