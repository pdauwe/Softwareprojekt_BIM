package awk.entity;

import java.io.Serializable;

import awk.entity.internal.Modul;

public class ModulTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int nummer;
	private boolean benoetigtComputerraum;
	private boolean isVerplant;
	
	public ModulTO(){
		
	}
	
	public Modul toModul(){
		return new Modul(this.getNummer(), this.isBenoetigtComputerraum());
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
	
	public boolean isVerplant() {
		return isVerplant;
	}

	public void setVerplant(boolean isVerplant) {
		this.isVerplant = isVerplant;
	}
	
	
	
}
