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
public class signupNGTest {
    
    public signupNGTest() {
    }

    @Test
    public void testDoGet() throws Exception {
        System.out.println("doGet");
        HttpServletRequest req = null;
        HttpServletResponse resp = null;
        signup instance = new signup();
        instance.doGet(req, resp);
        fail("The test case is a prototype.");
    }
    
}
