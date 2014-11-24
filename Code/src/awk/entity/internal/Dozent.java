package awk.entity.internal;

import java.util.ArrayList;

public class Dozent {

	private String name;
	private ArrayList<Zeit> zeiten;
	
	public Dozent (String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Zeit> getZeiten(){
		return this.zeiten;
	}
	
	public void addZeit(Zeit zeit){
		this.zeiten.add(zeit);
	}
}
