package awk.entity;

import java.io.Serializable;
import java.util.Set;

import awk.entity.internal.Zeit;

public class ZeitTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DozentTO dozent;
	private ModulTO modul;
	private Set<UhrzeitTO> bevorzugteZeiten;
	
	public ZeitTO(){
		
	}
	
	public Zeit toZeit(){
		Zeit z = new Zeit(this.getDozent().toDozent(), this.getModul().toModul());
		for(UhrzeitTO u : this.getBevorzugteZeiten()){
			z.addBevorzugteZeiten(u.toUhrzeit());
		}
		return z;
	}

	public DozentTO getDozent() {
		return dozent;
	}

	public void setDozent(DozentTO dozent) {
		this.dozent = dozent;
	}

	public ModulTO getModul() {
		return modul;
	}

	public void setModul(ModulTO modul) {
		this.modul = modul;
	}

	public Set<UhrzeitTO> getBevorzugteZeiten() {
		return bevorzugteZeiten;
	}

	public void addBevorzugteZeiten(UhrzeitTO bevorzugteZeit) {
		this.bevorzugteZeiten.add(bevorzugteZeit);
	}
	

}
