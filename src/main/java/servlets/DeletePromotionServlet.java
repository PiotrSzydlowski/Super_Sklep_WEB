package servlets;

import Dao.SaleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DeletePromotionServlet", urlPatterns = {"/DeletePromotionServlet"})
public class DeletePromotionServlet extends HttpServlet {

    /**
     *Metoda Metoda usuwająca promocję. Po pobraniu id promocji wywoływana jest metoda z klasy OrderDao, która usuwa promocję
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int deletePromotion = Integer.parseInt(request.getParameter("deletePromotion"));
        SaleDao saleDao = new SaleDao();
        saleDao.deletePromotion(deletePromotion);
        HttpSession session = request.getSession();
        session.setAttribute("message", "Usunięto promocję");
        response.sendRedirect("admin.jsp");
    }
}
