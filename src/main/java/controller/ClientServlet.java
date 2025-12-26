package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

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
@WebServlet(name="cl", urlPatterns = { "/ClientServlet","*.cl"})

public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final ClientDao  clientDao = new  ClientDao();
	private static final ObjectMapper  mapper = JsonMapper.builder()
	        .addModule(new JavaTimeModule())
	        .build();
    /**
     * Default constructor. 
     */
    public ClientServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		if(path.equals("/GET.cl")) {
			Collection< Client>  clients  = clientDao.findAll();
			mapper.writeValue( response.getWriter(), clients);
		}
		else if(path.equals("/GET_ID.cl")) {
			    
				String idParam = request.getParameter("id");
				if(idParam == null) {
					response.setStatus(400);
					return;
				}
			    int idUser = Integer.parseInt(idParam);
  			    Optional<Client> clientOpt = clientDao.findById(idUser);
  			    if(clientOpt.isEmpty()) {
  			    mapper.writeValue(response.getWriter(), null);
  			     
  			    }else {
  			    	Client client = clientOpt.get();
  	  			    mapper.writeValue(response.getWriter(), client);
  			    }
 		}else if(path.equals("/POST.cl")) {
 			Client client = mapper.readValue(request.getReader(), Client.class);
			client = clientDao.save(client);
			mapper.writeValue(response.getWriter(), client);
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
