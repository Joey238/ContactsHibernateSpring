package org.joey.contacts.entities;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class UrlEntity extends BaseEntity{
	
	public String getUrl(){
		return this.getClass().getSimpleName().toLowerCase()+"?id="+ getId();
	}
}
