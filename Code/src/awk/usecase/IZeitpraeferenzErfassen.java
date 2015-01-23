package awk.usecase;

import awk.AnwendungskernException;
import awk.entity.DozentTO;

public interface IZeitpraeferenzErfassen {

	public boolean zeitpraeferenzenSpeichern(DozentTO dozent) throws AnwendungskernException;
	
}
