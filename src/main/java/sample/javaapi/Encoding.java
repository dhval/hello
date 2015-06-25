package sample.javaapi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.UnsupportedEncodingException;

import java.nio.charset.Charset;
import java.nio.ByteBuffer;

/**
 *
 * @author c-dmudawal
 * //TODO
 *      1. unix to nuxi
 */
public class Encoding {
    
   /** 
      http://stackoverflow.com/questions/6393873/how-to-get-the-binary-values-of-the-bytes-stored-in-byte-array
      http://stackoverflow.com/questions/12310017/how-to-convert-a-byte-to-its-binary-string-representation
   **/
    static String print(Byte... bytes) {
        StringBuilder sb = new StringBuilder();
        for(Byte byteVal : bytes) {
            String val = Integer.toBinaryString(byteVal & 255 | 256).substring(1);
            System.out.println(val);
            sb.append(val);
        }
        return sb.toString();
    }
    
    static void api() throws UnsupportedEncodingException {
        String text = "A";
        byte[] utf8Bytes = text.getBytes("UTF-8");
        
        // Specify encoding in String
        String s1 = new String(utf8Bytes, "UTF-8");
        
        // Specify string using unicode #65
        String s2 = "\u0041";
        
        
    }

    public static void main(String[] args) throws Exception {
        byte[] utf8Bytes = "B".getBytes("UTF-8");
    
        
        Byte A = (byte) 0x41;
        ByteBuffer a = Charset.forName("UTF-8").encode("A");

        System.out.println("" + Integer.toBinaryString(A));
        
    }

  
}
