package Dao;

import entity.Category;
import entity.Product;
import helper.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Piotr Szydłowski
 */

public class ProductDao {

    /**
     * Metoda zapisująca obiekt product do bazy danych.
     *
     * @param product obiekt produktu z metody
     * @see servlets.ProductOperationServlet#getCategoryAndProduct(HttpServletRequest, HttpServletResponse)
     */
    public void saveProduct(Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(product);
        transaction.commit();
        session.close();
    }

    /**
     * Metoda zwraca Listę produktów z bazy danych.
     */
    public List<Product> getAllProducts() {
        Session session = getSession();
        Query query = session.createQuery("from Product");
        List<Product> list = query.list();
        session.close();
        return list;
    }

    /**
     * Metoda zwraca listę produktów z bazy danych na podstawie kategorii.
     *
     * @param catId parametr kategorii produktu
     */
    public List<Product> getAllProductsById(int catId) {
        Session session = getSession();
        Query query = session.createQuery("from Product as p where p.category.categoryId =:id");
        query.setParameter("id", catId);
        List<Product> list = query.list();
        session.close();
        return list;
    }

    /**
     * Metoda zwraca liczbę wystąpień produktu na podstawie określonej kategorii.
     *
     * @param catId kategoria produktu
     */
    public long getProductFromCategory(int catId) {
        Session session = getSession();
        Query query = session.createQuery("select count(*) from Product as p where p.category.categoryId =:catId");
        query.setParameter("catId", catId);
        long result = (long) query.list().get(0);
        session.close();
        return result;
    }

    /**
     * Metoda usuwa produkt z bazy danych na podstawie
     *
     * @param id numer id produktu w bazie danych.
     */
    public void deleteProduct(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Product product = (Product) session.get(Product.class, id);
        session.delete(product);
        transaction.commit();
        session.close();
    }

    /**
     * Metoda pobierająca obiekt product z bazy danych na podstawie
     *
     * @param productId numer id produktu w bazie danych.
     * @return pobrany produkt z bazy danych
     */
    public Product getProductById(int productId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Product product = (Product) session.get(Product.class, productId);
        session.close();
        return product;
    }

    /**
     * Metoda zmieniająca obiekt product na podstawie parametrów
     *
     * @param title - nazwa produktu
     * @param desc - opis produktu
     * @param price - cena produktu
     * @param discount - obniżka ceny produktu
     * @param quantity - ilość produktu
     * @param category - kategoria produktu
     * @param id - id produktu
     * @see servlets.EditProductServlet#editProductGet(HttpServletRequest, HttpServletResponse)
     */
    public void updateProduct(String title, String desc, double price, double discount, int quantity, Category category, int id) {
        Session session = getSession();
        Product product = (Product) session.get(Product.class, id);
        product.setProducttitle(title);
        product.setProductdescrption(desc);
        product.setProductprice(price);
        product.setProductdiscount(discount);
        product.setProductquantity(quantity);
        product.setCategory(category);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Metoda pobierająca ilość produktu z bazy danych na podstawie
     *
     * @param id numer id produktu w bazie danych.
     * @return ilosć pobranych produktów z bazy danych
     */
    public int getQuantity(int id) {
        Session session = getSession();
        Query query = session.createQuery("select p.productquantity from Product as p where p.productid =:id");
        query.setParameter("id", id);
        int result = (int) query.list().get(0);
        session.close();
        return result;
    }

    /**
     * Metoda zmieniająca ilość produktu na podstawie
     *
     * @param id numer id produktu w bazie danych.
     * @param value nowa ilość produktu podana w formularzu
     * @see servlets.EditProductServlet#addQuantityGet(HttpServletRequest, HttpServletResponse)
     */
    public void updateQuantity(int id, int value) {
        Session session = getSession();
        Product product = (Product) session.get(Product.class, id);
        product.setProductquantity(value);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Metoda zwracająca Listę Produkt na podstawie
     * @param searchQuery String parametr przekazany w input search.
     */
    public List<Product> getProductBySearch(String searchQuery){
        Session session = getSession();
        Query query = session.createQuery("from Product where producttitle like:searchQuery or productid " +
                "like:searchQuery or productprice like:searchQuery or productdiscount like:searchQuery");
        query.setParameter("searchQuery", "%"+searchQuery+"%");
        List<Product> list = query.list();
        session.close();
        return list;
    }

//    /**
//     * Metoda otwierająca sesję
//     */
    private Session getSession() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        return session;
    }
}
