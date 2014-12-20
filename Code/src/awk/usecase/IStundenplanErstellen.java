package awk.usecase;

import java.util.ArrayList;
import java.util.HashMap;

import awk.AnwendungskernException;
import awk.entity.internal.*;

public interface IStundenplanErstellen {

	public Stundenplan stundenplanGenerieren(Studiengang studiengang, ArrayList<Dozent> dozenten, ArrayList<Modul> module) throws AnwendungskernException;
	public boolean stundenplanSpeichern(Stundenplan stundenplan) throws AnwendungskernException;

	
	/***
	 * Erstellt den Stundenplan Urplan für jeden Studiengang
	 * @return Stundenplan je Studiengang
	 * @throws AnwendungskernException
	 */
	public HashMap<Studiengang, Stundenplan> erstelleUrplan() throws AnwendungskernException;
	
	
}
