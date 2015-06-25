package api.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Dhval
 */
public class FileFormater {
    
    static int COL_SIZE = 13;
    
     static class Contact {

        private int id; 
        private String type = "";
        private String name = "";
        private String email = "";
        private String county = "";
        private String agency = "";
        private String department = "";
        private String system = "";
        private String vendor = "";
        private String workPhone = "";
        private String cellPhone = "";
        private String homePhone = "";
        private String faxNumber = "";
        private String comment = "";

        @Override
        public String toString() {
            return "" + id + "," + agency + "," + cellPhone + "," + comment + "," + county + ","
                    + department + "," + email + "," + homePhone + "," + name + "," + system + ","
                    + "helpdesk," + vendor + "," + workPhone +  "," + faxNumber  + "\n";
        }     
        
    }
     
     static String getCol(int n, String[] cols) {
         return (cols.length > n) ? cols[n] : "";
     } 
    
    static void readFile(BufferedWriter bw) throws FileNotFoundException, IOException {
        File file = new File("helpdesk.txt");
        BufferedReader br  = new BufferedReader(new FileReader(file));
        String line;
        while((line = br.readLine()) != null) {
            String[] cols = line.split(",");
            if (StringUtils.isBlank(cols[0]) || !StringUtils.isNumeric(cols[0]) ) {
                System.out.println("INVALID -- LINE" + cols.length);
                System.out.println(line);
                continue;
            }
            Contact contact = new Contact();
            contact.id = Integer.parseInt(cols[0]);
            contact.agency = getCol(1, cols);
            contact.county = getCol(2, cols);
            contact.department = getCol(3, cols);
            contact.system = getCol(4, cols);
            contact.vendor = getCol(5, cols);
            contact.name = getCol(6, cols);
            contact.email = getCol(7, cols);
            contact.workPhone = getCol(8, cols);
            contact.cellPhone = getCol(9, cols);
            contact.homePhone = getCol(10, cols);
            contact.faxNumber = getCol(11, cols);
            contact.comment = getCol(12, cols);
            bw.write(contact.toString());
        }
    }
    
     public static void main( String[] args ) throws FileNotFoundException, IOException    {
         BufferedWriter bw = new BufferedWriter(new FileWriter(new File("contacts.csv")));
         readFile(bw);
         bw.close();
     }
}
