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
	
	
	
	
}
