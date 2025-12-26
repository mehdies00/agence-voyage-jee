package controller;

import java.io.IOException;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Model.dao.ClientDao;
import Model.entity.Client;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "lg", urlPatterns = {"/LoginServlet","*.lg"})
public class LoginServlet extends jakarta.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final ClientDao clientDao = new ClientDao();

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
 
	
		if(path.equals("/LogIn.lg")) {
		String email=request.getParameter("email");
		String pwd=request.getParameter("pass");
		Optional<Client> clientOpt = clientDao.findByStr(email,"email") ;
		response.setContentType("text/html;charset=UTF-8");
		
		if(clientOpt.isEmpty()) {
			 request.setAttribute("errorMessage", "Email invalide");
			 request.getRequestDispatcher("/include/html/LoginForm.jsp").forward(request, response);
			 	return;
		}
		Client client = clientOpt.get();
		if(!client.getPassword().equals(pwd)){
			 request.setAttribute("errorMessage", "Mot de passe invalide");
			 request.getRequestDispatcher("/include/html/LoginForm.jsp").forward(request, response);
				return;
		}else {
  		response.sendRedirect("./include/html/index.jsp");

		return;}

	}else if(path.equals("/SignIn.lg")){
		String username=request.getParameter("username");
		String email=request.getParameter("email");
		String pwd=request.getParameter("pass");
		Client user = new Client(username,email,pwd);
		if(username.isEmpty() || email.isEmpty() || pwd.isEmpty()) {
			 request.setAttribute("errorMessage", "Veuillez saisir des données correctes ");
			 request.getRequestDispatcher("/include/html/SignInForm.jsp").forward(request, response);
		}
		clientDao.save(user);
 		response.sendRedirect("./include/html/LoginForm.jsp");
 		
	}
	}

}
