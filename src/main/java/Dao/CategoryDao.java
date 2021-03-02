package Dao;

import entity.Category;
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

/**
 * Metoda zapisująca obiekt kategorii do bazy danych.
 */
public class CategoryDao {
    public int saveCategory(Category category) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int catId = (int) session.save(category);
        transaction.commit();
        session.close();
        return catId;
    }

    /**
     * Metoda pobierająca obiekt kategorii z bazy danych.
     * na podstawie
     * @param categoryid - id kategorii z bazy danych
     * @return obiekt kategorii
     */
    public Category getCategoryById(int categoryid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Category category = (Category) session.get(Category.class, categoryid);
        session.close();
        return category;
    }

    /**
     * Metoda pobierająca listę kategorii z bazy danych.
     * @return listę kategorii
     */
    public List<Category> getAllCategory() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Category");
        List listOfCategory = query.list();
        session.close();
        return listOfCategory;
    }

    /**
     * Metoda usuwająca kategorie z bazy danych.
     * na podstawie
     * @param id - id kategorii
     */
    public void deleteCategory(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Category category = (Category) session.get(Category.class, id);
        session.delete(category);
        transaction.commit();
        session.close();
    }

    /**
     * Metoda zmieniająca obiekt kategorii na podstawie parametrów
     * @param title - nazwa kategorii
     * @param desc - opis kategorii
     * @param id - id produktu
     */
    public void updateCategory(String title, String desc, int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Category category = (Category) session.get(Category.class, id);
        category.setCategoryTitle(title);
        category.setCategoryDescription(desc);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Metoda zwracająca Listę kategorii na podstawie parametrów
     * @param searchQuery - String podany w polu wykukiwania
     * @return Lista kategorii dopasowana na podstwie parametru
     */
    public List<Category> getCategoryBySearch(String searchQuery){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Category where categoryid like:searchQuery or categorytitle " +
                "like:searchQuery");
        query.setParameter("searchQuery", "%"+searchQuery+"%");
        List<Category> list = query.list();
        session.close();
        return list;
    }
}
