package servlets;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 @author Piotr Szydłowski
 */
class RegisterServletTest {


    List list = new ArrayList();

    @Test
    void createNewUser() {
        User user = new User("userName", "userEmail", "userPassword", "userPhone",
                "userAddress", "normal");
        list.add(user);
        assertEquals(1, list.size());
        assertEquals("userName", user.getUserName());
    }

    @Test
    void validForm() {
        User user = new User();
    }

    @Test
    void validRepetedPassword() {
    }

    @Test
    void validIfEmailRegitered() {
    }

    @Test
    void validatePatterPassword() {
    }
}