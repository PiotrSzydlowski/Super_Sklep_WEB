package servlets;

import Dao.UserDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ChangePasswordServlet", urlPatterns = {"/ChangePasswordServlet"})
public class ChangePasswordServlet extends HttpServlet {

    HttpSession session;
    String currentPass, newPass, repeatNewPass;
    int userID;
    User currentUser;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        currentUser = (User) session.getAttribute("current-user");
        getParametr(request, currentUser);
        changePassword(request, response);
    }

    /**
     * Metoda pobierająca paramertry z sesji
     * @param currentUser - użytkownik obecnie zalogowany do aplikacji
     */
    private void getParametr(HttpServletRequest request, User currentUser) {
        userID = currentUser.getUserID();
        currentPass = request.getParameter("currentPass");
        newPass = request.getParameter("newPass");
        repeatNewPass = request.getParameter("repeatNewPass");
    }

    /**
     * Metoda zmieniająca hasło w BD
     */
    public void changePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (validCurrentPassword() && validatePatterPassword() && validNewPassword()) {
            UserDao userDao = new UserDao();
            userDao.updateUserPassword(newPass, userID);
            session = request.getSession();
            session.setAttribute("message", "zmieniono hasło");
        } else if (!validCurrentPassword()) {
            session = request.getSession();
            session.setAttribute("message", "Nie odnaleziono użytkownika o takim haśle");
        } else if (!validNewPassword()) {
            session = request.getSession();
            session.setAttribute("message", "Hasła nie są jednakowe");
        } else if (!validatePatterPassword()) {
            session = request.getSession();
            session.setAttribute("message", "Hasło powinno zawierać przynajmniej 8 znakow, dużą literę oraz znak specjalny");
        }
        response.sendRedirect("userPassword.jsp");
    }

    /**
     * Metoda sprawdzająca czy hasło podane przez użytkownika istnieje w DB
     * @return true jeśli istnieje, false jeśli nie istnieje
     */
    public boolean validCurrentPassword() {
        return currentUser.getUserPassword().equals(currentPass);
    }

    /**
     * Metoda sprawdzająca czy podane hasło i jego powtórzenie jest identyczne
     * @return tru jeśli tak, false jeśli nie
     */
    public boolean validNewPassword() {
        return newPass.equals(repeatNewPass);
    }

    /**
     * Metoda czy nowe hasło wprowadzone przez użytkownika spełnia zasady paternu
     * @return true jesli spełnia, false jeśli nie
     */
    public boolean validatePatterPassword() {
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+!=])(?=\\S+$).{8,}";
        return newPass.matches(pattern);
    }
}
