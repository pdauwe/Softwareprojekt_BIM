package awk.usecase;

import java.util.ArrayList;

import awk.AnwendungskernException;
import awk.entity.StundenplanTO;
import awk.entity.internal.*;

public interface IStundenplanErstellen {

	public Stundenplan stundenplanGenerieren(Studiengang studiengang, ArrayList<Dozent> dozenten, ArrayList<Modul> module) throws AnwendungskernException;
	public boolean stundenplanSpeichern(StundenplanTO stundenplan) throws AnwendungskernException;

	
	/***
	 * Erstellt den Stundenplan Urplan für jeden Studiengang
	 * @return Stundenplan je Studiengang
	 * @throws AnwendungskernException
	 */
	public boolean erstelleUrplan() throws AnwendungskernException;
	
	
}
