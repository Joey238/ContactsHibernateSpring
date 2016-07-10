package org.joey.contacts.repositories;


import org.joey.contacts.entities.Address;

public class AddressRepository extends Repository<Address> {	
	//delete Constructor;
	//delete init();
	
	public AddressRepository() {
		super(Address.class);
	}
}
