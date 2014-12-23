import java.util.HashMap;

import awk.AnwendungskernException;
import awk.entity.internal.Studiengang;
import awk.entity.internal.Stundenplan;
import awk.entity.internal.StundenplanSlot;
import awk.usecase.IStundenplanErstellen;
import awk.usecase.impl.StundenplanErstellen;





public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		IStundenplanErstellen se = new StundenplanErstellen();
		
		try {
			HashMap<Studiengang, Stundenplan> urplan = se.erstelleUrplan();
			
			for(Studiengang studiengang : urplan.keySet()){
				System.out.println("############");
				System.out.println("Studiengang: " + studiengang.getName());
				System.out.println("############");
				
				
				for(Integer i : urplan.get(studiengang).getZuordnung().keySet()){
					System.out.println("Slot:" + i);
					System.out.println("Dozent: " + urplan.get(studiengang).getZuordnung().get(i).getDozent().getName());
					System.out.println("Modul: " + urplan.get(studiengang).getZuordnung().get(i).getModul().getBezeichnung());
				}

			}

		} catch (AnwendungskernException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
