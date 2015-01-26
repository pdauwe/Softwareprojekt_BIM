package dlg.zeiterfassung;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import awk.AnwendungskernException;
import awk.entity.DozentTO;
import awk.factory.IStundenplanFactory;
import awk.factory.impl.StundenplanFactory;
import awk.usecase.IZeitpraeferenzErfassen;
import awk.usecase.impl.DozentManager;
import dlg.DialogException;
import dlg.IAction;

public class DozentenZeitpraeferenzenErfassen implements IAction {

	@Override
	public String doAction(HttpServletRequest request) throws DialogException {
		
		String nextPage = null;
		
		ArrayList<Integer> prefZeiten = new ArrayList<Integer>();
		DozentTO dozent = new DozentTO();
		dozent.setName(request.getParameterValues("name")[0]);
		boolean ok = false;
		
		if (request.getParameter("speichern") != null ) {
			
			if(request.getParameterValues("zeitslot") == null){
				return this.goBack(request);
			}
			
			String[] values = request.getParameterValues("zeitslot");
			
			
			
			for (int i = 0; i < values.length; i++){	
				Integer value = Integer.parseInt(values[i]);
				prefZeiten.add(value);
			}
			
			for(Integer zeit : prefZeiten){
				dozent.addZeit(zeit);
			}

			try{
				IStundenplanFactory sf = new StundenplanFactory();
				IZeitpraeferenzErfassen ze = sf.getZeitpraeferenzenErfassen();
				ok = ze.zeitpraeferenzenSpeichern(dozent);
			}catch(AnwendungskernException e){
				e.printStackTrace();
				throw new DialogException();
			}
			
			if(ok){
				nextPage = "Hauptmenue.jsp";
			}else{
				nextPage = "zentraleFehlerseite.jsp";
			}
		}else if(request.getParameter("delete") != null){
			try{
				IStundenplanFactory sf = new StundenplanFactory();
				IZeitpraeferenzErfassen ze = sf.getZeitpraeferenzenErfassen();
				ok = ze.zeitpraeferenzenSpeichern(dozent);
			}catch(AnwendungskernException e){
				e.printStackTrace();
				throw new DialogException();
			}
			if(ok){
				return this.goBack(request);
			}else{
				nextPage = "zentraleFehlerseite.jsp";
			}
		}
		else if(request.getParameter("abbrechen") != null){
			nextPage = "Hauptmenue.jsp";
		}

		return nextPage;
	}
	
	private String goBack(HttpServletRequest request) throws DialogException {
		request.setAttribute("dozenten", DozentManager.getManager().getAlleDozenten());
		return "Zeiterfassung/Zeiterfassung.jsp";
	}

}
