package dlg;

import javax.servlet.http.HttpServletRequest;

public interface IAction {
	
	public String doAction (HttpServletRequest request) throws DialogException; 

}
