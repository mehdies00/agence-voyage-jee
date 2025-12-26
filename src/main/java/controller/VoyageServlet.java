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
		if(path.equals("/POST.vg")) {
			Collection< Destination>  destinations  = destinationDao.findAll();
			String fromStr = request.getParameter("from");
			String destinationStr = request.getParameter("destination");
			String departure =  request.getParameter("depart");
			String Return = request.getParameter("Return");
			String StrTravelers = request.getParameter("travelers");
			String typeStr = request.getParameter("type");
			Optional<Destination> fromOpt = destinationDao.findByStr(fromStr, "title");
			Optional<Destination> destinationOpt = destinationDao.findByStr(destinationStr, "title");
			if (!fromOpt.isEmpty() && !destinationOpt.isEmpty()) {
				Destination fromObj =fromOpt.get();
				Destination destinationObj =destinationOpt.get();
				int destinationId = destinationObj.getId();
				int locationId = fromObj.getId() ;
				float budget = 0;
				LocalDate depart = LocalDate.parse(departure);
				LocalDate return_date = LocalDate.parse(Return);;
				int type = Integer.parseInt(typeStr);
				String[] travelers = StrTravelers.split(",");
				for(String traveler:travelers) {
					String t = traveler.trim(); 
  			        int count = Integer.parseInt(t.split(" ")[0]);
 			        if (t.contains("Adult")) {
			            budget += count * 1000.0f;
			        } else if (t.contains("Child")) {
			            budget += count * 600.0f;
			        } else if (t.contains("Infant")) {
			            budget += count * 0.0f;
			        }
			    }
			    
 			    if (type == 2) budget *= 1.5f; // Premium
			    if (type == 3) budget *= 2.5f; // Luxury
				

				Voyage voyage = new Voyage(destinationId, locationId, budget, depart, return_date, type, StrTravelers);
				voyageDao.save(voyage);
				mapper.writeValue(response.getWriter(),voyage);				
			}else {
				mapper.writeValue(response.getWriter(),null);				
			}
		
		};	}

}
