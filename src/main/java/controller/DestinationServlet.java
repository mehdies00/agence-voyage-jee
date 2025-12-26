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
	private static final DestinationDao  destinationDao = new  DestinationDao();
	private static final  ObjectMapper  mapper = new ObjectMapper();

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
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String path = request.getServletPath();
		if(path.equals("/GET.dt")) {
			Collection< Destination>  destinations  = destinationDao.findAll();
			mapper.writeValue(response.getWriter(), destinations);
 		};
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
