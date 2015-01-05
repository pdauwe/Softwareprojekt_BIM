package awk.usecase.impl;

import java.util.HashMap;

import awk.AnwendungskernException;
import awk.DatenhaltungsException;
import awk.entity.StudiengangTO;
import awk.entity.StundenplanSlotTO;
import awk.entity.StundenplanTO;
import awk.entity.internal.Studiengang;
import awk.entity.internal.Stundenplan;
import awk.entity.internal.StundenplanSlot;
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
		return urplan.get(studiengang);
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
		
		Studiengang sgang = studiengang.toStudiengang();
		
		// Überprüfen ob der Dozent zu dem Zeitslot schon belegt ist.
		// Wenn ja, dann nicht hinzufuegen und abbrechen.
		for(Stundenplan stundenplan : this.urplan.values()){
			StundenplanSlot slot = stundenplan.getZuordnung().get(zeitslot);
			if(slot != null){
				if(slot.getDozent().equals(stundenplanslot.getDozent().toDozent())){
					return false;
				}
			}
			
			for(StundenplanSlot s : stundenplan.getZuordnung().values()){
				if(s.getModul().equals(stundenplanslot.getModul().toModul())){
					return false;
				}
			}
			
			
		}

		if(!this.urplan.containsKey(sgang)){
			Stundenplan stundenplan = new Stundenplan(sgang);
			this.urplan.put(sgang, stundenplan);
		}
		
		Stundenplan stundenplan = this.urplan.get(sgang);
		boolean ok = stundenplan.addZuordnung(zeitslot, stundenplanslot.toStundenplanSlot());
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
		
		for(Stundenplan s : this.urplan.values()){
			
			if((s.getZuordnung().containsKey(14)) == false){
				return false;
			}
		}
		return true;
		
	}
	
	public HashMap<Studiengang, Stundenplan> getUrplan(){
		return this.urplan;
	}
}
