package awk.usecase.impl;

import awk.AnwendungskernException;
import awk.entity.internal.Dozent;
import awk.entity.internal.Modul;
import awk.entity.internal.Studiengang;
import awk.entity.internal.Stundenplan;


/*
 * Managerklasse zur Verwaltung von Stundenplänen
 */
public class StundenplanManager {
	
	private static StundenplanManager self;
	private Stundenplan urplan;
	
	public static StundenplanManager getManager(){
		if(self == null){
			return new StundenplanManager();
		}else{
			return self;
		}
	}
	

	public Stundenplan getStundenplan(Studiengang studiengang){
		//TODO: Implementierung
		return null;
	}
	
	public boolean stundenplanSpeichern(Stundenplan stundenplan){
		//TODO: Implementierung
		return false;
	}
	
	public boolean addToUrplan(int zeitslot, Studiengang studiengang,
			Modul modul, Dozent dozent) throws AnwendungskernException {
		// TODO Auto-generated method stub
		
		
		
		
		return false;
	}
	
	public boolean isUrplanComplete(){
		return false;
	}
	
	public Stundenplan getUrplan(){
		return this.urplan;
	}
}
