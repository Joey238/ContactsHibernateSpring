package org.joey.contacts.entities;

public class Contact {
	private long id;
	private String name;
	private long addressId;
	
	public Contact(){}
	
	public Contact(String name, Long addressId) {
		super();
		this.name = name;
		this.addressId = addressId;
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
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	
	
	
}
