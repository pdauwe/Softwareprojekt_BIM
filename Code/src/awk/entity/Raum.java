package awk.entity;

public class Raum {

	private int anzahlPlaetze;
	private String name;
	private boolean isComputerraum;
	
	public Raum (String name, int anzahlPlaetze, boolean isComputerraum)
	{
		this.name = name;
		this.anzahlPlaetze = anzahlPlaetze;
		this.isComputerraum = isComputerraum;
	}
	
	public int getAnzahlPlaetze() {
		return anzahlPlaetze;
	}
	public void setAnzahlPlaetze(int anzahlPlaetze) {
		this.anzahlPlaetze = anzahlPlaetze;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isComputerraum() {
		return isComputerraum;
	}
	public void setComputerraum(boolean isComputerraum) {
		this.isComputerraum = isComputerraum;
	}
	
	
}
