package awk.entity;

import java.io.Serializable;

import awk.entity.internal.Semester;

public class SemesterTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	
	public SemesterTO(){
		
	}
	
	public Semester toSemester(){
		return new Semester(this.getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
