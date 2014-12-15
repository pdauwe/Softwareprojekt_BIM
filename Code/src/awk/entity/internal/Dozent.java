package awk.entity.internal;

import java.util.ArrayList;

public class Dozent {

	private String name;
	private ArrayList<Integer> prefZeiten;
	private ArrayList<Integer> gesperrteZeiten;
	
	public Dozent (String name){
		this.name = name;
		this.prefZeiten = new ArrayList<Integer>();
		this.gesperrteZeiten = new ArrayList<Integer>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Integer> getZeiten(){
		return this.prefZeiten;
	}
	
	public void addZeit(int zeit){
		this.prefZeiten.add(zeit);
	}
	
	public void setZeiten(ArrayList<Integer> zeiten){
		this.prefZeiten = zeiten;
	}
	
	public void removeZeit(int zeit){
		int indexOfZeit = this.prefZeiten.indexOf(zeit);
		this.prefZeiten.remove(indexOfZeit);
	}

	public ArrayList<Integer> getGesperrteZeiten() {
		return gesperrteZeiten;
	}

	public void addGesperrteZeiten(Integer gesperrtZeit) {
		this.gesperrteZeiten.add(gesperrtZeit);
	}
	
	
}
