package awk.usecase.impl;

import java.util.ArrayList;
import java.util.Collections;

import awk.AnwendungskernException;
import awk.DatenhaltungsException;
import awk.entity.StudiengangTO;
import awk.entity.internal.Studiengang;
import awk.persistence.IStundenplanDatenzugriff;
import awk.persistence.impl.StundenplanDatenzugriff;

public class StudiengangManager {

	private IStundenplanDatenzugriff stundenplanDatenzugriff = new StundenplanDatenzugriff();
	
	private static StudiengangManager self;
	private ArrayList<Studiengang> alleStudiengaenge;
	
	public static StudiengangManager getManager(){
		if(self == null){
			return new StudiengangManager();
		}else{
			return self;
		}
	}

	public ArrayList<Studiengang> getAlleStudiengaenge() {
		if(this.alleStudiengaenge == null){
			this.ladeStudiengaenge();
		}
		return alleStudiengaenge;
	}
	
	private void ladeStudiengaenge(){
		this.alleStudiengaenge = new ArrayList<Studiengang>();
		
		try{
			
			for(StudiengangTO s : this.stundenplanDatenzugriff.alleStudiengaenge()){
				this.alleStudiengaenge.add(s.toStudiengang());
			}
		}catch(DatenhaltungsException e){
			e.printStackTrace();
		}
	}
	
	/***
	 * Geniert eine zufaellige Liste aller Studiengaenge
	 * @return Zufallsliste aller Studiengaenge
	 * @throws AnwendungskernException
	 */
	public ArrayList<StudiengangTO> studiengangZufallsliste()
			throws AnwendungskernException {
		ArrayList<StudiengangTO> alleStudiengaenge = new ArrayList<StudiengangTO>();
		for(Studiengang s : this.getAlleStudiengaenge()){
			StudiengangTO sto = new StudiengangTO();
			sto.setName(s.getName());
			alleStudiengaenge.add(sto);
		}
		Collections.shuffle(alleStudiengaenge);
		return alleStudiengaenge;
	}

	/****
	 * Liefert die Studiengangnummer eines Studiengangs
	 * @param studiengang
	 * @return Studiengangnummer
	 * @throws AnwendungskernException
	 */
	public int studiengangNummerVonStudiengang(StudiengangTO studiengang) throws AnwendungskernException{
		try{
			return this.stundenplanDatenzugriff.studiengangNummerVonStudiengang(studiengang);
		}catch(DatenhaltungsException e){
			e.printStackTrace();
			throw new AnwendungskernException();
		}
	}
}
