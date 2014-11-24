package awk.entity;

import java.io.Serializable;

import awk.entity.internal.Studiengang;

public class StudiengangTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	
	public StudiengangTO(){
		
	}

	public Studiengang toStudiengang(){
		return new Studiengang(this.getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
