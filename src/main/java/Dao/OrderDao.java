package Dao;

import entity.Order;
import entity.Product;
import helper.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import servlets.CheckoutServlet;
import java.util.List;


/**
 * @author Piotr Szydłowski
 */
public class OrderDao {

    /**
     * Metoda zapisująca obiekt Order w bazie danych
     *
     * @param order - obiekt utworzony w
     * @see CheckoutServlet#createOrders()
     */
    public void saveOrder(Order order) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(order);
        transaction.commit();
        session.close();
    }

    /**
     * Metoda zwracająca Integer bedący największym numerem zamówienia zapisanym w bazie danych
     */
    public Integer getOrderNumber() {
        Session session = getSession();
        Query query = session.createQuery("select max(ordernumber) from orders");
        Integer result = (Integer) query.list().get(0);
        return result;
    }

    /**
     * Metoda usuwająca obiekt Order z bazy danych
     *
     * @param orderNumber - numer zamówienia
     */
    public void deleteOrder(int orderNumber) {
        Session session = getSession();
        Query query = session.createQuery("delete from orders as o where o.ordernumber =:orderNumber");
        query.setParameter("orderNumber", orderNumber);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Metoda zmieniająca obiekt Order w bazie danych
     *
     * @param orderNumber - numer zamówienia z bazy danych
     */
    public void updateOrder(int orderNumber) {
        Session session = getSession();
        Query query = session.createQuery("update orders set orderstatus ='opłacony' where ordernumber =:orderNumber");
        query.setParameter("orderNumber", orderNumber);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Metoda zwracająca Listę obiektów Order z baziy danych
     *
     * @param id - numer id obiektu zapisanego w bazie danych
     */
    public List<Order> getOrdersById(int id) {
        Session session = getSession();
        Query query = session.createQuery("from orders where userid =:id");
        query.setParameter("id", id);
        List<Order> list = query.list();
        session.close();
        return list;
    }

    /**
     * Metoda zwracająca Listę obiektów Order z bazy danych
     *
     * @param number - numer zamówienia z bazy danych
     */
    public List<Order> getOrderByNumber(int number) {
        Session session = getSession();
        Query query = session.createQuery("from orders where ordernumber =:number");
        query.setParameter("number", number);
        List<Order> list = query.list();
        session.close();
        return list;
    }

    /**
     * Metoda zmieniająca cenę zamówienia po wykorzystaniu promocji
     * @param orderNumber - numer zamówienia
     * @param totalprice - cena zamówienia
     */
    public void updateTotalPriceAfterUseCoupon(int orderNumber, double totalprice) {
        Session session = getSession();
        Query query = session.createQuery("update orders set totalprice =:totalprice where ordernumber =:orderNumber");
        query.setParameter("orderNumber", orderNumber);
        query.setParameter("totalprice", totalprice);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Metoda zwracająca Sumę zamówienia po numerze zamówienia
     * @param number - numer zamówienia
     * @return suma zamówienia
     */
    public double getTotalPriceByOrderNumber(int number){
        Session session = getSession();
        Query query = session.createQuery("select totalprice from orders where ordernumber =:number");
        query.setParameter("number", number);
        double totalPrice = (double) query.list().get(0);
        session.close();
        return totalPrice;
    }

    /**
     * Metoda pobierająca i zwracająca wartość kuponu.
     * zwracana wartość to 1 jeśli nie to 0.
     * @param number - numer zamówienia
     * @return - wartość 1 dla zamówienia, które wykorzystało kupon, 0 dla zamówienia, dla kórego nie był wykorzystany kupon
     */
    public int getCoupon(int number){
        Session session = getSession();
        Query query = session.createQuery("select coupon from orders where ordernumber =:number");
        query.setParameter("number", number);
        Integer coupon = (Integer) query.list().get(0);
        session.close();
        return coupon;
    }

    /**
     * Metoda zmieniająca wartość w kolumnie coupon na 1 po jego wykorzystaniu
     * @param orderNumber - numer zamówienia
     */
    public void updateCoupon(int orderNumber) {
        Session session = getSession();
        Query query = session.createQuery("update orders set coupon ='1' where ordernumber =:orderNumber");
        query.setParameter("orderNumber", orderNumber);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Metoda zwracająca listę zamówień po id usera z tabeli orders
     * @param id - numer id usera w tabeli orders
     * @return lista zamówień
     */
    public List<Order> getOrdersByIdAndStatus(int id) {
        Session session = getSession();
        Query query = session.createQuery("from orders where userid =:id and orderstatus = 'zamówione'");
        query.setParameter("id", id);
        List<Order> list = query.list();
        session.close();
        return list;
    }

    private Session getSession() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        return session;
    }

}
