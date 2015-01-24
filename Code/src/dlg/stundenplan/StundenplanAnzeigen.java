package dlg.stundenplan;

import javax.servlet.http.HttpServletRequest;

import awk.usecase.impl.StudiengangManager;
import dlg.DialogException;
import dlg.IAction;

public class StundenplanAnzeigen implements IAction {

	@Override
	public String doAction(HttpServletRequest request) throws DialogException {
		
		String nextPage = null;
		
		if(request.getParameter("studiengangwechseln") != null){
			request.setAttribute("studiengaenge", StudiengangManager.getManager().getAlleStudiengaenge());
			nextPage = "Stundenplan/StudiengangAuswaehlen.jsp";
		}else if(request.getParameter("hauptmenue") != null){
			nextPage = "Hauptmenue.jsp";
		}
		return nextPage;
	}

}
