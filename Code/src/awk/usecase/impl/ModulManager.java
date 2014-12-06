package awk.usecase.impl;

import java.util.ArrayList;

import awk.DatenhaltungsException;
import awk.entity.ModulTO;
import awk.entity.internal.Modul;
import awk.persistence.IStundenplanDatenzugriff;
import awk.persistence.impl.StundenplanDatenzugriff;

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
	
	private ModulManager(){
		this.ladeAlleModule();
	}
	
	public ArrayList<Modul> getAlleModule(){
		return this.alleModule;
	}
	
	private void ladeAlleModule(){
		ArrayList<ModulTO> module;
		try{
			module = this.stundenplanDatenzugriff.alleModule();
			
			for(ModulTO m : module){
				this.alleModule.add(m.toModul());
			}
			
		}catch(DatenhaltungsException e){
			e.printStackTrace();
		}
	}

}
