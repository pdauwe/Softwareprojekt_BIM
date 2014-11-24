package awk.usecase;

import java.util.ArrayList;

import awk.AnwendungskernException;
import awk.entity.*;

public interface IStundenplanErstellen {

	public Stundenplan stundenplanGenerieren(Studiengang studiengang, ArrayList<Dozent> dozenten, ArrayList<Modul> module) throws AnwendungskernException;
	public boolean stundenplanSpeichern(Stundenplan stundenplan) throws AnwendungskernException;
}
