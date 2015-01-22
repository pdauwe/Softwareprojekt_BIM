package dlg;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dlg.menue.Hauptmenue;
import dlg.stundenplan.StudiengangAuswaehlen;
import dlg.stundenplan.StundenplanAnzeigen;
import dlg.stundenplan.StundenplanErstellung;
import dlg.zeiterfassung.DozentenZeitpraeferenzenErfassen;

/**
 * Servlet implementation class Dispatch
 */
@WebServlet("/Dispatch")
public class Dispatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Hauptmenue hauptmenue = new Hauptmenue();
	DozentenZeitpraeferenzenErfassen zeiterfassung = new DozentenZeitpraeferenzenErfassen();
	StudiengangAuswaehlen studiengangauswahl = new StudiengangAuswaehlen();
	StundenplanErstellung stundenplanerstellung = new StundenplanErstellung();
	StundenplanAnzeigen stundenplananzeigen = new StundenplanAnzeigen();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dispatch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher view = null;
		String page = request.getParameter("page");
		String nextPage = null;
		try {
			/* Aktion ausfuehren */
			if (page.equalsIgnoreCase("hauptmenue")){
				nextPage = hauptmenue.doAction(request);
			}
			else if (page.equalsIgnoreCase("zeiterfassung")){
				nextPage = zeiterfassung.doAction(request);
			}
			else if (page.equalsIgnoreCase("studiengangauswahl")){
				nextPage = studiengangauswahl.doAction(request);
			}
			else if (page.equalsIgnoreCase("stundenplanerstellen")){
				nextPage = stundenplanerstellung.doAction(request);
			}
			else if (page.equalsIgnoreCase("stundenplananzeigen")){
				nextPage = stundenplananzeigen.doAction(request);
			}
			
		} catch (DialogException e) {
			 	nextPage = "zentraleFehlerseite.jsp";
		}
		view = request.getRequestDispatcher(nextPage);
		view.forward(request, response);
		
		
		
	}

}
