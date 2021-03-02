package servlets;

import Dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;
import java.io.IOException;


/**
 * @author Piotr Szydłowski
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

    HttpSession httpSession;

    /**
     *Metoda usuwająca użytkownika z bazy danych.
     * Jeśli uśytkownik ma co najmniej jedno zamówienie to wyświetlany jest komunikat
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int deleteUserBtn = Integer.parseInt(request.getParameter("deleteUserBtn"));
        UserDao userDao = new UserDao();
        try {
            userDao.deleteUser(deleteUserBtn);
            httpSession = request.getSession();
            httpSession.setAttribute("message", "Usunięto użytkownika o id " + deleteUserBtn);
        } catch (Exception e) {
            String message = e.getMessage();
            HttpSession httpSession = request.getSession();
            if (message.equals("could not execute statement")) {
                httpSession.setAttribute("message", "Brak możliwości usunięcia użytkownika, co najmniej jedno zamówienie przypisane");
            }
        }
        response.sendRedirect("userTable.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int getHistory = Integer.parseInt(request.getParameter("getHistory"));
        httpSession = request.getSession();
        httpSession.setAttribute("getHistoryByUserId", getHistory);
        response.sendRedirect("history.jsp");
    }
}
