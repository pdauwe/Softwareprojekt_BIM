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
public class StundenplanManager {
	
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
	

	public StundenplanTO getStundenplan(StudiengangTO studiengang){
		return this.urplan.get(studiengang);
	}
	
	public boolean stundenplanSpeichern(StundenplanTO stundenplan) throws AnwendungskernException{
		try {
			return this.stundenplanDatenzugriff.speichereStundenplan(stundenplan);
		} catch (DatenhaltungsException e) {
			// TODO Auto-generated catch block
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
				if(slot.getDozent().equals(stundenplanslot.getDozent().toDozent())){
					return false;
				}
			}
			
			for(StundenplanSlotTO s : stundenplan.getZuordnung().values()){
				if(s.getModul().equals(stundenplanslot.getModul().toModul())){
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
		boolean ok = stundenplan.addZuordnung(zeitslot, stundenplanslot);
		if(!ok){
			return false;
		}

		return true;
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
	
	public HashMap<StudiengangTO, StundenplanTO> getUrplan(){
		return this.urplan;
	}
}
