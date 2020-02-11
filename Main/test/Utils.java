
import java.net.URL;

/**
 *
 * @author nickb
 */
public class Utils {
    
    static String fetch(String... allurls) throws Exception{
        String str=null;
        byte[] returnedData=new byte[]{0};  //dummy
        for(String oneurl: allurls ){
            var url = new URL("http://localhost:2020"+oneurl);
            var conn = url.openConnection();
            conn.connect();
            var istr = conn.getInputStream();
            returnedData = istr.readAllBytes();
        }
        return new String(returnedData,0,returnedData.length);
    }
    
}
