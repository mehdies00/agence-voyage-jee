package controller;

import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.databind.ObjectMapper;
import Model.dao.DestinationDao;
import Model.entity.Destination;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FeedbackServlet
 */
@WebServlet(name="dt", urlPatterns = { "/DestinationServlet","*.dt"})

public class DestinationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DestinationServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if(path.equals("/GET.dt")) {
			DestinationDao  destinationDao = new  DestinationDao();
			Collection< Destination>  destinations  = destinationDao.findAll();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			ObjectMapper  mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(destinations);
			response.getWriter().write(json);
			
		};
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
