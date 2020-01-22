import java.awt.Color;
import java.awt.image.BufferedImage;
    import java.io.*;
import java.util.Calendar;
import java.util.Date;
import javax.imageio.ImageIO;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.*;

    @WebServlet(urlPatterns={"/clock"})
    public class PngTest extends HttpServlet{
        @Override
        public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
            int width=512;
            int height=512;
            int centerx = width/2;
            int centery = height/2;
            var img = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
            var cal = Calendar.getInstance();
            double hourAngle = cal.get(Calendar.HOUR) / 12.0 * 2.0 * Math.PI;
            double minuteAngle = cal.get(Calendar.MINUTE) / 60.0 * 2.0 * Math.PI;
            var G = img.getGraphics();
            G.setColor(Color.WHITE);
            G.fillRect(0,0,width,height);
            G.setColor(Color.BLACK);
            double x = centerx + width*0.25 * Math.sin(hourAngle);
            double y = centery + height*0.25 * Math.cos(hourAngle);
            y = height-y;
            G.drawLine(centerx,centery,(int)x,(int)y);
            x = centerx + width*0.45 * Math.sin(minuteAngle);
            y = centery + height*0.45 * Math.cos(minuteAngle);
            y = height-y;
            G.drawLine(centerx,centery,(int)x,(int)y);
            G.dispose();
            var bos = new ByteArrayOutputStream();
            ImageIO.write(img, "png", bos);
            resp.setContentType("image/png");
            var wr = resp.getOutputStream();
            wr.write(bos.toByteArray());
        }
    }
