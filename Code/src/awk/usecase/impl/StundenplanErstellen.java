package awk.usecase.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import awk.AnwendungskernException;
import awk.entity.StudiengangTO;
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
	public ArrayList<Studiengang> generiereStudiengangZufallsliste()
			throws AnwendungskernException {
		ArrayList<Studiengang> alleStudiengaenge = StudiengangManager.getManager().getAlleStudiengaenge();
		Collections.shuffle(alleStudiengaenge);
		return alleStudiengaenge;
	}

	@Override
	public Dozent randomDozentMitZeitpref(int zeitpref,
			Studiengang studiengang) throws AnwendungskernException {
		
		ArrayList<Dozent> alleDozenten = DozentManager.getManager().getAlleDozenten();
		Random randomGenerator = new Random();
		int randomIndex = randomGenerator.nextInt(alleDozenten.size());
		
		return alleDozenten.get(randomIndex);
	}

	@Override
	public Dozent randomDozent(Studiengang studiengang)
			throws AnwendungskernException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Modul randomModul(Dozent dozent, Studiengang studiengang) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stundenplan erstelleUrplan() throws AnwendungskernException {
		
		StundenplanManager stundenplanManager = StundenplanManager.getManager();
		
		ArrayList<Dozent> alleDozenten = DozentManager.getManager().getAlleDozenten();
		ArrayList<Modul> alleModule = ModulManager.getManager().getAlleModule();
		
		
		
		for(int zeitslot = 0; zeitslot<15; zeitslot++){
			
			ArrayList<Studiengang> stList = this.generiereStudiengangZufallsliste();
			
			for(Studiengang s :stList){
				Dozent randomDozent = this.randomDozentMitZeitpref(zeitslot, s);
				
				if(randomDozent == null){
					break;
				}
				
				Modul randomModul = this.randomModul(randomDozent, s);
				
				stundenplanManager.addToUrplan(zeitslot, s, randomModul, randomDozent);
				
				randomModul.setVerplant(true);
				randomDozent.removeZeit(zeitslot);
				
				alleDozenten.set(alleDozenten.indexOf(randomDozent), randomDozent);
				alleModule.set(alleModule.indexOf(randomModul), randomModul);
			}
		}
		
		if(stundenplanManager.isUrplanComplete()){
			return stundenplanManager.getUrplan();
		}
		
		for(int zeitslot = 0; zeitslot<15; zeitslot++){
			
			ArrayList<Studiengang> stList = this.generiereStudiengangZufallsliste();
			
			for(Studiengang s :stList){
				Dozent randomDozent = this.randomDozent(s);
				
				if(randomDozent == null){
					break;
				}
				
				Modul randomModul = this.randomModul(randomDozent, s);
				
				stundenplanManager.addToUrplan(zeitslot, s, randomModul, randomDozent);
				
				randomModul.setVerplant(true);
				randomDozent.removeZeit(zeitslot);
				
				alleDozenten.set(alleDozenten.indexOf(randomDozent), randomDozent);
				alleModule.set(alleModule.indexOf(randomModul), randomModul);
			}
		}
		
		return stundenplanManager.getUrplan();
		
	}

}
