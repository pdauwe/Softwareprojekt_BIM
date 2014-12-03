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
	
	public boolean speichereDozent(DozentTO dozent) throws DatenhaltungsException;

	public boolean speichereStundenplan(StundenplanTO stundenplan) throws DatenhaltungsException;
	public StundenplanTO ladeStundenplanFuerStudiengang(StudiengangTO studiengang) throws DatenhaltungsException;
	
}
