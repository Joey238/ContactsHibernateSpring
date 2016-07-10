package org.joey.contacts.repositories;

import org.joey.contacts.entities.Contact;

public class ContactRepository extends Repository<Contact> {

	public ContactRepository() {
		super(Contact.class);
	}
	
}
