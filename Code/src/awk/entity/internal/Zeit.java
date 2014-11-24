package awk.entity.internal;

import java.util.Set;

public class Zeit {

	private Dozent dozent;
	private Modul modul;
	private Set<Uhrzeit> bevorzugteZeiten;
	
	public Zeit (Dozent dozent, Modul modul){
		this.dozent = dozent;
		this.modul = modul;
	}

	public Dozent getDozent() {
		return dozent;
	}

	public void setDozent(Dozent dozent) {
		this.dozent = dozent;
	}

	public Modul getModul() {
		return modul;
	}

	public void setModul(Modul modul) {
		this.modul = modul;
	}

	public Set<Uhrzeit> getBevorzugteZeiten() {
		return bevorzugteZeiten;
	}

	public void addBevorzugteZeiten(Uhrzeit bevorzugteZeit) {
		this.bevorzugteZeiten.add(bevorzugteZeit);
	}
	
	
	
}
