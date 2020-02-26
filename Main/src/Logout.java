import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
@WebServlet(urlPatterns={"/logout"})
public class Logout extends HttpServlet
{
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        
        resp.setContentType("text/plain");
        
        HttpSession sess;
        sess = req.getSession();
        var pw = resp.getWriter();
        var username = req.getParameter("username");
        
        if (username == null) {
            pw.printf("Bad username");
            return;
        }
        
        var logged_in = (String) sess.getAttribute("logged_in_user");
        
        if (username.equals(logged_in))
        {
            sess.setAttribute("logged_in_user", null);
            pw.printf("You are logged out");
        }
        else
        {
            pw.printf("You are not logged in");
        }
    }

}
