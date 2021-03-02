package Dao;

import entity.Category;
import entity.Product;
import helper.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryDaoTest {

    static Category categoryTest;
    static int maxCategoryId;
    CategoryDao categoryDao = new CategoryDao();

    @BeforeAll
    static void setUp() {
      categoryTest = new Category("Test kategorii", "Opis kategorii");
    }

    @Test
    @Order(1)
    void saveCategory() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(categoryTest);
        transaction.commit();
        session.close();
        assertEquals("Test kategorii", categoryTest.getCategoryTitle());
        assertEquals("Opis kategorii", categoryTest.getCategoryDescription());
        maxCategoryId = getIdCategoryTest();
    }

    @Test
    @Order(2)
    void getCategoryById() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Category category = (Category) session.get(Category.class, getIdCategoryTest());
        session.close();
        assertEquals("Opis kategorii", category.getCategoryDescription());
    }

    @Test
    @Order(3)
    void getAllCategory() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Category");
        List<Category> list = query.list();
        session.close();
        assertEquals(categoryDao.getAllCategory().size(), list.size());
    }

    @Test
    @Order(4)
    void updateCategory() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Category category = (Category) session.get(Category.class, getIdCategoryTest());
        category.setCategoryTitle("title");
        category.setCategoryDescription("desc");
        session.getTransaction().commit();
        session.close();
        assertEquals("title", category.getCategoryTitle());
        assertEquals("desc", category.getCategoryDescription());
    }

    @Test
    @Order(5)
    void deleteCategory() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Category category = (Category) session.get(Category.class, getIdCategoryTest());
        session.delete(category);
        transaction.commit();
        session.close();
        assertNotEquals(getIdCategoryTest(), maxCategoryId);
    }


    public int getIdCategoryTest() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select max (categoryId) from Category");
        List<Integer> list = query.list();
        Integer max = Collections.max(list);
        return max;
    }
}