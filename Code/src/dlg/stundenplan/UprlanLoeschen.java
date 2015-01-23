package dlg.stundenplan;

import javax.servlet.http.HttpServletRequest;

import awk.AnwendungskernException;
import awk.usecase.impl.StundenplanManager;
import dlg.DialogException;
import dlg.IAction;

public class UprlanLoeschen implements IAction {

	@Override
	public String doAction(HttpServletRequest request) throws DialogException {
		
		String nextPage = null;
		if(request.getParameter("delete") != null){
			try {
				boolean ok = StundenplanManager.getManager().deleteUrplan();
				if(ok){
					nextPage = "Stundenplan/StundenplanErstellung.jsp";
				}
			} catch (AnwendungskernException e) {
				e.printStackTrace();
			}
			
		}else if(request.getParameter("abbrechen") != null){
			nextPage = "Hauptmenue.jsp";
		}
			
		return nextPage;
	}

}
