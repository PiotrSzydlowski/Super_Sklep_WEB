package servlets;

import Dao.CategoryDao;

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

@WebServlet(name = "CategoryServlet", urlPatterns = {"/CategoryServlet"})
public class CategoryServlet extends HttpServlet {

    CategoryDao categoryDao = new CategoryDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        String deleteCategoryBtn = request.getParameter("deleteCategoryBtn");
        deleteCategory(request, response, deleteCategoryBtn);
    }

    /**
     *Metoda usuwająca obiekt kategorii z bazy danych
     * W momencie natrafienia na powiązanie kluczy obcych pojawia się komunikat.
     * Po poprawnym usunięciu kategorii następuje przekierowanie na stronę categoryTable
     * @param deleteCategoryBtn
     * @throws IOException
     */
    private void deleteCategory(HttpServletRequest request, HttpServletResponse response, String deleteCategoryBtn) throws IOException {
        try {
            categoryDao.deleteCategory(Integer.parseInt(deleteCategoryBtn));
        } catch (Exception e) {
            String message = e.getMessage();
            HttpSession httpSession = request.getSession();
            if (message.equals("could not execute statement")) {
                httpSession.setAttribute("message", "Brak możliwości usunięcia rekordu. Kategoria przypisana do co najmniej jednego produktu");
            }
        }
        response.sendRedirect("categoryTable.jsp");
    }
}
