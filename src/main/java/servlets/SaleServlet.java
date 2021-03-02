package servlets;

import Dao.OrderDao;
import Dao.SaleDao;
import entity.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


/**
 * @author Piotr Szydłowski
 */
@WebServlet(name = "SaleServlet", urlPatterns = {"/SaleServlet"})
public class SaleServlet extends HttpServlet {

    SaleDao saleDao = new SaleDao();
    OrderDao orderDao = new OrderDao();
    HttpSession session;
    Date currentDate = new Date();


    /**
     * Metoda pobierąjąca z formularza przkazany przez użytkwnika kod promocji
     * Na podstawie kodu promocji sprawdzanie czy istnieje kod w bazie danych
     * Sprawdzanie czy wykorzystanie kodu mieści się w ramach czasowych zapisanych w bazie
     * Sprawdzanie czy osiągnięta została minimalna wartośc zamówienia
     * Jesli warunki spełnione wsatwianie znacznika 1 do tabeli orders
     * przelicznie ceny
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String code = request.getParameter("code");
        int orderNumber = Integer.parseInt(request.getParameter("realizeCode"));
        int coupon = orderDao.getCoupon(orderNumber);
        String formatCurrentDate = null, dataFrom = null, dateTo = null;

        try {
            List<String> list = saleDao.getCode(code);
            String codeFromDB = list.get(0);
            System.out.println(codeFromDB + " CODE FROM DB");
            if (codeFromDB != null) {
                double moreThanPrice = saleDao.getMoreThanPrice(codeFromDB);
                if (codeFromDB.equals(code)) {
                    if (coupon == 0) {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        formatCurrentDate = formatter.format(currentDate);
                        dataFrom = saleDao.getDateFrom(codeFromDB);
                        dateTo = saleDao.getDateTo(codeFromDB);
                        if (formatCurrentDate.compareTo(dataFrom) >= 0) {
                            if (formatCurrentDate.compareTo(dateTo) <= 0) {
                                double value = saleDao.getValue(code);
                                double totalPrice = orderDao.getTotalPriceByOrderNumber(orderNumber);
                                if (totalPrice > moreThanPrice) {
                                    double finalPriceAfterDiscount = totalPrice - value;
                                    session = request.getSession();
                                    session.setAttribute("totalPrice", finalPriceAfterDiscount);
                                    orderDao.updateCoupon(orderNumber);
                                    orderDao.updateTotalPriceAfterUseCoupon(orderNumber, finalPriceAfterDiscount);
                                } else if (totalPrice < moreThanPrice) {
                                    HttpSession session = request.getSession();
                                    session.setAttribute("message", "Zbyt niska kwota zamówienia");
                                }
                            }
                        } else if (formatCurrentDate.compareTo(dateTo) >= 0 || formatCurrentDate.compareTo(dataFrom) <= 0) {
                            HttpSession session = request.getSession();
                            session.setAttribute("message", "Kupon nieaktywny");
                        }
                    } else if (coupon == 1) {
                        session.setAttribute("message", "Kupon już wykorzystany");
                    }
                }
            }
        } catch (
                IndexOutOfBoundsException e) {
            HttpSession session = request.getSession();
            session.setAttribute("message", "Brak aktywnego Kuponu");
        }
        response.sendRedirect("payment.jsp");
    }
}
