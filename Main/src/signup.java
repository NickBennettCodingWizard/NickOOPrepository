import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet(urlPatterns={"/signup"})
public class signup extends HttpServlet
{
    ArrayList<String> usernames = new ArrayList<String>();
    ArrayList<String> passwords = new ArrayList<String>();
    ArrayList<String> names = new ArrayList<String>();
    
    int num_users = 0;
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        resp.setContentType("text/plain");
        var pw = resp.getWriter();
        
        var username = req.getParameter("username");
        var password = req.getParameter("password");
        var name = req.getParameter("name");
        
        if (username == null || password == null || name == null) {
            pw.printf("Bad parameter given");
            return;
        }
        
        usernames.add(username);
        passwords.add(password);
        names.add(name);
        
        if( usernames.get(num_users) == null ){
            pw.printf("No username provided");
        } else {
            var sess = req.getSession();
            sess.setAttribute("usernames", usernames );
            sess.setAttribute("passwords", passwords);
            sess.setAttribute("names", names);
            sess.setAttribute("num_users", num_users);
            pw.printf("Signed up as: "+ usernames.get(num_users)+"\n");
            pw.printf("Your password is: " + passwords.get(num_users)+"\n");
            pw.printf("Your name is: " + names.get(num_users)+"\n");
        }
        num_users++;
    }
}
