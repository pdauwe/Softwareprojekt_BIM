package awk.usecase.impl;

import java.util.ArrayList;

import awk.AnwendungskernException;
import awk.DatenhaltungsException;
import awk.entity.DozentTO;
import awk.entity.internal.Dozent;
import awk.persistence.IStundenplanDatenzugriff;
import awk.persistence.impl.StundenplanDatenzugriff;

public class DozentManager {

	private IStundenplanDatenzugriff stundenplanDatenzugriff = new StundenplanDatenzugriff();
	
	private static DozentManager self;
	private ArrayList<Dozent> alleDozenten;
	
	public static DozentManager getManager(){
		if(self == null){
			return new DozentManager();
		}else{
			return self;
		}
	}
	
	public ArrayList<Dozent> getAlleDozenten(){
		if(this.alleDozenten == null){
			this.ladeDozenten();
		}
		
		return this.alleDozenten;
	}
	
	private void ladeDozenten(){
		this.alleDozenten = new ArrayList<Dozent>();
		try{
			
			for(DozentTO d : this.stundenplanDatenzugriff.alleDozenten()){
				this.alleDozenten.add(d.toDozent());
			}
			
		}catch(DatenhaltungsException e){
			e.printStackTrace();
		}
	}
	
	public boolean zeitpraeferenzenFuerDozentSpeichern(DozentTO dozent) throws AnwendungskernException{
		try{
			return this.stundenplanDatenzugriff.speichereDozentZeitpraeferenzen(dozent);
		}catch(DatenhaltungsException e){
			e.printStackTrace();
			throw new AnwendungskernException();
		}
	}
}
