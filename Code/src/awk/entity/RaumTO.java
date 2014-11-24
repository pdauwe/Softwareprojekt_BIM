package awk.entity;

import java.io.Serializable;

import awk.entity.internal.Raum;

public class RaumTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int anzahlPlaetze;
	private String name;
	private boolean isComputerraum;
	
	public RaumTO(){
		
	}
	
	public Raum toRaum(){
		return new Raum(this.getName(), this.getAnzahlPlaetze(), this.isComputerraum());
	}
	
	public int getAnzahlPlaetze() {
		return anzahlPlaetze;
	}

	public void setAnzahlPlaetze(int anzahlPlaetze) {
		this.anzahlPlaetze = anzahlPlaetze;
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

}
