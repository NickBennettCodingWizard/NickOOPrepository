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
        String txt = Utils.fetch( "/srv/login?user=bob" );
        assertTrue( txt.contains("Logged in as bob"));
    }
    
    @Test
    public void testLogin2() throws Exception{
        String txt = Utils.fetch( "/srv/login?user=jill");
        assertFalse( txt.contains("Don't kow who you are"));
        assertTrue( txt.contains("Logged in as jill"));
    }
    
    @Test
    public void testWhoNotLoggedIn() throws Exception{
        String txt = Utils.fetch( "/srv/who" ) ;
        assertTrue( txt.contains("Don't know who you are"));
    }
    
    @Test
    public void testWhoLoggedIn() throws Exception{
        String txt = Utils.fetch(  "/srv/login?user=bob", "/srv/who" ) ;
        assertTrue( txt.contains("You are bob"));
    }
    
}