
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


@WebServlet(urlPatterns={"/weather"})
public class weather extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        
        String[] urls = new String[]{
            "https://alerts.weather.gov/cap/wwaatmget.php?x=OHZ088&y=1", //Scioto County, OH
            "https://alerts.weather.gov/cap/wwaatmget.php?x=OHZ055&y=1", //Franklin County, OH
            "https://alerts.weather.gov/cap/wwaatmget.php?x=OHZ082&y=1"  //Pike County, OH
        };
          
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        
        pw.println("<!DOCTYPE html>");
        pw.println("<HTML>");
        pw.println("<head>");
        pw.println("<meta charset=utf8>");
        pw.println("</head><body>");
        pw.println("<table>");
        pw.println("<tr><th>Starts</th><th>Ends</th><th>Area</th><th>Event</th></tr>");
        
        for(var ustr : urls ){
            try{
                var content = new URL(ustr).openStream().readAllBytes();
                var doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(content));
                var entries = doc.getElementsByTagName("entry");
                for(int i=0;i<entries.getLength();++i){
                    var e = (Element) entries.item(i);
                    
                    String event = getText(e,"cap:event");
                    String area = getText(e,"cap:areaDesc");
                    String effective = getText(e,"cap:effective");
                    String expires = getText(e,"cap:expires");
                    
                    pw.println("<tr>");
                    pw.println("<td>");
                    pw.println(effective);
                    pw.println("</td>");
                    pw.println("<td>");
                    pw.println(expires);
                    pw.println("</td>");
                    pw.println("<td>");
                    pw.println(area);
                    pw.println("</td>");
                    pw.println("<td>");
                    pw.println(event);
                    pw.println("</td>");
                    pw.println("</tr>");
                }
            }
            catch(ParserConfigurationException | SAXException e){
                pw.println("Problem: "+e);
            }
        }
        pw.println("</table>");
        pw.println("</body></html>");
    }
    
    String getText( Element e, String tag){
        var lst = e.getElementsByTagName(tag);
        if( lst.getLength() == 0 )
            return "";
        var event = lst.item(0).getTextContent();
        return event;
    }
}
