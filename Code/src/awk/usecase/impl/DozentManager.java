package awk.usecase.impl;

import java.util.ArrayList;
import java.util.Random;

import awk.AnwendungskernException;
import awk.DatenhaltungsException;
import awk.entity.DozentTO;
import awk.entity.StudiengangTO;
import awk.entity.internal.Dozent;
import awk.entity.internal.Studiengang;
import awk.persistence.IStundenplanDatenzugriff;
import awk.persistence.impl.StundenplanDatenzugriff;
/***
 * Singleton Klasse DozentManager
 * Kümmert sich um alle Dozent Objekte
 * 
 *
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
	public boolean ZeitpraeferenzenFuerDozentSpeichern(DozentTO dozent) throws AnwendungskernException{
		try{
			return this.stundenplanDatenzugriff.speichereDozentZeitpraeferenzen(dozent);
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
	 * @return Dozent
	 * @throws AnwendungskernException
	 */
	public Dozent RandomDozentMitZeitpref(int zeitpref,
			StudiengangTO studiengang) throws AnwendungskernException {
		ArrayList<DozentTO> alleDozenten;
		
		try {
			alleDozenten = this.stundenplanDatenzugriff.dozentenMitZeitprefUndStudiengang(zeitpref, studiengang);
		} catch (DatenhaltungsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AnwendungskernException();
		}
		Random randomGenerator = new Random();
		int randomIndex = randomGenerator.nextInt(alleDozenten.size());
		
		return alleDozenten.get(randomIndex).toDozent();
		
	}

	/***
	 * Liefer einen zufaelligen Dozenten im Studiengang
	 * @param studiengang
	 * @return Dozent
	 * @throws AnwendungskernException
	 */
	public Dozent RandomDozent(Studiengang studiengang)
			throws AnwendungskernException {
		
		ArrayList<Dozent> alleDozenten = DozentManager.getManager().getAlleDozenten();
		Random randomGenerator = new Random();
		int randomIndex = randomGenerator.nextInt(alleDozenten.size());
		
		return alleDozenten.get(randomIndex);
	}
	
	public ArrayList<Dozent> alleDozentenVonStudiengang(Studiengang studiengang){
		return null;
	}
}
