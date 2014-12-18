package awk.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import awk.entity.internal.Stundenplan;

public class StundenplanTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StudiengangTO studiengang;
	private HashMap<Integer, StundenplanSlotTO> zuordnung;
	
	public StundenplanTO(){
		this.zuordnung = new HashMap<Integer, StundenplanSlotTO>();
	}
	
	public Stundenplan toStundenplan(){
		
		Stundenplan s = new Stundenplan(this.studiengang.toStudiengang());
		for(Map.Entry<Integer, StundenplanSlotTO> entry : zuordnung.entrySet()){
			s.addZuordnung(entry.getKey().intValue(), entry.getValue().toStundenplanSlot());
		}
		return s;
		
	}

	public StudiengangTO getStudiengang() {
		return studiengang;
	}

	public void setStudiengang(StudiengangTO studiengang) {
		this.studiengang = studiengang;
	}
	
	public Map<Integer, StundenplanSlotTO> getZuordnung() {
		return this.zuordnung;
	}
	
	public boolean addZuordnung(int zeitslot, StundenplanSlotTO stundenplanslot){
		if(this.zuordnung.containsKey(Integer.valueOf(zeitslot))){
			return false;
		}else{
			this.zuordnung.put(Integer.valueOf(zeitslot), stundenplanslot);
			return true;
		}
	}
	
}
