package servlets;

import Dao.UserDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 @author Piotr Szydłowski
 */

@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
    
    String userName, userEmail, userPassword, userPhone, userAddress, userRepetPassword;
    HttpSession httpSession;
    UserDao userDao = new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        createNewUser(request, response);
    }

    /**
     * Metoda tworząca obiekt usera na podstawie
     * @param request przekazanych w formularzu
     * @return liste avability
     */
    public void createNewUser(HttpServletRequest request, HttpServletResponse respone) throws IOException {
        userName = request.getParameter("user_name");
        userEmail = request.getParameter("user_email");
        userPassword = request.getParameter("user_password");
        userPhone = request.getParameter("user_phone");
        userAddress = request.getParameter("user_address");
        userRepetPassword = request.getParameter("user_repet_password");

        if (validForm() && validRepetedPassword() && validIfEmailRegitered() && validIfEmailRegitered() && validatePatterPassword()) {
//            String MD5Password = encryptPassword(userPassword);
            User user = new User(userName, userEmail, userPassword, userPhone, userAddress, "normal");
            userDao.saveUser(user);
            setMessage(request);
        } else if (!validRepetedPassword()) {
            httpSession = request.getSession();
            httpSession.setAttribute("message", "Hasła nie są identyczne");
        } else if (!validForm()) {
            httpSession = request.getSession();
            httpSession.setAttribute("validateForm", "Nie uzupełniono wszystkich pól, uzupełnij pola i spróbuj ponownie");
        } else if (!validIfEmailRegitered()) {
            httpSession = request.getSession();
            httpSession.setAttribute("validateForm", "Użytkownik o podanym adresie " + userEmail + " istnieje");
        } else if (!validatePatterPassword()) {
            httpSession = request.getSession();
            httpSession.setAttribute("validateForm", "Hasło powinno zawierać przynajmniej 8 znakow, dużą literę oraz znak specjalny");
        }
        respone.sendRedirect("register.jsp");
    }

    private void setMessage(HttpServletRequest request) {
        httpSession = request.getSession();
        httpSession.setAttribute("message", "Użytkownik " + userName + " zarejestrowany");
    }

    /**
     * Metoda szyfrująca hasło zapisywane do bazy danych
     * @param password - hasło podane w formularzu
     * @return zaszyfrowane hasło
     */
    private String encryptPassword(String password) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        digest.update(password.getBytes());
        String md5Password = new BigInteger(1, digest.digest()).toString(16);
        return md5Password;
    }

    /**
     * Metoda walidująca czy formularz posiada puste pola
     */
    public boolean validForm() {
        return !userName.isEmpty() && !userPassword.isEmpty() && !userEmail.isEmpty() && !userAddress.isEmpty();
    }

    /**
     * Metoda walidująca czy hasło i jego powtórzenie jest identyczne
     */
    public boolean validRepetedPassword() {
        return userPassword.equals(userRepetPassword);
    }

    /**
     * Metoda sprawdzająca czy użyty przy rejestracji e-mail znajduje się w bazie danych
     */
    public boolean validIfEmailRegitered() {
        List list = userDao.getUserByEmail(userEmail);
        return list.isEmpty();
    }

    /**
     * Metoda walidująca podane hasło zgodnie z wzorcem
     */
    public boolean validatePatterPassword() {
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+!=])(?=\\S+$).{8,}";
        return userPassword.matches(pattern);
    }
}
