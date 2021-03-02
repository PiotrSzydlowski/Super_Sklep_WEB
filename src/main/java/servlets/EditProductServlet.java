package servlets;

import Dao.CategoryDao;
import Dao.ProductDao;
import entity.Category;
import entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


/**
 * @author Piotr Szydłowski
 */
@WebServlet(name = "EditProductServlet", urlPatterns = {"/EditProductServlet"})
public class EditProductServlet extends HttpServlet {

    HttpSession httpSession;
    ProductDao productDao = new ProductDao();
    CategoryDao categoryDao = new CategoryDao();
    int productId;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        getParamFromProductTable(request, response);
    }


    /**
     * Metoda pobierająca parametr operationPost z inputu strony jsp
     * Na podstawie parametru następuje rozróżnienie wykonania modyfikacji produktu lub przekazania parametru
     * id produktu do zmiany ilości
     */
    private void getParamFromProductTable(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String operation = request.getParameter("operationPost");
        if (operation.trim().equals("editProductOperation")) {
            editProductPost(request, response);
        } else if (operation.trim().equals("addQuantity")) {
            String addQuantityBtn = request.getParameter("addQuantityBtn");
            httpSession = request.getSession();
            httpSession.setAttribute("productIdQuantity", addQuantityBtn);
            response.sendRedirect("addProductQuantity.jsp");
        }
    }

    /**
     * Metoda pobierająca parametr operationget z inputu strony jsp
     * Na podstawie parametru następuje rozróżnienie wykonania operacji dodania ilości lub modyfikacji produktu
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operationGet = request.getParameter("operationGet");
        if (operationGet.trim().equals("editProductOperation")) {
            editProductGet(request, response);
        } else if (operationGet.trim().equals("addProductQuantityOperation")) {
            addQuantityGet(request, response);
        }
    }

    /**
     * Metoda dodająca do istniejącego stanu produktu w bazie danych ilośc określoną w label strony jsp
     */
    public void addQuantityGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String productIdQuantity = (String) httpSession.getAttribute("productIdQuantity");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int oldQuantity = productDao.getQuantity(Integer.parseInt(productIdQuantity));
        int value = oldQuantity + quantity;
        productDao.updateQuantity(Integer.parseInt(productIdQuantity), value);
        response.sendRedirect("productsTable.jsp");
        httpSession = request.getSession();
        httpSession.setAttribute("message", "Zaktualizowano ilość dla produktu o id: " + productIdQuantity);
    }

    /**
     * Metoda pobierająca parametry opisujące produkt z label strony jsp
     * Na podstawie parametrów zmieniająca instniejący obiekt produktu w bazie danych
     */
    public void editProductGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String productName = request.getParameter("productName");
        String productDesc = request.getParameter("productDesc");
        double productPrice = Double.parseDouble(request.getParameter("productPrice"));
        double productDiscount = Double.parseDouble(request.getParameter("productDiscount"));
        int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
        String catId = request.getParameter("catId");

        Category categoryById = createCategoryById(catId);
        productDao.updateProduct(productName, productDesc, productPrice, productDiscount, productQuantity, categoryById, productId);

        response.sendRedirect("productsTable.jsp");
        httpSession = request.getSession();
        httpSession.setAttribute("message", "Zaaktualizowano rekord o id: " + productId);
    }

    /**
     * Metoda pobiera kategorię na podstawie id kategorii zapisanej w bazie danych.
     * id kategorii przekazywane w inpucie strony jsp
     *
     * @param catId
     */
    private Category createCategoryById(String catId) {
        return categoryDao.getCategoryById(Integer.parseInt(catId));
    }


    /**
     * Na podstawie parametru odczytanego z inputa pobierany jest obiekt product.
     * Pobierana jest lista kategorii.
     * atrybuty produktu zapisywane są na poziomie sesji
     * przekierwanie na stronę prezentującą lalele do zmiany produktu
     */
    private void editProductPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        productId = Integer.parseInt(request.getParameter("editProductBtn"));
        httpSession = request.getSession();
        httpSession.setAttribute("idProduct", productId);

        Product productById = productDao.getProductById(productId);
        CategoryDao categoryDao = new CategoryDao();
        List<Category> allCategory = categoryDao.getAllCategory();

        httpSession.setAttribute("pTitle", productById.getProducttitle());
        httpSession.setAttribute("pDesc", productById.getProductdescrption());
        httpSession.setAttribute("pPrice", productById.getProductprice());
        httpSession.setAttribute("pDiscount", productById.getProductdiscount());
        httpSession.setAttribute("pQuantity", productById.getProductquantity());
        httpSession.setAttribute("pCategory", allCategory);

        response.sendRedirect("editProduct.jsp");
    }
}
