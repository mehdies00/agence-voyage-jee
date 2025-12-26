package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
 
import Model.dao.ClientDao;
import Model.dao.FeedbackDao;
import Model.entity.Client;
import Model.entity.Feedback;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FeedbackServlet
 */
@WebServlet(name="fb", urlPatterns = { "/FeedbackServlet","*.fb"})

public class FeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final FeedbackDao feedbackDao = new FeedbackDao();	
	private static final ObjectMapper  mapper = JsonMapper.builder()
	        .addModule(new JavaTimeModule())
	        .build();
    /**
     * Default constructor. 
     */
    public FeedbackServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String path = request.getServletPath();
		if(path.equals("/GET.fb")) {
			Collection<Feedback> feedbacks  =  feedbackDao.findAll();
			 mapper.writeValue(response.getWriter(), feedbacks);
		}else if(path.equals("/POST.fb")) {
			Feedback feedback  =  mapper.readValue(request.getReader(), Feedback.class);			
			feedback = feedbackDao.save(feedback);
			 mapper.writeValue(response.getWriter(), feedback);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
