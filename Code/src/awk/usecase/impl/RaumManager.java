package awk.usecase.impl;

import java.util.ArrayList;
import java.util.Random;

import awk.AnwendungskernException;
import awk.DatenhaltungsException;
import awk.entity.ModulTO;
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
	
	/***
	 * Liefert ein Raum zu einer Raumnummer aus der Datenbank
	 * @param raumNummer
	 * @return RaumTO
	 * @throws AnwendungskernException
	 */
	public RaumTO raumMitNummer(int raumNummer) throws AnwendungskernException{
		try{
			return this.stundenplanDatenzugriff.raumMitNummer(raumNummer);
		}catch(DatenhaltungsException e){
			e.printStackTrace();
			throw new AnwendungskernException();
		}
	}
	
	/***
	 * Liefert einen Raum fuer ein Modul
	 * @param modul
	 * @return raum
	 * @throws AnwendungskernException
	 */
	public RaumTO raumFuerModul(ModulTO modul) throws AnwendungskernException{
		ArrayList<RaumTO> raeume = null;
		
		try{
			raeume =  this.stundenplanDatenzugriff.alleRaeume();
			
			if(raeume.size() == 0){
				return null;
			}else{
				Random randomGenerator = new Random();
				RaumTO raum;
				do{
					int randomIndex = randomGenerator.nextInt(raeume.size());
					raum = raeume.get(randomIndex);
				}while(!(raum.isComputerraum() == modul.isBenoetigtComputerraum()));
					
				return raum;
			}	
		}catch(DatenhaltungsException e){
			e.printStackTrace();
			throw new AnwendungskernException();
		}
	}
	
}
