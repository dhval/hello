package sample.javaapi.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Hello world!
 *
 * StringTokenizer has been deprecated use split instead.
 * 
 */
public class JSONPrinter 
{
    private static boolean isEmpty(String str) {
        if (str == null || str.trim().length() ==0)
            return true;
        return false;
    }
    
    private static int rowId = 1;
    
    private static String toJSON(Map<String,String> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        Iterator<Map.Entry<String,String>> itr = map.entrySet().iterator();
        while(itr.hasNext()) {
            Map.Entry<String,String> entry = itr.next();
            sb.append("\"").append(entry.getKey()).append("\": ");
            sb.append("\"").append(entry.getValue()).append("\"");
            if (itr.hasNext()) sb.append(", ");
        }
        sb.append(" } ");
        return sb.toString();
    }
    
    private static Map<String,String> parseLine(List<String> list, String line) {
        Map<String,String> map = new HashMap<String, String>();
        map.put("id", Integer.toString(rowId++));
        String element;
        int count = 0;
        String[] strs = line.split(",");
        for(String str :strs) {
            if (isEmpty(str))
                str = "";
            else // remove problem chars
                str = str.replace("\"", "").replace("'", "");
            if (count <list.size()) map.put(list.get(count), str);
            count++;
        }
        return map;
    }
    
    private static List<String> parseFirstLine(String line) {
        List<String> list = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(line, ",");
        while(st.hasMoreTokens()) {
            String token = st.nextToken();
            if (isEmpty(token))
                token = "?";
            else
                token = token.trim().toLowerCase().replace(" ", "");
            list.add(token);
        }
        return list;
    }
    
    public static void main( String[] args ) throws FileNotFoundException, IOException
    {
        File file = new File("helpdesk.txt");
        BufferedReader br  = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("helpdesk.json")));
        // read first line, acts as column header
        List<String> list = parseFirstLine(br.readLine());
         System.out.println(list);
        String line;
        boolean isFirstLine = true;
        bw.write("[ ");
        while ((line = br.readLine()) != null) {
            if (!isFirstLine) // add this at end of everline except the first one.
                bw.write(",");
            isFirstLine = false;
            Map<String, String> map = parseLine(list, line);
            String json = toJSON(map);
            System.out.println(json);
            bw.write(json);
        }
        bw.write(" ]");
        bw.close();
        System.out.println( "Hello World!" );
    }
    public static final String ER_CONTACTS_OUTPUT = "contacts.json";
    public static final String ER_CONTACTS_INPUT = "contacts.txt";
}
