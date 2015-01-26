package awk.usecase.impl;

import java.util.HashMap;

import awk.AnwendungskernException;
import awk.DatenhaltungsException;
import awk.entity.StudiengangTO;
import awk.entity.StundenplanSlotTO;
import awk.entity.StundenplanTO;
import awk.persistence.IStundenplanDatenzugriff;
import awk.persistence.impl.StundenplanDatenzugriff;


/***
 *  Managerklasse zur Verwaltung von Stundenplaenen
 */
public class StundenplanManager{
	
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
	
	/***
	 * Loescht einen vorhandenen Urplan
	 * @return true
	 * @throws AnwendungskernException
	 */
	public boolean deleteUrplan() throws AnwendungskernException{
		try {
			return this.stundenplanDatenzugriff.deleteUrplan();
		} catch (DatenhaltungsException e) {
			e.printStackTrace();
			throw new AnwendungskernException();
		}
	}
	
	/***
	 * Gibt einen Stundenplan fuer einen Studiengang zurueck
	 * @param studiengang
	 * @return StundenplanTO
	 * @throws AnwendungskernException
	 */
	public StundenplanTO getStundenplan(StudiengangTO studiengang) throws AnwendungskernException{
		try {
			return this.stundenplanDatenzugriff.ladeStundenplanFuerStudiengang(studiengang);
		} catch (DatenhaltungsException e) {
			e.printStackTrace();
			throw new AnwendungskernException();
		}
	}
	
	/***
	 * Speichert einen Stundenplan persistent
	 * @param stundenplan
	 * @return true
	 * @throws AnwendungskernException
	 */
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

		for(StundenplanTO stundenplan : this.urplan.values()){
			
			StundenplanSlotTO slot = stundenplan.getZuordnung().get(zeitslot);
			
			if(slot != null){
				// Ueberpruefen ob der Dozent zu dem Zeitslot schon belegt ist.
				// Wenn ja, dann nicht hinzufuegen und abbrechen.
				if(slot.getDozent().equals(stundenplanslot.getDozent())){
					return false;
				}
				
				// Ueberpruefen ob der Raum zu dem Zeitslot schon belegt ist.
				// Wenn ja, dann nicht hinzufuegen und abbrechen.
				if(slot.getRaum().equals(stundenplanslot.getRaum())){
					return false;
				}
			}
			
			// Ueberpruefen ob das Modul schon verplant wurde.
			// Wenn ja, dann nicht hinzufuegen und abbrechen.
			for(StundenplanSlotTO s : stundenplan.getZuordnung().values()){
				if(s.getModul().equals(stundenplanslot.getModul())){
					return false;
				}
			}
			
			
		}

		// Ueberpruefen ob ein Stundenplan fuer den Studiengang existiert.
		// Falls nein, wird ein neuer leerer Stundenplan erstellt und dem Urplan hinzugefuegt.
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
		
		boolean alleStudiengaengeImUrplan = false;
		
		try {
			int anzahlStudiengaenge = StudiengangManager.getManager().anzahlStudiengaenge();
			if(this.urplan.keySet().size() == anzahlStudiengaenge){
				alleStudiengaengeImUrplan = true;
			}
		} catch (AnwendungskernException e) {
			e.printStackTrace();
		}

		if(alleStudiengaengeImUrplan){
			for(StudiengangTO studiengang : this.urplan.keySet()){
				try {
					int anzahlModule = StudiengangManager.getManager().maxAnzahlAnModulenFuerStudiengang(studiengang);
					
					if(this.urplan.get(studiengang).getZuordnung().values().size() != anzahlModule){
						return false;
					}
				} catch (AnwendungskernException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		}else{
			return false;
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
