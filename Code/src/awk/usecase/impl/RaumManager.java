package awk.usecase.impl;

import java.util.ArrayList;

import awk.AnwendungskernException;
import awk.DatenhaltungsException;
import awk.entity.RaumTO;
import awk.entity.internal.Raum;
import awk.persistence.IStundenplanDatenzugriff;
import awk.persistence.impl.StundenplanDatenzugriff;

public class RaumManager {

	private IStundenplanDatenzugriff stundenplanDatenzugriff = new StundenplanDatenzugriff();
	
	private static RaumManager self;
	private ArrayList<Raum> alleRaeume;
	
	
	public static RaumManager getManager(){
		if(self == null){
			return new RaumManager();
		}else{
			return self;
		}
	}
	
	public ArrayList<Raum> getAlleRaeume(){
		if(this.alleRaeume == null){
			this.ladeAlleRaeume();
		}
		
		return this.alleRaeume;
	}
	
	private void ladeAlleRaeume(){
		this.alleRaeume = new ArrayList<Raum>();
		try{
			
			for(RaumTO r : this.stundenplanDatenzugriff.alleRaeume()){
				this.alleRaeume.add(r.toRaum());
			}
			
		}catch(DatenhaltungsException e){
			e.printStackTrace();
		}
	}
	
	/****
	 * Liefert die Raumnummer eines Raumes
	 * @param raum
	 * @return Raumnummer
	 * @throws AnwendungskernException
	 */
	public int raumNummerVonRaum(RaumTO raum) throws AnwendungskernException{
		try{
			return this.stundenplanDatenzugriff.raumNummerVonRaum(raum);
		}catch(DatenhaltungsException e){
			e.printStackTrace();
			throw new AnwendungskernException();
		}
	}
	
}
