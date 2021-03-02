package Dao;

import entity.Category;
import entity.Product;
import helper.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;

import javax.validation.constraints.AssertTrue;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Piotr Szydłowski
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductDaoTest {

    static ProductDao productDao = new ProductDao();
    static CategoryDao categoryDao = new CategoryDao();
    static Product productTest;
    static int maxAddProduct;


    @BeforeAll
     public static void beforeTheEntireTestFixture() {
        List<Category> allCategory = categoryDao.getAllCategory();
        Category category = allCategory.get(0);
        productTest = new Product("Test Produkt", "Testowy opis", "photo.jpg",
                10, 2, 100, category);
    }

    @Test
    @Order(1)
    void saveProduct() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(productTest);
        transaction.commit();
        session.close();
        maxAddProduct = getIdTest();
        assertEquals("Test Produkt", productTest.getProducttitle());
        assertEquals("Testowy opis", productTest.getProductdescrption());
        assertEquals("photo.jpg", productTest.getProductphoto());
        assertEquals(10, productTest.getProductprice());
        assertEquals(2, productTest.getProductdiscount());
        assertEquals(100, productTest.getProductquantity());
    }

    @Test
    @Order(2)
    void getAllProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Product");
        List<Product> list = query.list();
        session.close();
        assertEquals(productDao.getAllProducts().size(), list.size());
    }

    @Test
    @Order(3)
    void getProductById() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Product product = (Product) session.get(Product.class, getIdTest());
        session.close();
        assertEquals("photo.jpg", product.getProductphoto());
    }

    @Test
    @Order(4)
    void updateProduct() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Product product = (Product) session.get(Product.class, getIdTest());
        product.setProducttitle("title");
        product.setProductdescrption("desc");
        product.setProductprice(10);
        product.setProductdiscount(0);
        product.setProductquantity(100);
        session.getTransaction().commit();
        session.close();
        assertEquals("title", product.getProducttitle());
        assertEquals("desc", product.getProductdescrption());
        assertEquals(10, product.getProductprice());
        assertEquals(0, product.getProductdiscount());
        assertEquals(100, product.getProductquantity());
    }

    @Test
    @Order(5)
    void getQuantity() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select p.productquantity from Product as p where p.productid =:id");
        query.setParameter("id", getIdTest());
        int result = (int) query.list().get(0);
        session.close();
        assertEquals(100, result);
    }

    @Test
    @Order(6)
    void updateQuantity() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Product product = (Product) session.get(Product.class, getIdTest());
        product.setProductquantity(9);
        session.getTransaction().commit();
        session.close();
        assertEquals(9, productDao.getQuantity(getIdTest()));
    }

    @Test
    @Order(7)
    void deleteProduct() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Product product = (Product) session.get(Product.class, getIdTest());
        session.delete(product);
        transaction.commit();
        session.close();
        assertNotEquals(getIdTest(), maxAddProduct);
    }

    public int getIdTest() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select max (productid) from Product");
        List<Integer> list = query.list();
        Integer max = Collections.max(list);
        return max;
    }
}