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
public class Who2NGTest {
    
    public Who2NGTest() {
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
    public void testNoUser() throws Exception{
        String notLoggedInTxt = Utils.fetch("/srv/who2");
        assertTrue(notLoggedInTxt.contains("You are not logged in. Please Sign Up or Login."));
    }
    
    @Test
    public void testIsUser() throws Exception{
        String notLoggedInTxt = Utils.fetch("/srv/who2");
        assertTrue(notLoggedInTxt.contains("You are not logged in. Please Sign Up or Login."));
        String txt = Utils.fetch( "/srv/signup?username=bob&password=1234&name=bill" );
        assertTrue( txt.contains("Signed up as: bob"));
        txt = Utils.fetch( "/srv/login?username=bob&password=1234" );
        assertTrue( txt.contains("You are logged in"));
        String isLoggedInTxt = Utils.fetch("/srv/who2");
        assertTrue(isLoggedInTxt.contains("You are logged in as: bob"));
    }
    
    @Test
    public void testMultipleUsers() throws Exception{
        String notLoggedInTxt = Utils.fetch("/srv/who2");
        assertTrue(notLoggedInTxt.contains("You are not logged in. Please Sign Up or Login."));
        String txt = Utils.fetch( "/srv/signup?username=bob&password=1234&name=bill" );
        assertTrue( txt.contains("Signed up as: bob"));
        txt = Utils.fetch( "/srv/login?username=bob&password=1234" );
        assertTrue( txt.contains("You are logged in"));
        String txt2 = Utils.fetch( "/srv/signup?username=JellyLord&password=7145&name=Dion" );
        assertTrue( txt2.contains("Signed up as: JellyLord"));
        String isLoggedInTxt = Utils.fetch("/srv/who2");
        assertFalse(isLoggedInTxt.contains("You are logged in as: JellyLord"));
        assertTrue(isLoggedInTxt.contains("You are logged in as: bob"));
        txt2 = Utils.fetch("/srv/logout?username=bob");
        assertTrue(txt2.contains("You are logged out"));
        txt2 = Utils.fetch( "/srv/login?username=JellyLord&password=7145" );
        assertTrue( txt2.contains("You are logged in"));
        isLoggedInTxt = Utils.fetch("/srv/who2");
        assertTrue(isLoggedInTxt.contains("You are logged in as: JellyLord"));
        assertFalse(isLoggedInTxt.contains("You are logged in as: bob"));
    }
}
