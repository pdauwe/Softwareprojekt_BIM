package dlg.menue;

import javax.servlet.http.HttpServletRequest;

import awk.usecase.impl.DozentManager;
import dlg.DialogException;
import dlg.IAction;

public class Hauptmenue implements IAction{

		public String doAction(HttpServletRequest request) throws DialogException{
		
			String nextPage = null;
			
			if (request.getParameter("zeiterfassung") != null ){
				
				request.setAttribute("dozenten", DozentManager.getManager().getAlleDozenten());

				nextPage = "Zeiterfassung/Zeiterfassung.jsp";
			}
			    
			return nextPage;		

		}

}
