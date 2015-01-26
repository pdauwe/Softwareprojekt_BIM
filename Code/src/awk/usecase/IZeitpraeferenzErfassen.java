package awk.usecase;

import awk.AnwendungskernException;
import awk.entity.DozentTO;

public interface IZeitpraeferenzErfassen {

	/***
	 * Speichert die Zeitpraeferenzen eines Dozenten persistent
	 * @param dozent
	 * @return true
	 * @throws AnwendungskernException
	 */
	public boolean zeitpraeferenzenSpeichern(DozentTO dozent) throws AnwendungskernException;
	
	/***
	 * Loescht die Zeitpraeferenzen eines Dozenten
	 * @param dozent
	 * @return true
	 * @throws AnwendungskernException
	 */
	public boolean zeitpraeferenzenLoeschen(DozentTO dozent) throws AnwendungskernException;
}
