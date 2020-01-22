import java.io.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(urlPatterns={"/baz"})
public class ParameterTest extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        resp.setContentType("text/plain");
        var pw = resp.getWriter();
        var name = req.getParameter("username");
        var ageStr = req.getParameter("age");
        if( name == null || ageStr == null ){
            pw.printf("Missing parameter");
            return;
        }
        try{
            int age = Integer.parseInt(ageStr);
            pw.printf("In five years, %s will be %d years old.", name,age+5);
        } catch(NumberFormatException e){
            pw.printf("Bad age format");
            return;
        }
    }
}
