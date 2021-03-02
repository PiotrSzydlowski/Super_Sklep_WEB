package Dao;

import entity.User;
import helper.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

/**
 * @author Piotr Szydłowski
 */
public class UserDao {

    /**
     * Metoda zapisująca obiekt użytkownika do bazy danych.
     *
     * @param user obiekt użytkownika z metody
     * @see servlets.RegisterServlet#createNewUser
     */
    public void saveUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    /**
     * Metoda zwracająca listę obiektów user.
     *
     * @param email adres email użytkownika zapisany w bazie danych
     */
    public List getUserByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from User where userEmail = :e");
        query.setParameter("e", email);
        List list = query.list();
        session.close();
        return list;
    }

    /**
     * Metoda zwracająca obiekt user.
     *
     * @param email    adres - email użytkownika zapisany w bazie danych
     * @param password - hasło użytkownika zapisane w bazie danych
     * @return obiekt user
     */
    public User getUserByEmailAndPassword(String email, String password) {
        Session session = getSession();
        Query query = session.createQuery("from User where userEmail = :e and userPassword = :p");
        query.setParameter("e", email);
        query.setParameter("p", password);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }

    /**
     * Metoda zwracająca listę użytkowników zapisanych w bazie danych.
     *
     * @return lista użytkowników
     */
    public List<User> getAllUsers() {
        Session session = getSession();
        Query query = session.createQuery("from User");
        List<User> list = query.list();
        session.close();
        return list;
    }

    /**
     * Metoda zmieniająca obiekt user.
     * @param username - nazwa użytkownika
     * @param useremail - email użytkownika
     * @param userphone - telefon użytkownika
     * @param useraddress - adres użytkownika
     */
    public void updateUser(String username, String useremail, String userphone, String useraddress, int id) {
        Session session = getSession();
        User user = (User) session.get(User.class, id);
        user.setUserEmail(useremail);
        user.setUserName(username);
        user.setUserPhone(userphone);
        user.setUserAddress(useraddress);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Metoda zmieniająca hasło w bazie danych
     * @param password - nowe hasło
     * @param id - id użytkownika
     */
    public void updateUserPassword(String password, int id) {
        Session session = getSession();
        User user = (User) session.get(User.class, id);
        user.setUserPassword(password);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Metoda zmieniająca obiekt user z poziomu panelu administratora
     * @param username - nazwa użytkownika
     * @param useremail - email użytkownika
     * @param userphone - telefon użytkownika
     * @param useraddress - adres użytkownika
     * @param userpass - hasło użytkownika
     * @param usertype - typ użytkownika
     */
    public void updateAllRecordUser(String username, String useremail, String userphone, String useraddress,
                                    String userpass, String usertype, int id) {
        Session session = getSession();
        User user = (User) session.get(User.class, id);
        user.setUserEmail(useremail);
        user.setUserName(username);
        user.setUserPhone(userphone);
        user.setUserAddress(useraddress);
        user.setUserPassword(userpass);
        user.setUserType(usertype);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Metoda zwracająca obiekt User na podstawie parametrów:
     * @param id - id usera zapisanego w bazie danych
     * @return obiekt użytkownika
     */
    public User getUserById(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = (User) session.get(User.class, id);
        session.close();
        return user;
    }

    /**
     * Metoda usuwająca obiekt user z bazy danych.
     * @param id - id użytkownika zapisanego w wbazie danych
     */
    public void deleteUser(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        transaction.commit();
        session.close();
    }

    /**
     * Metoda zwracająca Listę obiektów User.
     * @param searchQuery - String będący parametrem pobieranym z input search
     * @return lista obiektó user
     */
    public List<User> getUsersBySearching(String searchQuery){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where userid like:searchQuery or username " +
                "like:searchQuery or userphone like:searchQuery or useremail like:searchQuery");
        query.setParameter("searchQuery", "%"+searchQuery+"%");
        List<User> list = query.list();
        session.close();
        return list;
    }

    private Session getSession() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        return session;
    }
}
