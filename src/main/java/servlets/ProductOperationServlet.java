package servlets;

import Dao.CategoryDao;
import Dao.ProductDao;
import entity.Category;
import entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

/**
 * @author Piotr Szydłowski
 */
@WebServlet(name = "ProductOperationServlet", urlPatterns = {"/ProductOperationServlet"})
@MultipartConfig
public class ProductOperationServlet extends HttpServlet {

    CategoryDao categoryDao = new CategoryDao();
    ProductDao productDao = new ProductDao();
    HttpSession session;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            getCategoryAndProduct(request, response);
        } catch (NumberFormatException e) {
            session = request.getSession();
            session.setAttribute("message", "Błędny format ceny");
            response.sendRedirect("admin.jsp");
        } catch (FileNotFoundException e) {
            session = request.getSession();
            session.setAttribute("message", "Dodano produkt bez pliku .img");
            response.sendRedirect("admin.jsp");
        }
    }

    /**
     Metoda na podstawie wartości parametru operation:
     - pobiera dane z formularza i dodaje nową kategorię(addCategory)
     - Tworzy na podstawie pobranych parametrów z formularza obiekt Product (addproduct)
     */
    public void getCategoryAndProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operation = request.getParameter("operation");
        if (operation.trim().equals("addcategory")) {
            String title = request.getParameter("catTitle");
            String description = request.getParameter("catDescription");
            createCategory(request, response, title, description);
        } else if (operation.trim().equals("addproduct")) {
            String pName = request.getParameter("pName");
            String pDesc = request.getParameter("pDesc");
            double pPrice = Double.parseDouble(request.getParameter("pPrice"));
            double pDiscount = Double.parseDouble(request.getParameter("pDiscount"));
            int pQuantity = Integer.parseInt(request.getParameter("pQuantity"));
            int catId = Integer.parseInt(request.getParameter("catId"));
            Part part = request.getPart("pPic");
            createProduct(pName, pDesc, pPrice, pDiscount, pQuantity, catId, part, response, request);
        }
    }

    /**
     * Metoda tworząca obiekt produktu i zapisująca go do DB
     * @param pName - nazwa produktu
     * @param pDesc - opis produktu
     * @param pPrice - cena produktu
     * @param pDiscount - obniżka ceny produktu
     * @param pQuantity - ilość produktu
     * @param catId - id kategorii, do której należy produkt
     * parametry pochodzą z formularza
     */
    private void createProduct(String pName, String pDesc, double pPrice, double pDiscount, int pQuantity, int catId,
                               Part part, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Product product = new Product();
        product.setProducttitle(pName);
        product.setProductdescrption(pDesc);
        product.setProductprice(pPrice);
        product.setProductdiscount(pDiscount);
        product.setProductquantity(pQuantity);
        product.setProductphoto(part.getSubmittedFileName());
        getCategoryById(catId, product);
        productDao.saveProduct(product);
        uploadReadWriteFile(part, request);
        session = request.getSession();
        session.setAttribute("message", "Dodano produkt");
        response.sendRedirect("admin.jsp");
    }

    /**
     * Metoda odczytująca i zapisująca dane w postaci plików png
     * @throws IOException
     */
    private void uploadReadWriteFile(Part part, HttpServletRequest request) throws IOException {
        String path = request.getRealPath("img") + File.separator + "products" + File.separator + part.getSubmittedFileName();
        //upload data
        FileOutputStream fos = new FileOutputStream(path);
        InputStream inputStream = part.getInputStream();
        //read data
        byte[] data = new byte[inputStream.available()];
        inputStream.read(data);
        // write the data
        fos.write(data);
        fos.close();
    }

    /**
     * Metoda pobierająca na podstawie parametru
     * @param catId wybraną przez użytkownika w formularzu kategorię
     * i ustawiająca dla parametru
     * @param product wybraną kategorię
     */
    private void getCategoryById(int catId, Product product) {
        Category categoryById = categoryDao.getCategoryById(catId);
        product.setCategory(categoryById);
    }

    /**
     Metoda tworząca obiekt kategorii i zapisująca go do DB oraz wyświetlająca komunikat o poprawnym dodaniu kategorii
     */
    private void createCategory(HttpServletRequest request, HttpServletResponse response, String title, String description)
            throws IOException {
        Category category = new Category();
        category.setCategoryTitle(title);
        category.setCategoryDescription(description);
        int catId = categoryDao.saveCategory(category);
        session = request.getSession();
        session.setAttribute("message", "Kategoria dodana z numerem id: " + catId);
        response.sendRedirect("admin.jsp");
    }
}
