package dlg.menue;

import javax.servlet.http.HttpServletRequest;

import awk.usecase.impl.DozentManager;
import awk.usecase.impl.StudiengangManager;
import dlg.DialogException;
import dlg.IAction;

public class Hauptmenue implements IAction{

		public String doAction(HttpServletRequest request) throws DialogException{
		
			String nextPage = null;
			
			if (request.getParameter("zeiterfassung") != null ){
				request.setAttribute("dozenten", DozentManager.getManager().getAlleDozenten());
				nextPage = "Zeiterfassung/Zeiterfassung.jsp";
			}else if(request.getParameter("studiengangauswahl") != null){
				request.setAttribute("studiengaenge", StudiengangManager.getManager().getAlleStudiengaenge());
				nextPage = "Stundenplan/StudiengangAuswaehlen.jsp";
			}else if(request.getParameter("stundenplanerstellen") != null){
				nextPage = "Stundenplan/StartErstellung.jsp";
			}
			
			
			
			
			return nextPage;		

		}

}
