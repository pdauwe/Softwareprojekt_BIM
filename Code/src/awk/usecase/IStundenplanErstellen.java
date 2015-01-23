package awk.usecase;

import awk.AnwendungskernException;

public interface IStundenplanErstellen {
	
	/***
	 * Erstellt den Stundenplan Urplan für jeden Studiengang
	 * @return Stundenplan je Studiengang
	 * @throws AnwendungskernException
	 */
	public boolean erstelleUrplan() throws AnwendungskernException;
	
	
}
