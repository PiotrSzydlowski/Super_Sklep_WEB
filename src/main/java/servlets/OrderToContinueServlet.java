package servlets;

import Dao.OrderDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "OrderToContinueServlet", urlPatterns = {"/OrderToContinueServlet"})
public class OrderToContinueServlet extends HttpServlet {

    /**
     *Metoda pobierająca z tabeli numer zamówienia i całkowitą cenę zamówienia
     * i ustawiająca je jako parametry sesji
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        int orderNumber = Integer.parseInt(request.getParameter("orderNumber"));
        HttpSession session = request.getSession();
        session.setAttribute("orderNumber", orderNumber);
        OrderDao orderDao = new OrderDao();
        double totalPriceByOrderNumber = orderDao.getTotalPriceByOrderNumber(orderNumber);
        session.setAttribute("totalPrice", totalPriceByOrderNumber);
        response.sendRedirect("payment.jsp");
    }
}
