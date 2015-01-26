package awk.usecase.impl;

import java.util.ArrayList;
import java.util.Random;

import awk.AnwendungskernException;
import awk.DatenhaltungsException;
import awk.entity.DozentTO;
import awk.entity.ModulTO;
import awk.entity.StudiengangTO;
import awk.entity.internal.Modul;
import awk.persistence.IStundenplanDatenzugriff;
import awk.persistence.impl.StundenplanDatenzugriff;

/***
 * Klasse zur Verwaltung von Modulen
 */
public class ModulManager {

	private IStundenplanDatenzugriff stundenplanDatenzugriff = new StundenplanDatenzugriff();
	
	private static ModulManager self;
	private ArrayList<Modul> alleModule;
	
	public static ModulManager getManager(){
		if(self == null){
			return new ModulManager();
		}else{
			return self;
		}
	}

	public ArrayList<Modul> getAlleModule(){
		if(this.alleModule == null){
			this.ladeAlleModule();
		}
		return this.alleModule;
	}
	
	private void ladeAlleModule(){
		this.alleModule = new ArrayList<Modul>();
		try{
			
			for(ModulTO m : this.stundenplanDatenzugriff.alleModule()){
				this.alleModule.add(m.toModul());
			}
			
		}catch(DatenhaltungsException e){
			e.printStackTrace();
		}
	}
	
	/***
	 * Liefert ein zufaelliges Modul eines Dozenten in einem Studiengang
	 * @param dozent
	 * @param studiengang
	 * @return ModulTO
	 * @throws AnwendungskernException
	 */
	public ModulTO randomModulVonDozentImStudiengang(DozentTO dozent, StudiengangTO studiengang) throws AnwendungskernException{
		try{
			ArrayList<ModulTO> alleModule =  this.stundenplanDatenzugriff.moduleVonDozentImStudiengang(dozent, studiengang);
			if(alleModule.size() == 0){
				return null;
			}else{
				Random randomGenerator = new Random();
				int randomIndex = randomGenerator.nextInt(alleModule.size());
				return alleModule.get(randomIndex);
			}
		}catch(DatenhaltungsException e){
			e.printStackTrace();
			throw new AnwendungskernException();
		}
	}
	
	/****
	 * Liefert die Modulnummer eines Moduls
	 * @param modul
	 * @return modulnummer
	 * @throws AnwendungskernException
	 */
	public int modulNummerVonModul(ModulTO modul) throws AnwendungskernException{
		try{
			return this.stundenplanDatenzugriff.modulNummerVonModul(modul);
		}catch(DatenhaltungsException e){
			e.printStackTrace();
			throw new AnwendungskernException();
		}
	}
	
	/***
	 * Liefert ein Modul zu einer Modulnummer aus der Datenbank
	 * @param modulNummer
	 * @return ModulTO
	 * @throws AnwendungskernException
	 */
	public ModulTO modulMitNummer(int modulNummer) throws AnwendungskernException{
		try{
			return this.stundenplanDatenzugriff.modulMitNummer(modulNummer);
		}catch(DatenhaltungsException e){
			e.printStackTrace();
			throw new AnwendungskernException();
		}
	}

}
