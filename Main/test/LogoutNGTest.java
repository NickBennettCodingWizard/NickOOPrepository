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
public class LogoutNGTest {
    
    public LogoutNGTest() {
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
    public void testLogingOut() throws Exception{
        String txt = Utils.fetch( "/srv/signup?username=bob&password=1234&name=bill" );
        assertTrue( txt.contains("Signed up as: bob"));
        txt = Utils.fetch( "/srv/login?username=bob&password=1234" );
        assertTrue( txt.contains("You are logged in"));
        txt = Utils.fetch( "/srv/logout?username=bob" );
        assertTrue( txt.contains("You are logged out"));
    }
    
    @Test
    public void testLogoutNotLoggedIn() throws Exception{
        String txt = Utils.fetch( "/srv/signup?username=bob&password=1234&name=bill" );
        assertTrue( txt.contains("Signed up as: bob"));
        txt = Utils.fetch( "/srv/logout?username=bob" );
        assertTrue( txt.contains("You are not logged in"));
    }
    
    @Test
    public void testLogoutBadParameter() throws Exception{
        String txt = Utils.fetch( "/srv/logout?" );
        assertTrue( txt.contains("Bad username"));
    }
    
    
}
