import awk.AnwendungskernException;
import awk.entity.internal.Stundenplan;
import awk.usecase.IStundenplanErstellen;
import awk.usecase.impl.EntityBaboManager;
import awk.usecase.impl.StundenplanErstellen;





public class TestClass {

	public static void main(String[] args) {
		
		
		IStundenplanErstellen spErstellen = new StundenplanErstellen();
		
		try {
			System.out.println(spErstellen.erstelleUrplan());
		} catch (AnwendungskernException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
