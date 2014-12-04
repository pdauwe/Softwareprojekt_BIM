package awk.usecase.impl;

import java.util.ArrayList;

import awk.entity.internal.Raum;

public class RaumManager {

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
		//TODO: raeume laden
		this.alleRaeume = null;
	}
	
}
