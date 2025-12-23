package controller;

import java.io.IOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
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
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
	
		if(path.equals("/LogIn.lg")) {
		String email=request.getParameter("email");
		String pwd=request.getParameter("pass");
		Client client = (Client) session.createQuery("from Client where email = :email and password = :pwd ", Client.class)
				.setParameter("email", email)
				.setParameter("pwd", pwd)
				.getSingleResultOrNull();
		response.setContentType("text/html;charset=UTF-8");
		if(client != null) {
			//getServletContext().getRequestDispatcher("/include/html/index.jsp").forward(request, response);
			response.sendRedirect("./include/html/index.jsp");

		}
		else {
			//getServletContext().getRequestDispatcher("/include/html/LoginForm.jsp").forward(request, response);
			response.sendRedirect("./include/html/LoginForm.jsp");

		}
		session.close();
		sessionFactory.close();
	}else if(path.equals("/SignIn.lg")){
		String username=request.getParameter("username");
		String email=request.getParameter("email");
		String pwd=request.getParameter("pass");
		Client user = new Client(username,email,pwd);
    	session.getTransaction().begin();
    	session.persist(user);
    	session.getTransaction().commit();
    	session.close();
    	sessionFactory.close();
    	//getServletContext().getRequestDispatcher("./include/html/index.jsp").forward(request, response);
		response.sendRedirect("./include/html/index.jsp");

	}
	}

}
