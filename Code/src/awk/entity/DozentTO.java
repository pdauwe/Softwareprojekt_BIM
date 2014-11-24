package awk.entity;

import java.io.Serializable;

import awk.entity.internal.Dozent;

public class DozentTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	
	public DozentTO(){
		
	}
	
	public Dozent toDozent(){
		return new Dozent(this.getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
