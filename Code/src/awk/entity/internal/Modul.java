package awk.entity.internal;

public class Modul {

	private int nummer;
	private boolean benoetigtComputerraum;
	
	public Modul(int nummer, boolean benoetigtComputerraum){
		this.nummer = nummer;
		this.benoetigtComputerraum = benoetigtComputerraum;
	}
	
	public int getNummer() {
		return nummer;
	}
	public void setNummer(int nummer) {
		this.nummer = nummer;
	}
	public boolean isBenoetigtComputerraum() {
		return benoetigtComputerraum;
	}
	public void setBenoetigtComputerraum(boolean benoetigtComputerraum) {
		this.benoetigtComputerraum = benoetigtComputerraum;
	}
	
	
}
