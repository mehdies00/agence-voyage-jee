package controller;

import java.io.BufferedReader;
import java.io.IOException;
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
		if(path.equals("/GET.cl")) {
			 ClientDao  clientDao = new  ClientDao();
			Collection< Client>  clients  = clientDao.findAll();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			ObjectMapper  mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(clients);
			response.getWriter().write(json);
		}
		else if(path.equals("/GET_ID.cl")) {
			    String idParam = request.getParameter("id");
			    int idUser = Integer.parseInt(idParam);
			    
			    ClientDao clientDao = new ClientDao();
			    Optional<Client> client = clientDao.findById(idUser);
			    
			    ObjectMapper mapper = new ObjectMapper();
			    String jsonClient = mapper.writeValueAsString(client.orElse(null));
			    
			    response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(jsonClient);
			}else if(path.equals("/POST.cl")) {
			ObjectMapper  mapper = JsonMapper.builder()
			        .addModule(new JavaTimeModule())
			        .build();
			
			ClientDao clientDao = new ClientDao();
			Client client = mapper.readValue(request.getReader(), Client.class);
			client = clientDao.save(client);
			String json = mapper.writeValueAsString(client);
			
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
