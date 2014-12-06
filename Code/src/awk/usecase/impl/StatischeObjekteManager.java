package awk.usecase.impl;

import java.util.ArrayList;

import awk.entity.internal.Dozent;
import awk.entity.internal.Modul;
import awk.entity.internal.Raum;
import awk.entity.internal.Semester;
import awk.entity.internal.Studiengang;


/*
 * Managerklasse die alle statischen Objekte zurück gibt.
 * Statische Objekte sind all die Objekte, die nicht vom Benutzer geändert werden können.  
 */
public class StatischeObjekteManager {

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
