package awk.usecase.impl;

import awk.AnwendungskernException;
import awk.entity.DozentTO;
import awk.usecase.IZeitpraeferenzErfassen;

public class ZeitpraeferenzErfassen implements IZeitpraeferenzErfassen {


	@Override
	public boolean zeitpraeferenzenSpeichern(DozentTO dozent) throws AnwendungskernException {
		DozentManager dozentManager = DozentManager.getManager();
		return dozentManager.zeitpraeferenzenFuerDozentSpeichern(dozent);
	}

	@Override
	public boolean zeitpraeferenzenLoeschen(DozentTO dozent) throws AnwendungskernException {
		DozentManager dozentManager = DozentManager.getManager();
		return dozentManager.zeitpraeferenzenFuerDozentLoeschen(dozent);
	}

}
