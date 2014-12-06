package awk.usecase.impl;

import java.util.ArrayList;

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
	
	private StudiengangManager(){
		this.ladeStudiengaenge();
	}

	public ArrayList<Studiengang> getAlleStudiengaenge() {
		return alleStudiengaenge;
	}
	
	private void ladeStudiengaenge(){
		ArrayList<StudiengangTO> studiengaenge;
		
		try{
			studiengaenge = this.stundenplanDatenzugriff.alleStudiengaenge();
			
			for(StudiengangTO s : studiengaenge){
				this.alleStudiengaenge.add(s.toStudiengang());
			}
		}catch(DatenhaltungsException e){
			e.printStackTrace();
		}
	}
	
	
	
	
}
