/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.CookieHandler;
import java.net.CookieManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author terio
 */
public class signupNGTest {
    
    public signupNGTest() {
    }
    @BeforeClass
    public static void foo(){
        Utils.startJetty();
    }
    @AfterClass
    public static void foo2(){
        Utils.stopJetty();
    }
    
    static CookieManager cookieManager = new CookieManager();
    @BeforeClass
    public static void setupSession(){
        CookieHandler.setDefault(cookieManager);
    }

    @BeforeMethod   //before each @test this will run and clear cookies
    public void clearCookies(){
        cookieManager.getCookieStore().removeAll();
    }
    
    @Test
    public void testSignUp() throws Exception{
        String txt = Utils.fetch( "/srv/signup?username=bob&password=1234&name=bill" );
        assertTrue( txt.contains("Signed up as: bob"));
        assertTrue( txt.contains("Your password is: 1234"));
        assertTrue( txt.contains("Your name is: bill"));
    }
    
    @Test
    public void testSignUpCorrectUsername() throws Exception{
        String txt = Utils.fetch( "/srv/signup?username=bob&password=1234&name=bill" );
        assertTrue( txt.contains("Signed up as: bob"));
    }
    
    @Test
    public void testSignUpCorrectPassword() throws Exception{
        String txt = Utils.fetch( "/srv/signup?username=bob&password=1234&name=bill" );
        assertTrue( txt.contains("Your password is: 1234"));
    }
    
    @Test
    public void testSignUpCorrectName() throws Exception{
        String txt = Utils.fetch( "/srv/signup?username=bob&password=1234&name=bill" );
        assertTrue( txt.contains("Your name is: bill"));
    }
    
    @Test
    public void testSignUpNoUsername() throws Exception{
        String txt = Utils.fetch( "/srv/signup?password=1234&name=bill" );
        assertTrue( txt.contains("Bad parameter given"));
    }
    
    @Test
    public void testSignUpNoPassword() throws Exception{
        String txt = Utils.fetch( "/srv/signup?&username=bob&name=bill" );
        assertTrue( txt.contains("Bad parameter given"));
    }
    
    @Test
    public void testSignUpNoName() throws Exception{
        String txt = Utils.fetch( "/srv/signup?username=bob&password=1234&" );
        assertTrue( txt.contains("Bad parameter given"));
    }
    
    @Test
    public void testSignUpNoParameters() throws Exception{
        String txt = Utils.fetch( "/srv/signup?" );
        assertTrue( txt.contains("Bad parameter given"));
    }
    
    
    
    
    
}
