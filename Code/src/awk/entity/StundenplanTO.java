package awk.entity;

import java.io.Serializable;
import java.util.ArrayList;

import awk.entity.internal.Stundenplan;

public class StundenplanTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<ModulWrapperTO> module;
	private StudiengangTO studiengang;
	private SemesterTO semester;
	
	public StundenplanTO(){
		
	}
	
	public Stundenplan toStudiengang(){
		Stundenplan s = new Stundenplan(this.getStudiengang().toStudiengang(), this.getSemester().toSemester());
		for(ModulWrapperTO m : this.getModule()){
			s.addModul(m.toModulWrapper());
		}
		return s;
	}

	public ArrayList<ModulWrapperTO> getModule() {
		return module;
	}

	public void setModule(ArrayList<ModulWrapperTO> module) {
		this.module = module;
	}

	public StudiengangTO getStudiengang() {
		return studiengang;
	}

	public void setStudiengang(StudiengangTO studiengang) {
		this.studiengang = studiengang;
	}

	public SemesterTO getSemester() {
		return semester;
	}

	public void setSemester(SemesterTO semester) {
		this.semester = semester;
	}
	
	
}
