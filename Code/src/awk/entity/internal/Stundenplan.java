package awk.entity.internal;

import java.util.ArrayList;

public class Stundenplan {

	private ArrayList<ModulWrapper> module;
	private Studiengang studiengang;
	private Semester semester;
	
	public Stundenplan(Studiengang studiengang, Semester semester){
		
	}

	public ArrayList<ModulWrapper> getModule() {
		return module;
	}

	public void addModul(ModulWrapper modul) {
		this.module.add(modul);
	}

	public Studiengang getStudiengang() {
		return studiengang;
	}

	public void setStudiengang(Studiengang studiengang) {
		this.studiengang = studiengang;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}
	
	
}
