/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author terio
 */
public class LoginNGTest {
    
    public LoginNGTest() {
    }

    @Test
    public void testDoGet() throws Exception {
        System.out.println("doGet");
        HttpServletRequest req = null;
        HttpServletResponse resp = null;
        Login instance = new Login();
        instance.doGet(req, resp);
        fail("The test case is a prototype.");
    }
    
}
