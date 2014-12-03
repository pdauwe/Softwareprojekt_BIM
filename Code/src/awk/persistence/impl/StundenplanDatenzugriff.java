package awk.persistence.impl;

import java.util.ArrayList;

import awk.DatenhaltungsException;
import awk.entity.DozentTO;
import awk.entity.ModulTO;
import awk.entity.RaumTO;
import awk.entity.StudiengangTO;
import awk.entity.StundenplanTO;
import awk.persistence.IStundenplanDatenzugriff;

public class StundenplanDatenzugriff implements IStundenplanDatenzugriff {

	@Override
	public ArrayList<DozentTO> alleDozenten() throws DatenhaltungsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ModulTO> alleModule() throws DatenhaltungsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<StudiengangTO> alleStudiengaenge()
			throws DatenhaltungsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<RaumTO> alleRaeume() throws DatenhaltungsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean speichereDozent(DozentTO dozent)
			throws DatenhaltungsException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean speichereStundenplan(StundenplanTO stundenplan)
			throws DatenhaltungsException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public StundenplanTO ladeStundenplanFuerStudiengang(
			StudiengangTO studiengang) throws DatenhaltungsException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
