package dlg.stundenplan;

import javax.servlet.http.HttpServletRequest;

import awk.AnwendungskernException;
import awk.entity.StudiengangTO;
import awk.entity.StundenplanTO;
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
			
			StudiengangTO studiengang = new StudiengangTO();
			studiengang.setName(sName);
			request.setAttribute("studiengang", sName);
			
			
			StundenplanManager sm = StundenplanManager.getManager();
			StundenplanTO sp = null;
			try {
				 sp = sm.getStundenplan(studiengang);
			} catch (AnwendungskernException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(sp != null){
				request.setAttribute("stundenplan",sp);
			}
			nextPage = "Stundenplan/StundenplanAnzeigen.jsp";
			
		}

		return nextPage;
	}

}
