package awk.usecase.impl;
import java.util.ArrayList;

import awk.AnwendungskernException;
import awk.entity.DozentTO;
import awk.entity.ModulTO;
import awk.entity.RaumTO;
import awk.entity.StudiengangTO;
import awk.entity.StundenplanSlotTO;
import awk.entity.StundenplanTO;
import awk.usecase.IStundenplanErstellen;

public class StundenplanErstellen implements IStundenplanErstellen {
	
	/***
	 * Speichert den Stundenplan
	 * @param stundenplan
	 * @return true
	 * @throws AnwendungskernException
	 */
	private boolean stundenplanSpeichern(StundenplanTO stundenplan) throws AnwendungskernException {
		return StundenplanManager.getManager().stundenplanSpeichern(stundenplan);
	}
	
	/***
	 * Ordnet jedem StundenplanSlot einen Raum zu
	 * @throws AnwendungskernException
	 */
	private void ordneRaeumeZu() throws AnwendungskernException{
		
		for(int i = 1; i<16; i++){
			ArrayList<RaumTO> belegteRaeume = new ArrayList<RaumTO>();
			// HIER IST DER FEHLER
			for(StundenplanTO stundenplan : StundenplanManager.getManager().getUrplan().values()){
				StundenplanSlotTO slot = stundenplan.getZuordnung().get(Integer.valueOf(i));
				RaumTO raum = null;
				boolean foundRoom = false;
				while(foundRoom == false){
					
					raum = RaumManager.getManager().raumFuerModul(slot.getModul());
					
					if(belegteRaeume.contains(raum) == false){
						foundRoom = true;
					}
				}
				slot.setRaum(raum);
				belegteRaeume.add(raum);
			}
		}
	}


	@Override
	public boolean erstelleUrplan() throws AnwendungskernException {
		
		//TODO: evtl Optimierung der beiden Schleifen, in beiden passiert quasi das gleiche. Doppelter Code.

		StundenplanManager stundenplanManager = StundenplanManager.getManager();
		// Erste Iteration fuer das Zuordnen mit Zeitpraeferenzen
		for(int zeitslot = 1; zeitslot<16; zeitslot++){
			
			ArrayList<StudiengangTO> stList = StudiengangManager.getManager().studiengangZufallsliste();
			
			
			for(StudiengangTO s :stList){
	
				DozentTO randomDozent = DozentManager.getManager().RandomDozentMitZeitpref(zeitslot, s);
				
				if(randomDozent == null){
					continue;
				}
				
				ModulTO randomModul = ModulManager.getManager().randomModulVonDozentImStudiengang(randomDozent, s);
				
				if(randomModul == null){
					continue;
				}
				
				StundenplanSlotTO slot = new StundenplanSlotTO();
				slot.setDozent(randomDozent);
				slot.setModul(randomModul);

				boolean ok = stundenplanManager.addToUrplan(s, slot, zeitslot);
				
				if(!ok){
					continue;
				}
			}
		}

		
		// Zweite Iteration fuer das Zuordnen ohne Zeitpraeferenzen, um den Urplan aufzufuellen.
		for(int zeitslot = 1; zeitslot<16; zeitslot++){
			
			ArrayList<StudiengangTO> stList = StudiengangManager.getManager().studiengangZufallsliste();
			
			for(StudiengangTO s :stList){
				DozentTO randomDozent = DozentManager.getManager().RandomDozent(s);
				
				if(randomDozent == null){
					continue;
				}
				
				ModulTO randomModul = ModulManager.getManager().randomModulVonDozentImStudiengang(randomDozent, s);
				
				if(randomModul == null){
					continue;
				}
				
				StundenplanSlotTO slot = new StundenplanSlotTO();
				slot.setDozent(randomDozent);
				slot.setModul(randomModul);

				boolean ok = stundenplanManager.addToUrplan(s, slot, zeitslot);
				
				if(!ok){
					continue;
				}
				
				
			}
		}
		
		this.ordneRaeumeZu();

		for(StundenplanTO s : stundenplanManager.getUrplan().values()){
			this.stundenplanSpeichern(s);
		}
		
		return true;
	}
}
