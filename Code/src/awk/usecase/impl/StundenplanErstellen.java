package awk.usecase.impl;

import java.util.ArrayList;
import java.util.HashMap;


import awk.AnwendungskernException;
import awk.entity.DozentTO;
import awk.entity.ModulTO;
import awk.entity.StudiengangTO;
import awk.entity.StundenplanSlotTO;
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
	public boolean stundenplanSpeichern(Stundenplan stundenplan) throws AnwendungskernException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public HashMap<Studiengang,Stundenplan> erstelleUrplan() throws AnwendungskernException {
		
		//TODO: evtl Optimierung der beiden Schleifen, in beiden passiert quasi das gleiche. Doppelter Code.

		StundenplanManager stundenplanManager = StundenplanManager.getManager();

		// Erste Iteration fuer das Zuordnen mit Zeitpraeferenzen
		for(int zeitslot = 0; zeitslot<15; zeitslot++){
			
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
		
		// Fuer den Fall, dass der Plan bereits nach der ersten Iteration komplett ist.
		// Dies sollte jedoch unwahrscheinlich sein.
		if(stundenplanManager.isUrplanComplete()){
			return stundenplanManager.getUrplan();
		}
		
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

		return stundenplanManager.getUrplan();
		
	}

}
