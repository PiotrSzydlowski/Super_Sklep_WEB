package Dao;

import entity.Avability;
import helper.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Piotr Szydłowski
 */

class AvabilityDaoTest {

    @Test
    void getAllAvability() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Avability");
        List listOfAvability = query.list();
        session.close();
        boolean moreThanZero = (listOfAvability.size() > 0);
        assertTrue(moreThanZero);
    }

    @Test
    void getAvability() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Avability as a where a.idAvability =:id");
        query.setParameter("id", 3);
        Avability avability = (Avability) query.list().get(0);
        session.close();
        assertEquals(avability.getAvabilityname(), "wysoka");
    }

    @Test
    void updateAvability() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Avability avability = (Avability) session.get(Avability.class, 3);
        avability.setAvabilityvalue(99);
        session.getTransaction().commit();
        session.close();
        assertEquals(avability.getAvabilityvalue(), 99);
    }
}