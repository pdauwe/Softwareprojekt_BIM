package dlg.stundenplan;

import javax.servlet.http.HttpServletRequest;

import dlg.DialogException;
import dlg.IAction;

public class StundenplanErstellt implements IAction{

	@Override
	public String doAction(HttpServletRequest request) throws DialogException {
		
		String nextPage = null;
		
		if(request.getAttribute("hauptmenue") != null){
			nextPage = "../Hauptmenue.jsp";
		}
		
		return nextPage;
	}

}
