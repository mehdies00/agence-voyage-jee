package controller;

import java.io.IOException;
import java.util.Optional;
import Model.dao.ClientDao;
import Model.entity.Client;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "lg", urlPatterns = {"/LoginServlet", "*.lg"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final ClientDao clientDao = new ClientDao();

    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Crucial : redirige le GET vers le POST pour que window.location.href fonctionne
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        response.setContentType("text/html;charset=UTF-8");

        // CAS 1 : CONNEXION CLASSIQUE
        if (path.equals("/LogIn.lg")) {
            String email = request.getParameter("email");
            String pwd = request.getParameter("pass");
            Optional<Client> clientOpt = clientDao.findByStr(email, "email");

            if (clientOpt.isEmpty()) {
                request.setAttribute("errorMessage", "Email invalide");
                request.getRequestDispatcher("/include/html/LoginForm.jsp").forward(request, response);
                return;
            }

            Client client = clientOpt.get();
            if (!client.getPassword().equals(pwd)) {
                request.setAttribute("errorMessage", "Mot de passe invalide");
                request.getRequestDispatcher("/include/html/LoginForm.jsp").forward(request, response);
                return;
            } else {
                // MISE EN SESSION (Indispensable pour le Header)
                request.getSession().setAttribute("user", client);
                response.sendRedirect(request.getContextPath() + "/include/html/index.jsp");
                return;
            }
        } 
        
        // CAS 2 : INSCRIPTION
        else if (path.equals("/SignIn.lg")) {
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String pwd = request.getParameter("pass");

            if (username == null || username.isEmpty() || email == null || email.isEmpty()) {
                request.setAttribute("errorMessage", "Veuillez saisir des données correctes");
                request.getRequestDispatcher("/include/html/SignInForm.jsp").forward(request, response);
                return;
            }

            Client user = new Client(username, email, pwd);
            clientDao.save(user);
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/include/html/LoginForm.jsp");
        } 
        
        // CAS 3 : BOUTONS SOCIAUX (ID=1)
        else if (path.contains("SocialLogin.lg")) {
            Optional<Client> clientOpt = clientDao.findById(1);

            if (clientOpt.isPresent()) {
                Client adminUser = clientOpt.get();
                request.getSession().setAttribute("user", adminUser);
                response.sendRedirect(request.getContextPath() + "/include/html/index.jsp");
            } else {
                request.setAttribute("errorMessage", "Utilisateur test (ID 1) non trouvé en base.");
                request.getRequestDispatcher("/include/html/LoginForm.jsp").forward(request, response);
            }
            return;
        }
     // CAS 4 : DÉCONNEXION
        else if (path.equals("/Logout.lg")) {
            // 1. Invalide la session (supprime l'objet "user" et toutes les données)
            request.getSession().invalidate();
            
            // 2. Redirige vers la page d'accueil
            // Le header détectera que 'user' est null et affichera Login/SignUp
            response.sendRedirect(request.getContextPath() + "/include/html/index.jsp");
            return;
        }
    }
}