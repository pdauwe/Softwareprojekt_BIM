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
	private String bezeichnung;
	
	public ModulTO(){
		
	}
	
	public Modul toModul(){
		return new Modul(this.getNummer(), this.getBezeichnung(), this.isBenoetigtComputerraum());
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

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	@Override
	public int hashCode() {
		return this.getBezeichnung().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		ModulTO m = (ModulTO) obj;
		if(m.getBezeichnung().equals(this.getBezeichnung())){
			return true;
		}
		return false;
	}
}
