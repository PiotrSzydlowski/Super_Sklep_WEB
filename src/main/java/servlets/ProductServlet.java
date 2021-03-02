package servlets;

import Dao.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Piotr Szydłowski
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/ProductServlet"})
public class ProductServlet extends HttpServlet {

    ProductDao productDao = new ProductDao();

    /**
     * Metoda pobiera id z z value button oraz na tej podstawie usuwa produkt z DB
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String deleteProductBtn = request.getParameter("deleteProductBtn");
        deleteProduct(request, response, deleteProductBtn);
    }

    /**
     * Metoda usuwająca produkt z DB
     */
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response, String deleteProductBtn) throws IOException {
        productDao.deleteProduct(Integer.parseInt(deleteProductBtn));
        response.sendRedirect("productsTable.jsp");
    }
}
