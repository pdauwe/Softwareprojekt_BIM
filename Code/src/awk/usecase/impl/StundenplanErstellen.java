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
		
		StundenplanManager stundenplanManager = StundenplanManager.getManager();

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
		
		if(stundenplanManager.isUrplanComplete()){
			return stundenplanManager.getUrplan();
		}
		
		for(int zeitslot = 0; zeitslot<15; zeitslot++){
			
			ArrayList<StudiengangTO> stList = StudiengangManager.getManager().studiengangZufallsliste();
			
			for(StudiengangTO s :stList){
				DozentTO randomDozent = DozentManager.getManager().RandomDozent(s);
				
				if(randomDozent == null){
					break;
				}
				
				ModulTO randomModul = ModulManager.getManager().randomModulVonDozentImStudiengang(randomDozent, s);
				
				if(randomModul == null){
					break;
				}
				
				StundenplanSlotTO slot = new StundenplanSlotTO();
				slot.setDozent(randomDozent);
				slot.setModul(randomModul);
				
				boolean ok = stundenplanManager.addToUrplan(s, slot, zeitslot);
				
				if(!ok){
					break;
				}
				
			}
		}
		
		return stundenplanManager.getUrplan();
		
	}

}
