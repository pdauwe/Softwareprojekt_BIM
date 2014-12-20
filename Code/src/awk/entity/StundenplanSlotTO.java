package awk.entity;

import java.io.Serializable;

import awk.entity.internal.StundenplanSlot;

public class StundenplanSlotTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ModulTO modul;
	private DozentTO dozent;
	private RaumTO raum;
	
	public StundenplanSlotTO(){
		
	}
	
	public StundenplanSlot toStundenplanSlot(){
		return new StundenplanSlot(modul.toModul(), dozent.toDozent(), raum.toRaum());
	}

	public ModulTO getModul() {
		return modul;
	}

	public void setModul(ModulTO modul) {
		this.modul = modul;
	}

	public DozentTO getDozent() {
		return dozent;
	}

	public void setDozent(DozentTO dozent) {
		this.dozent = dozent;
	}

	public RaumTO getRaum() {
		return raum;
	}

	public void setRaum(RaumTO raum) {
		this.raum = raum;
	}
	
	
	
}
