import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
 
@WebServlet(urlPatterns={"/login"})
public class Login extends HttpServlet
{
    ArrayList<String> usernames = new ArrayList<>();
    ArrayList<String> passwords = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    String logged_in_user;
    
    int logged_in = 0;
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        resp.setContentType("text/plain");
        var pw = resp.getWriter();
        var username = req.getParameter("username");
        var password = req.getParameter("password");
        
        var sess = req.getSession();
        usernames = (ArrayList<String>) sess.getAttribute("usernames");
        passwords = (ArrayList<String>) sess.getAttribute("passwords");
        names = (ArrayList<String>) sess.getAttribute("names");
        logged_in_user = (String) sess.getAttribute("logged_in_user");
        
        if (password == null || username == null) {
            pw.printf("Missing login parameter");
            return;
        }
        
        if(logged_in_user != null)
        {
            pw.printf("You are already logged in as: " + logged_in_user);
        }
        else
        {
            for (int i = 0; i < usernames.size(); i++) {
                if (usernames.get(i).equals(username) && password.equals(passwords.get(i))) {
                    logged_in_user = username;
                    sess.setAttribute("logged_in_user", logged_in_user);
                    pw.printf("You are logged in");
                    return;
                }
            }
            pw.printf("This user is not signed up");
        }
    }
}
