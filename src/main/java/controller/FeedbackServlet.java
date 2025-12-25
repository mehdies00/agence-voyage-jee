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
			FeedbackDao feedbackDao = new FeedbackDao();
			Collection<Feedback> feedbacks  =  feedbackDao.findAll();
			
			ObjectMapper  mapper = JsonMapper.builder()
			        .addModule(new JavaTimeModule())
			        .build();
			String json = mapper.writeValueAsString(feedbacks);
			
			response.getWriter().write(json);
		}else if(path.equals("/POST.fb")) {
			ObjectMapper  mapper = JsonMapper.builder()
			        .addModule(new JavaTimeModule())
			        .build();
			
		 
			
			FeedbackDao feedbackDao = new FeedbackDao();		
			Feedback feedback  =  mapper.readValue(request.getReader(), Feedback.class);			
			feedback = feedbackDao.save(feedback);
			String json = mapper.writeValueAsString(feedback);
			
			response.getWriter().write(json);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
