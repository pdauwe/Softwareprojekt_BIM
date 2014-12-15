package awk.entity;

import java.io.Serializable;
import java.util.ArrayList;

import awk.entity.internal.Dozent;

public class DozentTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private ArrayList<Integer> prefZeiten;
	private ArrayList<Integer> gesperrteZeiten;
	
	public DozentTO(){
		this.prefZeiten = new ArrayList<Integer>();
		this.gesperrteZeiten = new ArrayList<Integer>();
	}
	
	public Dozent toDozent(){
		return new Dozent(this.getName());
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
