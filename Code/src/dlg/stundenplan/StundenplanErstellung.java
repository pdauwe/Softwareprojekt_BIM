package dlg.stundenplan;

import javax.servlet.http.HttpServletRequest;

import awk.AnwendungskernException;
import awk.factory.IStundenplanFactory;
import awk.factory.impl.StundenplanFactory;
import awk.usecase.IStundenplanErstellen;
import dlg.DialogException;
import dlg.IAction;

public class StundenplanErstellung implements IAction{

	@Override
	public String doAction(HttpServletRequest request) throws DialogException {
		
		IStundenplanFactory sf = new StundenplanFactory();
		final IStundenplanErstellen se = sf.getStundenplanErstellen();
		
		Thread spErstellenThread = new Thread(new Runnable() {
			public void run() {
				try {
					se.erstelleUrplan();
				} catch (AnwendungskernException e) {
					e.printStackTrace();
				}
			}
		});
		
		spErstellenThread.start();

		String nextPage = "Stundenplan/StartErstellung.jsp";
	
		
		return nextPage;
	}

}


