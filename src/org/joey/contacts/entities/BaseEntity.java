package org.joey.contacts.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/*This is sometimes useful to share common properties through a technical or a 
  business superclass without including it as a regular mapped entity (ie no specific table for this entity). 
For that purpose you can map them as @MappedSuperclass.
	-Classes annotated with @MappedSuperClass are like abstract classes in java
	-They are non persistent.
	-They cannot be queried over and are not mapped to any database table.
	-They are only used to contribute state and behavior to entities those are inherited from them.
	-It is good practice to mark abstract classes as @MappedSuperClass in jpa inheritance.
**/

@MappedSuperclass
public abstract class BaseEntity {
	
	@Id
	@GeneratedValue
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
}
