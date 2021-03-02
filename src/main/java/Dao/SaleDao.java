package Dao;
import entity.Sale;
import helper.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;


/**
 * @author Piotr Szydłowski
 */
public class SaleDao {

    private Session getSession() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        return session;
    }

    /**
     * Metoda zwracająca listę kodów z tabeli code
     * @param code - nazwa kodu
     * @return lista kodów
     */
    public List<String> getCode(String code){
        Session session = getSession();
        Query query = session.createQuery("select code from Sale where code =:code");
        query.setParameter("code", code);
        List result = query.list();
        return result;
    }

    /**
     * Metoda zwracająca wartość kodu z tabeli Sale
     * @param code - nazwa kodu
     * @return - wartość kodu
     */
    public double getValue(String code){
        Session session = getSession();
        Query query = session.createQuery("select value from Sale where code =:code");
        query.setParameter("code", code);
        double result = (double) query.list().get(0);
        return result;
    }

    /**
     * Metoda zwracająca datę w postaci stringa z tabeli Sale na podstawie
     * @param code - nazwa kodu
     * @return data rozpoczęcia promocji w postaci stringa
     */
    public String getDateFrom(String code){
        Session session = getSession();
        Query query = session.createQuery("select datafrom from Sale where code =:code");
        query.setParameter("code", code);
        String dataFrom = (String) query.list().get(0);
        return dataFrom;
    }

    /**
     * Metoda zwracająca datę w postaci stringa z tabeli Sale na podstawie
     * @param code - nazwa kodu
     * @return data zakończenia promocji w postaci stringa
     */
    public String getDateTo(String code){
        Session session = getSession();
        Query query = session.createQuery("select datato from Sale where code =:code");
        query.setParameter("code", code);
        String dataTo = (String) query.list().get(0);
        return dataTo;
    }

    /**
     * Metoda pobierająca minimalną wartośc zamówienia z DB
     * @param code - nazwa promocji
     * @return - minimalną waertośc zamówienia dla promocji
     */
    public double getMoreThanPrice(String code){
        Session session = getSession();
        Query query = session.createQuery("select price from Sale where code =:code");
        query.setParameter("code", code);
        double result = (double) query.list().get(0);
        return result;
    }

    /**
     * Metoda zapisująca do DB obiekt promocji
     * @param sale - obiekt promocji
     */
    public void saveSale(Sale sale) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(sale);
        transaction.commit();
        session.close();
    }

    /**
     * Metoda pobierająca listę promocji
     * @return lista promocji
     */
    public List<Sale> getAllSale(){
        Session session = getSession();
        Query query = session.createQuery("from Sale");
        List list = query.list();
        return list;
    }

    /**
     * Metoda usuwająca z DB obiekt promocji na podstawie
     * @param idsale - id promocji
     */
    public void deletePromotion(int idsale){
        Session session = getSession();
        Query query = session.createQuery("delete from Sale where idsale =:idsale");
        query.setParameter("idsale", idsale);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
