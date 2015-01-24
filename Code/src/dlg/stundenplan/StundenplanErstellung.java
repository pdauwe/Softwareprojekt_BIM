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
		
		String nextPage = null;
		boolean ok = false;
		
		if(request.getParameter("erstellen") != null){
			try{
				IStundenplanFactory sf = new StundenplanFactory();
				IStundenplanErstellen se = sf.getStundenplanErstellen();
				ok = se.erstelleUrplan();
			}catch(AnwendungskernException e){
				e.printStackTrace();
			}

			if(ok){
				nextPage = "Stundenplan/StundenplanErstellt.jsp";
			}else{
				nextPage = "zentraleFehlerseite.jsp";
			}
		}else if(request.getParameter("hauptmenue") != null){
			nextPage = "Hauptmenue.jsp";
		}
		
		
		return nextPage;
	}

}


