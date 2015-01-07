package awk.usecase.impl;

import java.util.ArrayList;

import awk.AnwendungskernException;
import awk.entity.DozentTO;
import awk.entity.ModulTO;
import awk.entity.StudiengangTO;
import awk.entity.StundenplanSlotTO;
import awk.entity.StundenplanTO;
import awk.entity.internal.Dozent;
import awk.entity.internal.Modul;
import awk.entity.internal.Studiengang;
import awk.entity.internal.Stundenplan;
import awk.usecase.IStundenplanErstellen;

public class StundenplanErstellen implements IStundenplanErstellen {

	@Override
	public Stundenplan stundenplanGenerieren(Studiengang studiengang,
			ArrayList<Dozent> dozenten, ArrayList<Modul> module)
			throws AnwendungskernException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean stundenplanSpeichern(StundenplanTO stundenplan) throws AnwendungskernException {
		return StundenplanManager.getManager().stundenplanSpeichern(stundenplan);
	}


	@Override
	public boolean erstelleUrplan() throws AnwendungskernException {
		
		//TODO: evtl Optimierung der beiden Schleifen, in beiden passiert quasi das gleiche. Doppelter Code.

		StundenplanManager stundenplanManager = StundenplanManager.getManager();

//		// Erste Iteration fuer das Zuordnen mit Zeitpraeferenzen
//		for(int zeitslot = 0; zeitslot<15; zeitslot++){
//			
//			ArrayList<StudiengangTO> stList = StudiengangManager.getManager().studiengangZufallsliste();
//			
//			
//			for(StudiengangTO s :stList){
//	
//				DozentTO randomDozent = DozentManager.getManager().RandomDozentMitZeitpref(zeitslot, s);
//				
//				if(randomDozent == null){
//					continue;
//				}
//				
//				ModulTO randomModul = ModulManager.getManager().randomModulVonDozentImStudiengang(randomDozent, s);
//				
//				if(randomModul == null){
//					continue;
//				}
//				
//				StundenplanSlotTO slot = new StundenplanSlotTO();
//				slot.setDozent(randomDozent);
//				slot.setModul(randomModul);
//
//				boolean ok = stundenplanManager.addToUrplan(s, slot, zeitslot);
//				
//				if(!ok){
//					continue;
//				}
//			}
//		}
//		
//		// Fuer den Fall, dass der Plan bereits nach der ersten Iteration komplett ist.
//		// Dies sollte jedoch unwahrscheinlich sein.
//		if(stundenplanManager.isUrplanComplete()){
//			return stundenplanManager.getUrplan();
//		}
		
		// Zweite Iteration fuer das Zuordnen ohne Zeitpraeferenzen, um den Urplan aufzufuellen.
		for(int zeitslot = 0; zeitslot<15; zeitslot++){
			
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
		
		System.out.println("values: " + stundenplanManager.getUrplan().values());
		
		for(StundenplanTO s : stundenplanManager.getUrplan().values()){
			stundenplanManager.stundenplanSpeichern(s);
		}
		return true;
		
	}

}
