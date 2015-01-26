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
				
				RaumTO randomRaum = RaumManager.getManager().raumFuerModul(randomModul);
				
				if(randomRaum == null){
					continue;
				}
				
				StundenplanSlotTO slot = new StundenplanSlotTO();
				slot.setDozent(randomDozent);
				slot.setModul(randomModul);
				slot.setRaum(randomRaum);
				

				boolean ok = stundenplanManager.addToUrplan(s, slot, zeitslot);
				
				if(!ok){
					continue;
				}
			}
		}

		while(!stundenplanManager.isUrplanComplete()){
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
					
					RaumTO randomRaum = RaumManager.getManager().raumFuerModul(randomModul);
					
					if(randomRaum == null){
						continue;
					}
					
					StundenplanSlotTO slot = new StundenplanSlotTO();
					slot.setDozent(randomDozent);
					slot.setModul(randomModul);
					slot.setRaum(randomRaum);
	
					boolean ok = stundenplanManager.addToUrplan(s, slot, zeitslot);
					
					if(!ok){
						continue;
					}
					
					
				}
			}
		}

		for(StundenplanTO s : stundenplanManager.getUrplan().values()){
			this.stundenplanSpeichern(s);
		}
		
		return true;
	}
}
