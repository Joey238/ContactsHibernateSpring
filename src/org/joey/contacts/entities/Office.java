package org.joey.contacts.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="office")
public class Office extends UrlEntity{

	@Column
	private String name;
	
	@OneToOne(cascade=CascadeType.ALL) //Try to save new office and new address at the same time.
	private Address address;
	
	//it only side relationship now between company-office , many offices may have the same company
	@ManyToOne //For JPA, owner side 
	@JoinColumn(name="company_fk") 
	private Company company;
	
	public Office(){}
	
	public Office(String name, Address address,Company company ){
		this.name=name;
		this.address=address;
		this.company=company;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	
}
