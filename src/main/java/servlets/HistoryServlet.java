package servlets;

import Dao.OrderDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 @author Piotr Szydłowski
 */
@WebServlet(name = "HistoryServlet", urlPatterns = {"/HistoryServlet"})
public class HistoryServlet extends HttpServlet {

    OrderDao orderDao = new OrderDao();
    HttpSession httpSession;

    /**
    Metoda pobierająca numer zamówienia jako parametr
     na jego podstawie usuwane jest obiekt zamówienia
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int cancelOrder = Integer.parseInt(request.getParameter("cancelOrder"));
        orderDao.deleteOrder(cancelOrder);
        httpSession = request.getSession();
        httpSession.setAttribute("message", "Zamówienie o numerze " + cancelOrder + " zostało usunięte");
        response.sendRedirect("history.jsp");
    }
}
