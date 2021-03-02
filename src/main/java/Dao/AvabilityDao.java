package Dao;

import entity.Avability;
import helper.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import java.util.List;

/**
 @author Piotr Szydłowski
 */
public class AvabilityDao {

    /**
     * Metoda pobierająca listę avability z bazy danych.
     * @return liste avability
     */
    public List<Avability> getAllAvability() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Avability");
        List listOfAvability = query.list();
        session.close();
        return listOfAvability;
    }

    /**
     * Metoda pobierająca obiekt avability z bazy danych.
     * na podstawie
     * @param id - id avablity z bazy danych
     * @return obiekt avability
     */
    public Avability getAvability(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Avability as a where a.idAvability =:id");
        query.setParameter("id", id);
        Avability avability = (Avability) query.list().get(0);
        session.close();
        return avability;
    }

    /**
     * Metoda zminiająca obiekt avability.
     * na podstawie
     * @param id - id vability z bazy danych
     * @param value - wartość będąca integerem z
     * @see servlets.AvabilityServlet#doPost
     * @return obiekt kategorii
     */
    public void updateAvability(int id, int value) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Avability avability = (Avability) session.get(Avability.class, id);
        avability.setAvabilityvalue(value);
        session.getTransaction().commit();
        session.close();
    }
}
