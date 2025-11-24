package controller;

import java.io.IOException;

import org.eclipse.model.Personne;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Model.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


 @WebServlet("/UserRegSeverlet")
public class UserRegSeverlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
    public UserRegSeverlet() {
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username=request.getParameter("username");
		String email=request.getParameter("email");
		String phone=request.getParameter("phonenumber");
		String gender=request.getParameter("genre");
		String pwd=request.getParameter("pass");
		Utilisateur user = new Utilisateur(username,email,pwd,phone,gender);
    	Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
    	SessionFactory  sessionFactory = configuration.buildSessionFactory();
    	Session	session = sessionFactory.openSession();
    	session.getTransaction().begin();
    	session.persist(user);
    	session.getTransaction().commit();
    	session.close();
    	sessionFactory.close();
		System.out.println(user.getId());
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println("<h1>Confirmation d'inscription</h1>");
		
		response.getWriter().println("<p>Nom d'utilisateur reçu : <strong>" + username + "</strong></p>");
		
	}

}
