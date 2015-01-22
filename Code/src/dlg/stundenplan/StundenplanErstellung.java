package dlg.stundenplan;

import javax.servlet.http.HttpServletRequest;

import awk.AnwendungskernException;
import awk.usecase.impl.StundenplanManager;
import dlg.DialogException;
import dlg.IAction;

public class StundenplanErstellung implements IAction{

	@Override
	public String doAction(HttpServletRequest request) throws DialogException {
		
		Thread seThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try{
					StundenplanManager.getManager().createUrplan();
				}catch(AnwendungskernException e){
					e.printStackTrace();
				}
			}
		});
		
		seThread.start();

		String nextPage = "Stundenplan/StartErstellung.jsp";
		return nextPage;
	}

}


