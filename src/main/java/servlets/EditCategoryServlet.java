package servlets;

import Dao.CategoryDao;
import entity.Category;

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
@WebServlet(name = "EditCategoryServlet", urlPatterns = {"/EditCategoryServlet"})
public class EditCategoryServlet extends HttpServlet {

    HttpSession httpSession;
    CategoryDao categoryDao = new CategoryDao();
    int categoryId;


    /**
     Metoda pobierająca ustawiona na poziomie sesji atrybuty oraz na ich podstawie zmieniąca obiekt kategorii w bazie danych
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        String categoryName = request.getParameter("categoryName");
        String categoryDesc = request.getParameter("categoryDesc");
        categoryDao.updateCategory(categoryName, categoryDesc, categoryId);
        response.sendRedirect("categoryTable.jsp");
        httpSession = request.getSession();
        httpSession.setAttribute("message", "Zaaktualizowano rekord o id: " + categoryId);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        getParam(request, response);
    }


    /**
     Metoda pobierająca wartości z input oraz ustawiająca na poziomie sesji wartości jako atrybuty
     */
    private void getParam(HttpServletRequest request, HttpServletResponse response) throws IOException {
        categoryId = Integer.parseInt(request.getParameter("editCategoryBtn"));
        httpSession = request.getSession();
        httpSession.setAttribute("idCategory", categoryId);

        Category categoryById = categoryDao.getCategoryById(categoryId);
        String categoryTitle = categoryById.getCategoryTitle();
        String categoryDescription = categoryById.getCategoryDescription();

        httpSession.setAttribute("title", categoryTitle);
        httpSession.setAttribute("desc", categoryDescription);
        response.sendRedirect("editCategory.jsp");
        return;
    }
}
