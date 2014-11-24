package awk.usecase.impl;

import java.util.ArrayList;

import awk.AnwendungskernException;
import awk.entity.internal.Dozent;
import awk.entity.internal.Modul;
import awk.entity.internal.Studiengang;
import awk.entity.internal.Stundenplan;
import awk.usecase.IStundenplanErstellen;

public class StundenplanErstellen implements IStundenplanErstellen {

	@Override
	public Stundenplan stundenplanGenerieren(Studiengang studiengang,
			ArrayList<Dozent> dozenten, ArrayList<Modul> module)
			throws AnwendungskernException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean stundenplanSpeichern(Stundenplan stundenplan) throws AnwendungskernException {
		// TODO Auto-generated method stub
		return false;
	}

}
