package awk.entity;

import java.io.Serializable;

import awk.entity.internal.Raum;

public class RaumTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private boolean isComputerraum;
	
	public RaumTO(){
		
	}
	
	public Raum toRaum(){
		return new Raum(this.getName(), this.isComputerraum());
	}

	public boolean isComputerraum() {
		return isComputerraum;
	}

	public void setComputerraum(boolean isComputerraum) {
		this.isComputerraum = isComputerraum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		RaumTO r = (RaumTO) obj;
		if(r.getName().equals(this.getName())){
			return true;
		}
		return false;
	}

}
