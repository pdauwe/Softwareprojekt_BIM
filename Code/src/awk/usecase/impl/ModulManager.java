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

}
