package servlets;

import Dao.AvabilityDao;
import entity.Avability;
import org.hibernate.engine.transaction.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;

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

@WebServlet(name = "AvabilityServlet", urlPatterns = {"/AvabilityServlet"})
public class AvabilityServlet extends HttpServlet {

    AvabilityDao avabilityDao = new AvabilityDao();
    HttpSession httpSession;

    /**
     * Metoda ustawia dostępność avability dla tzrech kategorii: dużej, średniej, małęj
     * na podstawie parametru pobranego z inputa name= avabilityOperation.
     * W zależności od value inputa zmieniana jest dostępność
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String avabilityOperation = request.getParameter("avabilityOperation");
        int valueMiddle = 0;
        int valueHight = 0;

        if (avabilityOperation.trim().equals("avability-hight")) {
            try {
                valueHight = Integer.parseInt(request.getParameter("valueHight"));
            } catch (NumberFormatException e) {
                Avability avability = avabilityDao.getAvability(3);
                avability.setAvabilityvalue(avability.getAvabilityvalue());
            }
            int idHight = Integer.parseInt(request.getParameter("idHight"));
            avabilityDao.updateAvability(idHight, valueHight);
            responseMessage(request, response);
        } else if (avabilityOperation.trim().equals("avability-middle")) {
            try {
                valueMiddle = Integer.parseInt(request.getParameter("valueMiddle"));
            } catch (NumberFormatException e) {
                Avability avability = avabilityDao.getAvability(2);
                avability.setAvabilityvalue(avability.getAvabilityvalue());
            }
            int idMiddle = Integer.parseInt(request.getParameter("idMiddle"));
            avabilityDao.updateAvability(idMiddle, valueMiddle);
            responseMessage(request, response);
        } else if (avabilityOperation.trim().equals("avability-low")) {
            int valueLow = Integer.parseInt(request.getParameter("valueLow"));
            int idLow = Integer.parseInt(request.getParameter("idLow"));
            avabilityDao.updateAvability(idLow, valueLow);
            responseMessage(request, response);
        }
    }

    /**
     * Metoda zwracająca komunikat użytkownikowi po aktualizacji poziomów dostępności
     */
    private void responseMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("productsTable.jsp");
        httpSession = request.getSession();
        httpSession.setAttribute("message", "Zaktualizowano Dostępność");
    }
}
