package dlg.stundenplan;

import javax.servlet.http.HttpServletRequest;

import awk.AnwendungskernException;
import awk.factory.impl.StundenplanFactory;
import awk.usecase.IStundenplanErstellen;
import dlg.DialogException;
import dlg.IAction;

public class StundenplanErstellung implements IAction{

	@Override
	public String doAction(HttpServletRequest request) throws DialogException {
		
		String nextPage = null;
		
		if(request.getParameter("erstellen") != null){
		
			StundenplanFactory sf = new StundenplanFactory();
			IStundenplanErstellen stundenplanErstellen = sf.getStundenplanErstellen();
			boolean ok = false;
			try {
				ok = stundenplanErstellen.erstelleUrplan();
			} catch (AnwendungskernException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(ok){
				nextPage = "Hauptmenue.jsp";
			}
	
		}else if(request.getParameter("abbrechen") != null){
			nextPage = "Hauptmenue.jsp";
		}
		
		return nextPage;
	}

}
