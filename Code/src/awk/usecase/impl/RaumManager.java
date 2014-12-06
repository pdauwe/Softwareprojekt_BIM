package awk.usecase.impl;

import java.util.ArrayList;

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
	
	private RaumManager(){
		this.ladeAlleRaeume();
	}
	
	public ArrayList<Raum> getAlleRaeume(){
		return this.alleRaeume;
	}
	
	private void ladeAlleRaeume(){
		ArrayList<RaumTO> raeume;
		try{
			raeume = this.stundenplanDatenzugriff.alleRaeume();
			
			for(RaumTO r : raeume){
				this.alleRaeume.add(r.toRaum());
			}
			
		}catch(DatenhaltungsException e){
			e.printStackTrace();
		}
	}
	
}
