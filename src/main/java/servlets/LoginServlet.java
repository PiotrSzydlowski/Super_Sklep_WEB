package servlets;

import Dao.UserDao;
import entity.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Piotr Szydłowski
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    UserDao userDao = new UserDao();
    String email, password;
    HttpSession httpSession;
    User user;

    /**
     * metoda ustawiająca komunikt jeśli dane logowania są błędne
     * jeśli dane logowania są poprawne ustawia na poziomie sesji użytkownika o odpowiednim typie
     * typ normal - ograniczenie widoczności panelu administratora
     * typ admin - dostęp do panelu administratora
     * jeśli typ użytkownika jest inny niż admin lub normal - brak możliowści zalogowania
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!validateLogin(request, response)) {
            httpSession = request.getSession();
            httpSession.setAttribute("validateForm", "Hasło lub login są niepoprawne, spróbuj ponownie");
            response.sendRedirect("login.jsp");
        } else {
            httpSession = request.getSession();
            httpSession.setAttribute("current-user", user);
            if (user.getUserType().equals("admin")) {
                response.sendRedirect("admin.jsp");
            } else if (user.getUserType().equals("normal")) {
                response.sendRedirect("index.jsp");
            } else {
                httpSession = request.getSession();
                httpSession.setAttribute("message", "Nie rozpoznano typu użytkownika");
                response.sendRedirect("login.jsp");
            }
        }
    }

    /**
     * Metoda pobierająca dane z formularza logowania.
     * Na podstawie danych następuje sprawdzenie czy użytkownik o podanym adresie email oraz hasle występuje
     *
     * @return false jesli nie istnieje i true jesli istnieje
     */
    private boolean validateLogin(HttpServletRequest request, HttpServletResponse response) {
        email = request.getParameter("username");
        password = request.getParameter("password");
        user = userDao.getUserByEmailAndPassword(email, password);
        return user != null;
    }
}
