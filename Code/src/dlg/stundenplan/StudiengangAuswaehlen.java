package dlg.stundenplan;

import javax.servlet.http.HttpServletRequest;

import awk.entity.internal.Studiengang;
import awk.usecase.impl.StundenplanManager;
import dlg.DialogException;
import dlg.IAction;

public class StudiengangAuswaehlen implements IAction {

	@Override
	public String doAction(HttpServletRequest request) throws DialogException {
		
		String nextPage = null;
		
		if(request.getParameter("stundenplananzeigen") != null){
			
			String sName = request.getParameter("studiengang");
			
			Studiengang studiengang = new Studiengang(sName);
			request.setAttribute("studiengang", sName);
			//request.setAttribute("stundenplan",StundenplanManager.getManager().getStundenplan(studiengang).getZuordnung());
			
			nextPage = "Stundenplan/StundenplanAnzeigen.jsp";
			
		}

		return nextPage;
	}

}
