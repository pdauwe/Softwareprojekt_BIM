package awk.usecase;

import awk.AnwendungskernException;
import awk.entity.StundenplanTO;

public interface IStundenplanErstellen {

	/***
	 * Speichert einen Stundenplan persistent
	 * @param stundenplan
	 * @return
	 * @throws AnwendungskernException
	 */
	public boolean stundenplanSpeichern(StundenplanTO stundenplan) throws AnwendungskernException;

	
	/***
	 * Erstellt den Stundenplan Urplan für jeden Studiengang
	 * @return Stundenplan je Studiengang
	 * @throws AnwendungskernException
	 */
	public boolean erstelleUrplan() throws AnwendungskernException;
	
	
}
