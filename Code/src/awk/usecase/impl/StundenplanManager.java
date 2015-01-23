package awk.usecase.impl;

import java.util.HashMap;

import awk.AnwendungskernException;
import awk.DatenhaltungsException;
import awk.entity.StudiengangTO;
import awk.entity.StundenplanSlotTO;
import awk.entity.StundenplanTO;
import awk.persistence.IStundenplanDatenzugriff;
import awk.persistence.impl.StundenplanDatenzugriff;


/*
 * Managerklasse zur Verwaltung von Stundenplänen
 */
public class StundenplanManager{
	
	/**
	 * 
	 */
	private IStundenplanDatenzugriff stundenplanDatenzugriff = new StundenplanDatenzugriff();
	private static StundenplanManager self;
	/***
	 * Der Urplan beinhaltet einen vollstaendigen Stundenplan fuer jeden Studiengang
	 */
	private HashMap<StudiengangTO,StundenplanTO> urplan;
	
	public static StundenplanManager getManager(){
		if(self == null){
			return new StundenplanManager();
		}else{
			return self;
		}
	}
	
	private StundenplanManager(){
		this.urplan = new HashMap<StudiengangTO, StundenplanTO>();
	}
	
<<<<<<< Updated upstream
=======
	public void createUrplan() throws AnwendungskernException{
		IStundenplanFactory sf = new StundenplanFactory();
		IStundenplanErstellen se = sf.getStundenplanErstellen();
		try {
			se.erstelleUrplan();
		} catch (AnwendungskernException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean deleteUrplan() throws AnwendungskernException{
		try {
			return this.stundenplanDatenzugriff.deleteUrplan();
		} catch (DatenhaltungsException e) {
			e.printStackTrace();
			throw new AnwendungskernException();
		}
	}

>>>>>>> Stashed changes
	public StundenplanTO getStundenplan(StudiengangTO studiengang) throws AnwendungskernException{
		try {
			return this.stundenplanDatenzugriff.ladeStundenplanFuerStudiengang(studiengang);
		} catch (DatenhaltungsException e) {
			e.printStackTrace();
			throw new AnwendungskernException();
		}
	}
	
	public boolean stundenplanSpeichern(StundenplanTO stundenplan) throws AnwendungskernException{
		try {
			return this.stundenplanDatenzugriff.speichereStundenplan(stundenplan);
		} catch (DatenhaltungsException e) {
			e.printStackTrace();
			throw new AnwendungskernException();
		}
	}
	
	/***
	 * Fuegt einen StundenplanSlot zu dem Stundenplan für einen Studiengang zum Urplan hinzu.
	 * @param studiengang
	 * @param stundenplan
	 * @return true/false
	 * @throws AnwendungskernException
	 */
	public boolean addToUrplan(StudiengangTO studiengang, StundenplanSlotTO stundenplanslot, int zeitslot) throws AnwendungskernException {
		
		// Überprüfen ob der Dozent zu dem Zeitslot schon belegt ist.
		// Wenn ja, dann nicht hinzufuegen und abbrechen.
		
		for(StundenplanTO stundenplan : this.urplan.values()){
			
			StundenplanSlotTO slot = stundenplan.getZuordnung().get(zeitslot);
			
			if(slot != null){
				if(slot.getDozent().equals(stundenplanslot.getDozent())){
					return false;
				}
			}
			
			for(StundenplanSlotTO s : stundenplan.getZuordnung().values()){
				if(s.getModul().equals(stundenplanslot.getModul())){
					return false;
				}
			}
			
			
		}

		if(!this.urplan.containsKey(studiengang)){
			StundenplanTO stundenplan = new StundenplanTO();
			stundenplan.setStudiengang(studiengang);
			this.urplan.put(studiengang, stundenplan);
		}
		
		StundenplanTO stundenplan = this.urplan.get(studiengang);
		return stundenplan.addZuordnung(zeitslot, stundenplanslot);
		
	}
	
	/***
	 * Gibt an, ob der Urplan vollstaendig erstellt wurde.
	 * Dies ist der Fall, wenn alle Module jedes Studiengangs verplant wurden bzw. alle Zeitslots gefuellt wurden
	 * @return true/false
	 */
	public boolean isUrplanComplete(){
		
		for(StundenplanTO s : this.urplan.values()){
			
			if((s.getZuordnung().containsKey(14)) == false){
				return false;
			}
		}
		return true;
		
	}
	
	/***
	 * Gibt an, ob ein Urplan vorhanden ist
	 * @return
	 * @throws AnwendungskernException
	 */
	public boolean doesUrplanExists() throws AnwendungskernException{
		try {
			return this.stundenplanDatenzugriff.doesUrplanExists();
		} catch (DatenhaltungsException e) {
			e.printStackTrace();
			throw new AnwendungskernException();
		}
	}
	
	public HashMap<StudiengangTO, StundenplanTO> getUrplan(){
		return this.urplan;
	}
}
