package awk.usecase.impl;

import java.util.ArrayList;
import java.util.Random;

import awk.AnwendungskernException;
import awk.DatenhaltungsException;
import awk.entity.DozentTO;
import awk.entity.StudiengangTO;
import awk.entity.internal.Dozent;
import awk.persistence.IStundenplanDatenzugriff;
import awk.persistence.impl.StundenplanDatenzugriff;

/***
 * Klasse zur Verwaltung von Dozenten
 */
public class DozentManager {

	private IStundenplanDatenzugriff stundenplanDatenzugriff = new StundenplanDatenzugriff();
	
	private static DozentManager self;
	private ArrayList<Dozent> alleDozenten;
	
	/***
	 * @return DozentManager
	 */
	public static DozentManager getManager(){
		if(self == null){
			return new DozentManager();
		}else{
			return self;
		}
	}
	
	/***
	 * Gibt alle Dozenten zurück
	 * @return
	 */
	public ArrayList<Dozent> getAlleDozenten(){
		if(this.alleDozenten == null){
			this.ladeDozenten();
		}
		
		return this.alleDozenten;
	}
	
	/***
	 * Lädt alle Dozenten aus dem persistenten Speicher in den Manager
	 */
	private void ladeDozenten(){
		this.alleDozenten = new ArrayList<Dozent>();
		try{
			
			for(DozentTO d : this.stundenplanDatenzugriff.alleDozenten()){
				this.alleDozenten.add(d.toDozent());
			}
			
		}catch(DatenhaltungsException e){
			e.printStackTrace();
		}
	}
	
	/***
	 * Speichert einen Dozent Objekt inkl seinen Zeitpraeferenzen persistent.
	 * @param dozent
	 * @return true/false
	 * @throws AnwendungskernException
	 */
	public boolean zeitpraeferenzenFuerDozentSpeichern(DozentTO dozent) throws AnwendungskernException{
		try{
			return this.stundenplanDatenzugriff.speichereDozentZeitpraeferenzen(dozent);
		}catch(DatenhaltungsException e){
			e.printStackTrace();
			throw new AnwendungskernException();
		}
	}
	
	/***
	 * Loescht die Zeitpraeferenzen eines Dozenten
	 * @param dozent
	 * @return
	 * @throws AnwendungskernException
	 */
	public boolean zeitpraeferenzenFuerDozentLoeschen(DozentTO dozent) throws AnwendungskernException{
		try{
			return this.stundenplanDatenzugriff.loescheDozentZeitpraeferenzen(dozent);
		}catch(DatenhaltungsException e){
			e.printStackTrace();
			throw new AnwendungskernException();
		}
	}
	
	/***
	 * Liefer zu einer Nummer den Dozenten, wenn dieser mit die Nummer als Dozentnummer in der Datenbank hat
	 * @param nummer
	 * @return DozentTO
	 * @throws AnwendungskernException
	 */
	public DozentTO dozentMitNummer(int nummer) throws AnwendungskernException{
		try{
			return this.stundenplanDatenzugriff.dozentMitNummer(nummer);
		}catch(DatenhaltungsException e){
			e.printStackTrace();
			throw new AnwendungskernException();
		}
	}
	
	/***
	 * Liefert die Dozentnummer des Dozenten aus der Datenbank
	 * @param dozent
	 * @return Dozentnummer
	 * @throws AnwendungskernException
	 */
	public int dozentNummerVonDozent(DozentTO dozent) throws AnwendungskernException{
		try{
			return this.stundenplanDatenzugriff.dozentNummerVonDozent(dozent);
		}catch(DatenhaltungsException e){
			e.printStackTrace();
			throw new AnwendungskernException();
		}
	}
	
	/***
	 * Liefert einen zufälligen Dozenten mit der angegebenen Zeitpraeferenz im Studiengang
	 * @param zeitpref
	 * @param studiengang
	 * @return DozentTO
	 * @throws AnwendungskernException
	 */
	public DozentTO RandomDozentMitZeitpref(int zeitpref,
			StudiengangTO studiengang) throws AnwendungskernException {
		ArrayList<DozentTO> dozenten;
		
		try {
			dozenten = this.stundenplanDatenzugriff.dozentenMitZeitprefUndStudiengang(zeitpref, studiengang);
		} catch (DatenhaltungsException e) {
			e.printStackTrace();
			throw new AnwendungskernException();
		}
		
		if(dozenten.size() == 0){
			return null;
		}else{
			Random randomGenerator = new Random();
			int randomIndex = randomGenerator.nextInt(dozenten.size());
			
			return dozenten.get(randomIndex);
		}	
	}

	/***
	 * Liefer einen zufaelligen Dozenten im Studiengang
	 * @param studiengang
	 * @return DozentTO
	 * @throws AnwendungskernException
	 */
	public DozentTO RandomDozent(StudiengangTO studiengang)
			throws AnwendungskernException {	

		ArrayList<DozentTO> dozenten;
		
		try {
			dozenten = this.stundenplanDatenzugriff.dozentenVonStudiengang(studiengang);
		} catch (DatenhaltungsException e) {
			e.printStackTrace();
			throw new AnwendungskernException();
		}
		
		if(dozenten.size() == 0){
			return null;
		}else{
			Random randomGenerator = new Random();
			int randomIndex = randomGenerator.nextInt(dozenten.size());
			
			return dozenten.get(randomIndex);
		}
	}

	/***
	 * Gibt den Dozenten eines Moduls zurück
	 * @param modulNummer
	 * @return DozentTO
	 * @throws AnwendungskernException
	 */
	public DozentTO dozentVonModulNummer(int modulNummer) throws AnwendungskernException{
		try{
			return this.stundenplanDatenzugriff.dozentVonModulNummer(modulNummer);
		}catch(DatenhaltungsException e){
			e.printStackTrace();
			throw new AnwendungskernException();
		}
	}
}
