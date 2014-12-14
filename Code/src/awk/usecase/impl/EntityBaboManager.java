package awk.usecase.impl;

import java.util.ArrayList;

import awk.entity.internal.Dozent;
import awk.entity.internal.Modul;
import awk.entity.internal.Raum;
import awk.entity.internal.Semester;
import awk.entity.internal.Studiengang;


/**
 * Dies ist der Entity Babo.
 * Der Babo kuemmert sich darum, alle Knecht-Entities die der Benutzer nicht aendern kann zurückzugeben.
 * Fragt den Babo welche Objekte ihr benoetigt und der Babo wird liefern!
 */

public class EntityBaboManager {
	
	private static EntityBaboManager self;
	
	public static EntityBaboManager getManager(){
		if(self == null){
			return new EntityBaboManager();
		}else{
			return self;
		}
	}
	
	public ArrayList<Dozent> getDozenten(){
		return DozentManager.getManager().getAlleDozenten();
	}
	
	public ArrayList<Modul> getModule(){
		//TODO: Implementierung
		return null;
	}
	
	public ArrayList<Raum> getRaeume(){
		return RaumManager.getManager().getAlleRaeume();
	}
	
	public ArrayList<Semester> getSemester(){
		//TODO: Implementierung
		return null;
	}
	
	public ArrayList<Studiengang> getStudiengaenge(){
		return StudiengangManager.getManager().getAlleStudiengaenge();
	}
	
	
	
	
}
