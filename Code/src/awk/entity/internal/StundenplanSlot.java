package awk.entity.internal;

/**
 * Wrapper-Klasse, die alle Informationen zu einem Zeitslot im Studenplan enthält
 */
public class StundenplanSlot {

	private Modul modul;
	private Dozent dozent;
	private Raum raum;
	
	public StundenplanSlot(Modul modul, Dozent dozent, Raum raum)
	{
		this.modul = modul;
		this.dozent = dozent;
		this.raum = raum;
	}

	public Modul getModul() {
		return modul;
	}

	public void setModul(Modul modul) {
		this.modul = modul;
	}

	public Dozent getDozent() {
		return dozent;
	}

	public void setDozent(Dozent dozent) {
		this.dozent = dozent;
	}

	public Raum getRaum() {
		return raum;
	}

	public void setRaum(Raum raum) {
		this.raum = raum;
	}
	
	
	
}
