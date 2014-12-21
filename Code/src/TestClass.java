import java.util.HashMap;

import awk.AnwendungskernException;
import awk.entity.internal.Studiengang;
import awk.entity.internal.Stundenplan;
import awk.usecase.IStundenplanErstellen;
import awk.usecase.impl.StundenplanErstellen;





public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		IStundenplanErstellen se = new StundenplanErstellen();
		
		try {
			HashMap<Studiengang, Stundenplan> urplan = se.erstelleUrplan();
			
			System.out.println(urplan);
			
		} catch (AnwendungskernException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
