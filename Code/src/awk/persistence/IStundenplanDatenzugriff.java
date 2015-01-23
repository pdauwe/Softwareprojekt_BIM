package awk.persistence;

import java.util.ArrayList;

import awk.DatenhaltungsException;
import awk.entity.DozentTO;
import awk.entity.ModulTO;
import awk.entity.RaumTO;
import awk.entity.StudiengangTO;
import awk.entity.StundenplanTO;

public interface IStundenplanDatenzugriff {
	
	/*
	 * ################################################################
	 * Returns Dozent / Dozenten
	 * ################################################################
	*/
	/***
	 * Liefert alle Dozenten die im persistenten Speicher stehen
	 * @return alle Dozenten
	 * @throws DatenhaltungsException
	 */
	public ArrayList<DozentTO> alleDozenten() throws DatenhaltungsException;
	
	/***
	 * Liefert alle Dozenten mit uebereinstimmender Zeitpraeferenz eines Studiengangs
	 * @param zeitpref
	 * @param studiengang
	 * @return alle Dozenten mit Zeitpraeferenz im Studiengang
	 * @throws DatenhaltungsException
	 */
	public ArrayList<DozentTO> dozentenMitZeitprefUndStudiengang(int zeitpref, StudiengangTO studiengang) throws DatenhaltungsException;
	
	/***
	 * Liefert alle Dozenten eines Studiengangs
	 * @param studiengang
	 * @return alle Dozenten eines Studiengangs
	 * @throws DatenhaltungsException
	 */
	public ArrayList<DozentTO> dozentenVonStudiengang(StudiengangTO studiengang) throws DatenhaltungsException;
	
	/***
	 * Gibt einen Dozenten zu einer Dozentennummer zurück
	 * @param nummer
	 * @return Dozent
	 * @throws DatenhaltungsException
	 */
	public DozentTO dozentMitNummer(int nummer) throws DatenhaltungsException;

	/***
	 * Liefert ein Dozent von einem Modul
	 * @param modulNummer
	 * @return DozentTO
	 * @throws DatenhaltungsException
	 */
	public DozentTO dozentVonModulNummer(int modulNummer) throws DatenhaltungsException;
	/***
	 * Gibt die Dozentnummer der Datenbank eines Dozenten zurück
	 * @param dozent
	 * @return Dozentnummer oder -1. -1 bedeutet, dass kein Dozent in der Datenbank gefunden wurde.
	 * @throws DatenhaltungsException
	 */
	public int dozentNummerVonDozent(DozentTO dozent) throws DatenhaltungsException;
	
	
	/*
	 * ################################################################
	 * Returns Modul / Module
	 * ################################################################
	*/
	/***
	 * Liefert alle Module die im persistenten Speicher stehen
	 * @return alle Module
	 * @throws DatenhaltungsException
	 */
	public ArrayList<ModulTO> alleModule() throws DatenhaltungsException;
	
	/***
	 * Liefert alle Module eines Dozenten
	 * @param dozentNummer
	 * @return alle Module eines Dozenten
	 * @throws DatenhaltungsException
	 */
	public ArrayList<ModulTO> moduleVonDozent(int dozentNummer) throws DatenhaltungsException;
	
	/***
	 * Gibt alle Module eines Dozenten in einem Studiengang zurueck
	 * @param dozent
	 * @param studiengang
	 * @return alle Module von Dozent im Studiengang
	 * @throws DatenhaltungsException
	 */
	public ArrayList<ModulTO> moduleVonDozentImStudiengang(DozentTO dozent, StudiengangTO studiengang) throws DatenhaltungsException;
	
	/****
	 * Liefert die Modulnummer aus der Datenbank.
	 * @param modul
	 * @return
	 * @throws DatenhaltungsException
	 */
	public int modulNummerVonModul(ModulTO modul) throws DatenhaltungsException;
	
	/***
	 * Liefert ein Modul zu einer Modulnummer
	 * @param modulNummer
	 * @return ModulTO
	 * @throws DatenhaltungsException
	 */
	public ModulTO modulMitNummer(int modulNummer) throws DatenhaltungsException;
	
	/*
	 * ################################################################
	 * Returns Studiengang / Studiengaenge
	 * ################################################################
	*/
	/***
	 * Liefer alle Studiengaenge die im persistenten Speicher stehen
	 * @return alle Studiengaenge
	 * @throws DatenhaltungsException
	 */
	public ArrayList<StudiengangTO>  alleStudiengaenge() throws DatenhaltungsException;
	
	/***
	 * Liefert die Studiengangnummer aus der Datenbank
	 * @param studiengang
	 * @return studiengangnummer
	 * @throws DatenhaltungsException
	 */
	public int studiengangNummerVonStudiengang(StudiengangTO studiengang) throws DatenhaltungsException;
	
	/*
	 * ################################################################
	 * Returns Raeume
	 * ################################################################
	*/
	/****
	 * Liefert alle Raeume die im persistenten Speicher stehen
	 * @return alle Raeume
	 * @throws DatenhaltungsException
	 */
	public ArrayList<RaumTO> alleRaeume() throws DatenhaltungsException;

	/***
	 * Liefert die Raumnummer aus der Datenbank
	 * @param raum
	 * @return raumnummer
	 * @throws DatenhaltungsException
	 */
	public int raumNummerVonRaum(RaumTO raum) throws DatenhaltungsException;
	
	/***
	 * Liefert einen Raum zu einer Raumnummer
	 * @param raumNummer
	 * @return RaumTO
	 * @throws DatenhaltungsException
	 */
	public RaumTO raumMitNummer(int raumNummer) throws DatenhaltungsException;
	
	/***
	 * Leifert einen Raum fuer ein Modul
	 * @param modul
	 * @return raum
	 * @throws DatenhaltungsException
	 */
	public RaumTO raumFuerModul(ModulTO modul) throws DatenhaltungsException;
	
	/*
	 * ################################################################
	 * Speichern
	 * ################################################################
	*/
	/***
	 * Speichert einen Dozenten mit seinen Zeitpraeferenzen im persistenten Speicher
	 * @param dozent
	 * @return true/false
	 * @throws DatenhaltungsException
	 */
	public boolean speichereDozentZeitpraeferenzen(DozentTO dozent) throws DatenhaltungsException;

	public boolean speichereStundenplan(StundenplanTO stundenplan) throws DatenhaltungsException;
	
	/*
	 * ################################################################
	 * Laden
	 * ################################################################
	*/
	public StundenplanTO ladeStundenplanFuerStudiengang(StudiengangTO studiengang) throws DatenhaltungsException;
	
}
