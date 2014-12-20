package dlg;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dlg.menue.Hauptmenue;
import dlg.zeiterfassung.DozentenZeitpraeferenzenErfassen;

public class Dispatcher extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	Hauptmenue hauptmenue = new Hauptmenue();
	DozentenZeitpraeferenzenErfassen zeiterfassung = new DozentenZeitpraeferenzenErfassen();
       
    public Dispatcher() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 processRequest(request, response);
	}

	protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		RequestDispatcher view = null;
		String page = request.getParameter("page");
		String nextPage = null;
		try {
			/* Aktion ausfuehren */
			if (page.equalsIgnoreCase("Hauptmenue"))
				nextPage = hauptmenue.doAction(request);
			else if (page.equalsIgnoreCase("Zeiterfassung"))
				nextPage = zeiterfassung.doAction(request);
			
			
		} catch (DialogException e) {
			 	nextPage = "zentraleFehlerseite.jsp";
		}
		view = request.getRequestDispatcher(nextPage);
		view.forward(request, response);
		
		
		
	}
}
