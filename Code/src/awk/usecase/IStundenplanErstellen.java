package awk.usecase;

import java.util.ArrayList;

import awk.AnwendungskernException;
import awk.entity.internal.*;

public interface IStundenplanErstellen {

	public Stundenplan stundenplanGenerieren(Studiengang studiengang, ArrayList<Dozent> dozenten, ArrayList<Modul> module) throws AnwendungskernException;
	public boolean stundenplanSpeichern(Stundenplan stundenplan) throws AnwendungskernException;
	
	public ArrayList<Studiengang> generiereStudiengangZufallsliste() throws AnwendungskernException;
	public Dozent randomDozentMitZeitpref(int zeitpref, Studiengang studiengang) throws AnwendungskernException;
	public Dozent randomDozent(Studiengang studiengang) throws AnwendungskernException;
	
	public Modul randomModul(Dozent dozent, Studiengang studiengang);
	
	public Stundenplan erstelleUrplan() throws AnwendungskernException;
	
	
}
