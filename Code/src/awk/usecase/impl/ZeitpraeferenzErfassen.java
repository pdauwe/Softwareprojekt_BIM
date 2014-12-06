package awk.usecase.impl;

import java.util.ArrayList;

import awk.AnwendungskernException;
import awk.entity.DozentTO;
import awk.entity.internal.*;
import awk.usecase.IZeitpraeferenzErfassen;

public class ZeitpraeferenzErfassen implements IZeitpraeferenzErfassen {

	@Override
	public ArrayList<Dozent> dozentenlisteAnzeigen()
			throws AnwendungskernException {
		DozentManager dozentManager = DozentManager.getManager();
		return dozentManager.getAlleDozenten();
	}

	@Override
	public ArrayList<Zeit> zeitpraeferenzbloeckeAnzeigen()
			throws AnwendungskernException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean zeitpraeferenzenSpeichern(Dozent dozent) throws AnwendungskernException {
		DozentManager dozentManager = DozentManager.getManager();
		DozentTO d = new DozentTO();
		d.setName(dozent.getName());
		d.setZeiten(dozent.getZeiten());
		return dozentManager.zeitpraeferenzenFuerDozentSpeichern(d);
	}

}
