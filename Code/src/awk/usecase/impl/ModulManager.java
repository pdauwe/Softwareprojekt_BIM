package awk.usecase.impl;

import java.util.ArrayList;

import awk.entity.internal.Modul;

public class ModulManager {

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
		// TODO: module laden
		this.alleModule = null;
	}

}
