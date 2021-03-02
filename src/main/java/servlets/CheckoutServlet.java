package servlets;

import Dao.OrderDao;
import Dao.ProductDao;
import Dao.UserDao;
import entity.Order;
import entity.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "CheckoutServlet", urlPatterns = {"/CheckoutServlet"})
public class CheckoutServlet extends HttpServlet {

    int productId1, productQnt1, productId2, productQnt2, productId3, productQnt3, userId, orderNumber, totalQnt;
    Order order1, order2, order3;
    OrderDao orderDao = new OrderDao();
    double totalPrice;
    User user;
    HttpSession session;
    ProductDao productDao = new ProductDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userId = Integer.parseInt(request.getParameter("checkout"));
        getCookies(request);
        orderNumber = orderDao.getOrderNumber() + 1;
        createOrders();
        getCurrentUser(request);
        response.sendRedirect("payment.jsp");
        setOrderAttribute();
    }

    private void getCurrentUser(HttpServletRequest request) {
        session = request.getSession();
        user = (User) session.getAttribute("current-user");
        int userID = user.getUserID();

        String user_email = request.getParameter("user_email");
        String user_name = request.getParameter("user_name");
        String user_phone = request.getParameter("user_phone");
        String user_address = request.getParameter("user_address");

        UserDao userDao = new UserDao();
        userDao.updateUser(user_name, user_email, user_phone, user_address, userID);
    }

    public void createOrders() {
        if (productId1 > 0 && productQnt1 > 0) {
            order1 = new Order(userId, productId1, totalPrice, "zamówione", orderNumber, productQnt1, 0);
            orderDao.saveOrder(order1);
            productDao.updateQuantity(productId1, (productDao.getQuantity(productId1) - productQnt1));
        }
        if (productQnt2 > 0 && productId2 > 0) {
            order2 = new Order(userId, productId2, totalPrice, "zamówione", orderNumber, productQnt2, 0);
            orderDao.saveOrder(order2);
            productDao.updateQuantity(productId2, (productDao.getQuantity(productId2) - productQnt2));
        }
        if (productQnt3 > 0 && productId3 > 0) {
            order3 = new Order(userId, productId3, totalPrice, "zamówione", orderNumber, productQnt3, 0);
            orderDao.saveOrder(order3);
            productDao.updateQuantity(productId3, (productDao.getQuantity(productId3) - productQnt3));
        }
    }

    private void getCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if ("productId1".equals(c.getName()))
                productId1 = Integer.parseInt(c.getValue());
            if ("productQnt1".equals(c.getName()))
                productQnt1 = Integer.parseInt(c.getValue());
            if ("productId2".equals(c.getName()))
                try {
                    productId2 = Integer.parseInt(c.getValue());
                } catch (NumberFormatException e) {
                    productId2 = 0;
                }
            if ("productQnt2".equals(c.getName()))
                try {
                    productQnt2 = Integer.parseInt(c.getValue());
                } catch (NumberFormatException e) {
                    productQnt2 = 0;
                }
            if ("productId3".equals(c.getName()))
                try {
                    productId3 = Integer.parseInt(c.getValue());
                } catch (NumberFormatException e) {
                    productId3 = 0;
                }
            if ("productQnt3".equals(c.getName()))
                try {
                    productQnt3 = Integer.parseInt(c.getValue());
                } catch (NumberFormatException e) {
                    productQnt3 = 0;
                }
            if ("totalPrice".equals(c.getName()))
                totalPrice = Double.parseDouble(c.getValue());
        }
    }

    private void setOrderAttribute() {
        session.setAttribute("orderNumber", orderNumber);
        session.setAttribute("totalPrice", totalPrice);
        session.setAttribute("productId1", productId1);
        session.setAttribute("productQnt1", productQnt1);
        session.setAttribute("productId2", productId2);
        session.setAttribute("productQnt2", productQnt2);
        session.setAttribute("productId3", productId3);
        session.setAttribute("productQnt3", productQnt3);
    }
}
