package awk.entity.internal;

import java.util.HashMap;
import java.util.Map;

public class Stundenplan {

	private Studiengang studiengang;
	private Map<Integer, StundenplanSlot> zuordnung;
	
	public Stundenplan  (Studiengang studiengang){
		this.studiengang = studiengang;
		zuordnung = new HashMap<Integer, StundenplanSlot>();
	}

	public Studiengang getStudiengang() {
		return studiengang;
	}

	public void setStudiengang(Studiengang studiengang) {
		this.studiengang = studiengang;
	}

	public Map<Integer, StundenplanSlot> getZuordnung() {
		return this.zuordnung;
	}
	
	/***
	 * Fügt einen StundenplanSlot zum Stundenplan hinzu, wenn der Zeitslot noch nicht besetzt ist. 
	 * @param zeitslot
	 * @param stundenplanslot
	 * @return
	 */
	public boolean addZuordnung(int zeitslot, StundenplanSlot stundenplanslot){
		if(this.zuordnung.containsKey(zeitslot)){
			return false;
		}else{
			this.getZuordnung().put(Integer.valueOf(zeitslot), stundenplanslot);
			return true;
		}
	}

}
