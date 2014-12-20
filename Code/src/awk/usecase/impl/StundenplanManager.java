package awk.usecase.impl;

import java.util.HashMap;

import awk.AnwendungskernException;
import awk.entity.StudiengangTO;
import awk.entity.StundenplanSlotTO;
import awk.entity.internal.Studiengang;
import awk.entity.internal.Stundenplan;


/*
 * Managerklasse zur Verwaltung von Stundenplänen
 */
public class StundenplanManager {
	
	private static StundenplanManager self;
	/***
	 * Der Urplan beinhaltet einen vollstaendigen Stundenplan fuer jeden Studiengang
	 */
	private HashMap<Studiengang,Stundenplan> urplan;
	
	public static StundenplanManager getManager(){
		if(self == null){
			return new StundenplanManager();
		}else{
			return self;
		}
	}
	
	private StundenplanManager(){
		this.urplan = new HashMap<Studiengang, Stundenplan>();
	}
	

	public Stundenplan getStundenplan(Studiengang studiengang){
		//TODO: Implementierung
		return null;
	}
	
	public boolean stundenplanSpeichern(Stundenplan stundenplan){
		//TODO: Implementierung
		return false;
	}
	
	/***
	 * Fuegt einen StundenplanSlot zu dem Stundenplan für einen Studiengang zum Urplan hinzu.
	 * @param studiengang
	 * @param stundenplan
	 * @return true/false
	 * @throws AnwendungskernException
	 */
	public boolean addToUrplan(StudiengangTO studiengang, StundenplanSlotTO stundenplanslot, int zeitslot) throws AnwendungskernException {
		
		// Überprüfen ob der Dozent zu dem Zeitslot schon belegt ist
		for(Stundenplan s : this.urplan.values()){
			if(s.getZuordnung().get(zeitslot).getDozent().equals(stundenplanslot.getDozent().toDozent())){
				return false;
			}
		}
		
		Stundenplan stundenplan = this.urplan.get(studiengang);
		
		if(stundenplan == null){
			Stundenplan stundenplanFuerStudiengang = new Stundenplan(studiengang.toStudiengang());
			stundenplan = stundenplanFuerStudiengang;
		}
		
		stundenplan.addZuordnung(zeitslot, stundenplanslot.toStundenplanSlot());

		this.urplan.put(studiengang.toStudiengang(), stundenplan);
		
		return true;
	}
	
	/***
	 * Gibt an, ob der Urplan vollstaendig erstellt wurde.
	 * Dies ist der Fall, wenn alle Module jedes Studiengangs verplant wurden bzw. alle Zeitslots gefuellt wurden
	 * @return true/false
	 */
	public boolean isUrplanComplete(){
		
		for(Stundenplan s : this.urplan.values()){
			if(!(s.getZuordnung().containsKey(14))){
				return false;
			}
		}
		return true;
		
	}
	
	public HashMap<Studiengang, Stundenplan> getUrplan(){
		return this.urplan;
	}
}
