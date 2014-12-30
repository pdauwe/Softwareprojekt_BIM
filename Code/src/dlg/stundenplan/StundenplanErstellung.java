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
		
		String nextPage = "Stundenplan/StartErstellung.jsp";
	
		
		return nextPage;
	}

}
