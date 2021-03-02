package servlets;

import Dao.SaleDao;
import entity.Sale;
import org.hibernate.exception.DataException;

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

@WebServlet(name = "PromotionServlet", urlPatterns = {"/PromotionServlet"})
public class PromotionServlet extends HttpServlet {

    SaleDao saleDao = new SaleDao();

    /**
     * Metoda pobierająca dane z formularza oraz zapisująca do bazy danych promocję
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String promotionCode = request.getParameter("promotionCode");
        double value = Double.parseDouble(request.getParameter("value"));
        double minPrice = Double.parseDouble(request.getParameter("minPrice"));
        String startDay = request.getParameter("startDay");
        String startMoth = request.getParameter("startMoth");
        String startYear = request.getParameter("startYear");
        String endDay = request.getParameter("endDay");
        String endMoth = request.getParameter("endMoth");
        String endYear = request.getParameter("endYear");
        String dateFrom = startYear + "-" + startMoth + "-" + startDay;
        String dateTo = endYear + "-" + endMoth + "-" + endDay;

        Sale sale = createSale(promotionCode, value, minPrice, dateFrom, dateTo);
        try {
            saleDao.saveSale(sale);
            HttpSession session = request.getSession();
            session.setAttribute("message", "Dodano promocję");
        }catch (DataException e){
            HttpSession session = request.getSession();
            session.setAttribute("message", "Wprowadzono błędną datę");
        }
        response.sendRedirect("admin.jsp");
    }

    /**
     * Metoda tworząca obiekt promocji
     * @return - obiekt promocji
     */
    private Sale createSale(String promotionCode, double value, double minPrice, String startDate, String endDate) {
        Sale sale = new Sale();
        sale.setCode(promotionCode);
        sale.setDatafrom(startDate);
        sale.setDatato(endDate);
        sale.setValue(value);
        sale.setPrice(minPrice);
        return sale;
    }
}
