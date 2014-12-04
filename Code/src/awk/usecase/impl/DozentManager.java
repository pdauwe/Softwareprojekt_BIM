package awk.usecase.impl;

import java.util.ArrayList;

import awk.entity.internal.Dozent;

public class DozentManager {

	
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
		// TODO: dozenten laden
		this.alleDozenten = null;
	}
}
