package awk.entity;

import java.io.Serializable;

import awk.entity.internal.ModulWrapper;

public class ModulWrapperTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String modulName;
	private String dozentName;
	private String raumName;
	private String zeit;
	
	public ModulWrapperTO(){
		
	}
	
	public ModulWrapper toModulWrapper(){
		return new ModulWrapper(this.getModulName(),this.getDozentName(),this.getRaumName(),this.getZeit());
	}
	
	public String getModulName() {
		return modulName;
	}
	public void setModulName(String modulName) {
		this.modulName = modulName;
	}
	public String getDozentName() {
		return dozentName;
	}
	public void setDozentName(String dozentName) {
		this.dozentName = dozentName;
	}
	public String getRaumName() {
		return raumName;
	}
	public void setRaumName(String raumName) {
		this.raumName = raumName;
	}
	public String getZeit() {
		return zeit;
	}
	public void setZeit(String zeit) {
		this.zeit = zeit;
	}
	
	
}
