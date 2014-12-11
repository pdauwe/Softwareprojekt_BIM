package awk.persistence;

import java.util.ArrayList;

import awk.DatenhaltungsException;
import awk.entity.DozentTO;
import awk.entity.ModulTO;
import awk.entity.RaumTO;
import awk.entity.StudiengangTO;
import awk.entity.StundenplanTO;

public interface IStundenplanDatenzugriff {

	public ArrayList<DozentTO> alleDozenten() throws DatenhaltungsException;
	public ArrayList<ModulTO> alleModule() throws DatenhaltungsException;
	public ArrayList<StudiengangTO>  alleStudiengaenge() throws DatenhaltungsException;
	public ArrayList<RaumTO> alleRaeume() throws DatenhaltungsException;
	
	public boolean speichereDozentZeitpraeferenzen(DozentTO dozent) throws DatenhaltungsException;
	
	public ArrayList<ModulTO> moduleVonDozent(int dozentNummer) throws DatenhaltungsException;
	public ArrayList<DozentTO> dozentenMitZeitprefUndStudiengang(int zeitpref, StudiengangTO studiengang) throws DatenhaltungsException;
	public ArrayList<DozentTO> dozentenVonStudiengang(StudiengangTO studiengang) throws DatenhaltungsException;
	
	public DozentTO dozentMitNummer(int nummer) throws DatenhaltungsException;

	public boolean speichereStundenplan(StundenplanTO stundenplan) throws DatenhaltungsException;
	public StundenplanTO ladeStundenplanFuerStudiengang(StudiengangTO studiengang) throws DatenhaltungsException;
	
}
