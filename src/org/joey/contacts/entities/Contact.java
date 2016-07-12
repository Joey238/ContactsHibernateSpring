package org.joey.contacts.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity 
//table name is class name
public class Contact {
	
	@Id
	@GeneratedValue
	private long id;
	@Column
	private String name;

	 //private long addressId;
	@OneToOne(cascade=CascadeType.ALL)//
	private Address address;
	
	public Contact(){}
	
	public Contact(String name, Address address) {
		super();
		this.name = name;
		this.address=address;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
