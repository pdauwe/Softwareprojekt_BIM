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
	
	private DozentManager(){
		this.ladeDozenten();
	}
	
	public ArrayList<Dozent> getAlleDozenten(){
		return this.alleDozenten;
	}
	
	private void ladeDozenten(){
		ArrayList<DozentTO> dozenten;
		try{
			dozenten = this.stundenplanDatenzugriff.alleDozenten();
			
			for(DozentTO d : dozenten){
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
