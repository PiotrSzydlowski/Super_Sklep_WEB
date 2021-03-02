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


/**
 * @author Piotr Szydłowski
 */
@WebServlet(name = "EditUserServlet", urlPatterns = {"/EditUserServlet"})
public class EditUserServlet extends HttpServlet {

    UserDao userDao = new UserDao();
    HttpSession httpSession;
    int editUserBtn;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getUserParam(request, response);
    }

    /**
     Metoda pobierająca parametry ustawione na poziomie sesji. Na ich podstawie modyfikowany jest obiekt user.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        String userPhone = request.getParameter("userPhone");
        String userPass = request.getParameter("userPass");
        String userAddress = request.getParameter("userAddress");
        String userType = request.getParameter("userType");

        userDao.updateAllRecordUser(userName,userEmail,userPhone,userAddress, userPass, userType, editUserBtn);

        response.sendRedirect("userTable.jsp");
        httpSession = request.getSession();
        httpSession.setAttribute("message", "Zaktualizowano użytkownika");
        return;
    }

    /**
     Metoda pobierająca id usera z label editUserBtn
     Na podstawie id pobierany jest obiekt usera oraz przekazywany do strony editUser.jsp
     */
    private void getUserParam(HttpServletRequest request, HttpServletResponse response) throws IOException {
        editUserBtn = Integer.parseInt(request.getParameter("editUserBtn"));
        httpSession = request.getSession();
        httpSession.setAttribute("userIdEdit", editUserBtn);
        User userById = userDao.getUserById(editUserBtn);

        httpSession.setAttribute("userEditName", userById.getUserName());
        httpSession.setAttribute("userEditEmail", userById.getUserEmail());
        httpSession.setAttribute("userEditPhone", userById.getUserPhone());
        httpSession.setAttribute("userEditPass",  userById.getUserPassword());
        httpSession.setAttribute("userEditAddress", userById.getUserAddress());
        httpSession.setAttribute("userEditType", userById.getUserType());
        response.sendRedirect("editUser.jsp");
    }
}
