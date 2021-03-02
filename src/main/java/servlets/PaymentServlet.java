package servlets;

import Dao.OrderDao;
import Dao.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;

/**
 * @author Piotr Szydłowski
 */
@WebServlet(name = "PaymentServlet", urlPatterns = {"/PaymentServlet"})
public class PaymentServlet extends HttpServlet {

    OrderDao orderDao = new OrderDao();
    ProductDao productDao = new ProductDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("paymentOperation");
        if (operation.trim().equals("cancel")) {
            cancelOrder(request);
        } else if (operation.trim().equals("accept")) {
            acceptOrder(request);
        }
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            c.setMaxAge(0);
            response.addCookie(c);
        }
        response.sendRedirect("index.jsp");
    }

    /**
     Metoda zmieniajaca status zamówienia na opłacony(symulacja opłacenia zamówienia)
     */
    private void acceptOrder(HttpServletRequest request) {
        int acceptOrder = Integer.parseInt(request.getParameter("acceptOrder"));
        orderDao.updateOrder(acceptOrder);
    }

    /**
     * Metoda usuwająca zamówienie z bazy danych
     */
    private void cancelOrder(HttpServletRequest request) {
        int cancelOrder = Integer.parseInt(request.getParameter("cancelOrder"));
        orderDao.deleteOrder(cancelOrder);
        getOrderAttribute(request);
    }

    private void getOrderAttribute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int productId1 = (int) session.getAttribute("productId1");
        int productQnt1 = (int) session.getAttribute("productQnt1");
        int productId2 = (int) session.getAttribute("productId2");
        int productQnt2 = (int) session.getAttribute("productQnt2");
        int productId3 = (int) session.getAttribute("productId3");
        int productQnt3 = (int) session.getAttribute("productQnt3");
        productDao.updateQuantity(productId1, (productDao.getQuantity(productId1) + productQnt1));
        if (productQnt2 > 0)
            productDao.updateQuantity(productId2, (productDao.getQuantity(productId2) + productQnt2));
        if (productQnt3 > 0)
            productDao.updateQuantity(productId3, (productDao.getQuantity(productId3) + productQnt3));
    }
}
