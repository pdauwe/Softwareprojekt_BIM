package awk.usecase;

import java.util.ArrayList;

import awk.AnwendungskernException;
import awk.entity.*;

public interface IZeitpraeferenzErfassen {

	public ArrayList<Dozent> dozentenlisteAnzeigen() throws AnwendungskernException;
	public ArrayList<Zeit> zeitpraeferenzbloeckeAnzeigen() throws AnwendungskernException;
	public boolean zeitpraeferenzenSpeichern() throws AnwendungskernException;
	
}
