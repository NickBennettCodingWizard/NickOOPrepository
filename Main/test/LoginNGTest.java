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
import org.testng.annotations.Test;

import org.eclipse.jetty.util.log.AbstractLogger;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.StdErrLog;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


/**
 *
 * @author nickb
 */
public class LoginNGTest {
    
    public LoginNGTest() {
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
    public void testLogin1() throws Exception{
        String txt = Utils.fetch( "/srv/signup?username=bob&password=1234&name=bill" );
        assertTrue( txt.contains("Signed up as: bob"));
        txt = Utils.fetch( "/srv/login?username=bob&password=1234" );
        assertTrue( txt.contains("You are logged in"));
    }
    
    @Test
    public void testAlreadyLoggedIn() throws Exception{
        String txt = Utils.fetch( "/srv/signup?username=bob&password=1234&name=bill" );
        assertTrue( txt.contains("Signed up as: bob"));
        txt = Utils.fetch( "/srv/signup?username=ted&password=4321&name=det" );
        assertTrue( txt.contains("Signed up as: ted"));
        txt = Utils.fetch( "/srv/login?username=bob&password=1234" );
        assertTrue( txt.contains("You are logged in"));
        txt = Utils.fetch( "/srv/login?username=ted&password=4321" );
        assertTrue( txt.contains("You are already logged in as: bob"));
    }
    
    @Test
    public void testLoginNoUsername() throws Exception{
        String txt = Utils.fetch( "/srv/login?password=1234");
        assertTrue( txt.contains("Missing login parameter"));
    }
    
    @Test
    public void testWhoNotLoggedIn() throws Exception{
        String txt = Utils.fetch( "/srv/who" ) ;
        assertTrue( txt.contains("Don't know who you are"));
    }
    
    @Test
    public void testLoginNoPassword() throws Exception{
        String txt = Utils.fetch( "/srv/login?username=bob" );
        assertTrue(txt.contains("Missing login parameter"));
    }
    
}