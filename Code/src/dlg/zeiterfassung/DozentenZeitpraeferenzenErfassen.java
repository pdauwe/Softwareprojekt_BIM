package dlg.zeiterfassung;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import awk.AnwendungskernException;
import awk.entity.DozentTO;
import awk.usecase.impl.DozentManager;
import dlg.DialogException;
import dlg.IAction;

public class DozentenZeitpraeferenzenErfassen implements IAction {

	@Override
	public String doAction(HttpServletRequest request) throws DialogException {
		
		String nextPage = null;
		
		ArrayList<Integer> prefZeiten = new ArrayList<Integer>();
		
		if (request.getParameter("speichern") != null ) {
			
			for(int i=0; i<14; i++){
				String[] values = request.getParameterValues(String.valueOf(i));
				Integer value = Integer.getInteger(values[0]);
				prefZeiten.add(value);
			}
			
			DozentTO dozent = new DozentTO();
			dozent.setName(request.getParameterValues("nameauswahl")[0]);
			
			for(Integer zeit : prefZeiten){
				dozent.addZeit(zeit);
			}
			
			boolean ok = false;
//			try{
//				ok = DozentManager.getManager().zeitpraeferenzenFuerDozentSpeichern(dozent);
//			}catch(AnwendungskernException e){
//				e.printStackTrace();
//				throw new DialogException();
//			}
			
			if(ok){
				nextPage = "Zeiterfassung/DozentenZeitpraeferenzenErfassen.jsp";
			}else{
				nextPage = "zentraleFehlerseite.jsp";
			}
		}

		return nextPage;
	}

}
