package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Piotr Szydłowski
 */
@WebServlet(name = "LogoutServlet",urlPatterns = {"/LogoutServlet"})
public class LogoutServlet extends HttpServlet {

    /**
     Podczas wylogowania użytkownika usuwa z sesji atrybut current-user służący do identyfikacji typu użytkownika
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        httpSession.removeAttribute("current-user");
        response.sendRedirect("login.jsp");
    }
}
