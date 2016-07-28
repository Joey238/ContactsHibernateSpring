package org.joey.contacts.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity 
public class Contact {
	
	@Id
	@GeneratedValue
	private long id;
	@Column
	private String name;

	
	public Contact(){}
	
	public Contact(String name) {
		super();
		this.name = name;
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

	public void setId(long id) {
		this.id = id;
	}
	
	
}
