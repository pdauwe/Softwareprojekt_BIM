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

	public ModulTO getModulName() {
		return modul;
	}

	public void setModulName(ModulTO modulName) {
		this.modul = modulName;
	}

	public DozentTO getDozentName() {
		return dozent;
	}

	public void setDozentName(DozentTO dozentName) {
		this.dozent = dozentName;
	}

	public RaumTO getRaumName() {
		return raum;
	}

	public void setRaumName(RaumTO raumName) {
		this.raum = raumName;
	}
	
	
	
}
