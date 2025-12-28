package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import Model.dao.DestinationDao;
import Model.dao.FeedbackDao;
import Model.dao.VoyageDao;
import Model.entity.Destination;
import Model.entity.Feedback;
import Model.entity.Voyage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FeedbackServlet
 */
@WebServlet(name="vg", urlPatterns = { "/VoyageServlet","*.vg"})

public class VoyageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final DestinationDao  destinationDao = new  DestinationDao();
	private static final VoyageDao voyageDao = new VoyageDao();
	private static final ObjectMapper  mapper = JsonMapper.builder()
	        .addModule(new JavaTimeModule())
	        .build();
    /**
     * Default constructor. 
     */
    public VoyageServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    String path = request.getServletPath();

	    if (path.equals("/POST.vg")) {
	        try {
	            // 1. Récupération des paramètres (Visibles et Cachés)
	            String fromStr = request.getParameter("from");
	            String destinationStr = request.getParameter("destination");
	            String departure = request.getParameter("depart");
	            String returnStr = request.getParameter("Return");
	            
	            // On récupère les nombres directement depuis les inputs cachés
	            int nbAdults = Integer.parseInt(request.getParameter("nbAdults"));
	            int nbChildren = Integer.parseInt(request.getParameter("nbChildren"));
	            int nbBabies = Integer.parseInt(request.getParameter("nbBabies"));
	            int type = Integer.parseInt(request.getParameter("type"));
	            
	            // Le texte complet pour l'affichage (ex: "2 Adults, 1 Child")
	            String travelersSummary = request.getParameter("travelers");

	            // 2. Recherche des destinations en BDD
	            Optional<Destination> fromOpt = destinationDao.findByStr(fromStr, "title");
	            Optional<Destination> destinationOpt = destinationDao.findByStr(destinationStr, "title");

	            if (fromOpt.isPresent() && destinationOpt.isPresent()) {
	                Destination fromObj = fromOpt.get();
	                Destination destinationObj = destinationOpt.get();

	                // 3. Calcul du budget basé sur les compteurs exacts
	                float basePrice = 1000.0f; // Prix de base
	                float budget = (nbAdults * basePrice) + (nbChildren * basePrice * 0.6f);
	                
	                // Application du multiplicateur de classe
	                if (type == 2) budget *= 1.5f; // Premium
	                else if (type == 3) budget *= 2.5f; // Luxury

	                // 4. Création de l'objet Voyage
	                LocalDate depart = LocalDate.parse(departure);
	                LocalDate returnDate = LocalDate.parse(returnStr);

	                Voyage voyage = new Voyage(
	                    destinationObj.getId(), 
	                    fromObj.getId(), 
	                    budget, 
	                    depart, 
	                    returnDate, 
	                    type, 
	                    travelersSummary
	                );

	                // 5. Sauvegarde et Réponse
	                voyageDao.save(voyage);
	                mapper.writeValue(response.getWriter(), voyage);
	            } else {
	                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	                mapper.writeValue(response.getWriter(), "Destination introuvable.");
	            }
	        } catch (Exception e) {
	            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	            mapper.writeValue(response.getWriter(), "Erreur : " + e.getMessage());
	        }
	    }
	}

}
