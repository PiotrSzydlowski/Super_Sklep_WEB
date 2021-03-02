package Dao;

import entity.Product;
import entity.User;
import helper.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserDaoTest {

    static User userTest;
    static int maxUserId;
    static String email = "test@testowy";


    @BeforeAll
    static void setUp() {
        userTest = new User("Testowy użytkownik", email, "haslo123",
                "123456", "Tesowy adres", "normal");
    }

    @Test
    @Order(1)
    void saveUser() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(userTest);
        transaction.commit();
        session.close();
        maxUserId = getMaxId();
        assertEquals("Testowy użytkownik", userTest.getUserName());
        assertEquals(email, userTest.getUserEmail());
        assertEquals("haslo123", userTest.getUserPassword());
        assertEquals("123456", userTest.getUserPhone());
        assertEquals("Tesowy adres", userTest.getUserAddress());
        assertEquals("normal", userTest.getUserType());
    }

    @Test
    @Order(2)
    void getUserByEmail() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select userEmail from  User where userEmail = :e");
        query.setParameter("e", email);
        List list = query.list();
        session.close();
        assertEquals(email, list.get(0));
    }

    @Test
    @Order(3)
    void getUserByEmailAndPassword() {
    }

    @Test
    @Order(4)
    void getAllUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from User");
        List<User> list = query.list();
        session.close();
        boolean moreThanZero = (0 < list.size());
        assertTrue(moreThanZero);
    }

    @Test
    @Order(5)
    void deleteUser(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, getMaxId());
        session.delete(user);
        transaction.commit();
        session.close();
        assertNotEquals(getMaxId(), maxUserId);
    }

    public int getMaxId() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select max (userID) from User");
        List<Integer> list = query.list();
        Integer max = Collections.max(list);
        return max;
    }
}