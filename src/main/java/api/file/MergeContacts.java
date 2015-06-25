package api.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dhval
 */
public class MergeContacts {
    
    
        private static String[] split(String s) {
        String[] array = {"","","","","","","","","",""};
        String[] cols = s.split(",");
        int i = 0;
        for(String str : cols) {
            array[i++] = str.trim().replace("\"", "").replace("'", "");
        }
        return array;
    }
    
    private static List<Contact> getSupportList() throws FileNotFoundException, IOException {
        List<Contact> list = new ArrayList<Contact>();
        BufferedReader br = new BufferedReader(new FileReader(new File("helpdesk.txt")));
        String line = br.readLine();
        while ((line = br.readLine()) != null) {
        Contact contact = new Contact();
            String[] cols = split(line);
            contact.agency = cols[0];
            contact.name = cols[1];
            contact.email = cols[2];
            contact.workPhone = cols[3];
            contact.homePhone = cols[4];
            contact.cellPhone = cols[5];
            contact.comment = cols[6];
            contact.type = "helpdesk";
            list.add(contact);
        }
        return list;
    }

    private static List<Contact> getERList() throws FileNotFoundException, IOException {
        List<Contact> list = new ArrayList<Contact>();
        BufferedReader br = new BufferedReader(new FileReader(new File("contacts.txt")));
        String line = br.readLine();
        while ((line = br.readLine()) != null) {
        Contact contact = new Contact();
            String[] cols = split(line);
            contact.county = cols[0];
            contact.department = cols[1];
            contact.vendor = cols[3];
            contact.system =  cols[2];
            contact.name = cols[4];
            contact.email = cols[5];
            contact.workPhone = cols[6];
            contact.comment = cols[7];
            contact.type = "er";
            list.add(contact);
        }
        return list;
    }

    static class Contact {

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
        private String comment = "";

        @Override
        public String toString() {
            return "Contact{" + "type=" + type + ", name=" + name + ", email=" + email + ", county=" + county + ", agency=" + agency + ", department=" + department + ", system=" + system + ", vendor=" + vendor + ", workPhone=" + workPhone + ", cellPhone=" + cellPhone + ", homePhone=" + homePhone + ", comment=" + comment + '}';
        }

     
        
    }

    
    
    private static void toString(String[] arr) {
        StringBuilder sb = new StringBuilder();
        for(String s : arr) {
            sb.append(s).append("--");
        }
        System.out.println("BAD ! " + arr.length + sb.toString());
    }

    public static void dostuff(String[] s) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("support.txt")));
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("merge.txt")));

        String line;
        while ((line = br.readLine()) != null) {
            String[] cols = line.split(",");
            if (cols.length < 7) {
                toString(cols);
                continue;
            }
            String col9 = (cols.length > 9) ? cols[9].trim() : "";
            String col8 = (cols.length > 8) ? cols[8].trim() : "";
            String col7 = (cols.length > 7) ? cols[7].trim() : "";
            String col6 = (cols.length > 6) ? cols[6].trim() : "";
            String department;
            if (col9.equals("")) {
                department = cols[2];
            } else {
                department = col9 + " (" + cols[2] + ")";
            }
            StringBuilder sb = new StringBuilder();
            String agency = sb.append(cols[6]).append(" ").append(col7).append(" ").append(col8).toString().trim();
            sb = new StringBuilder();
            sb.append(agency).append(", ").append(cols[1]).append(" ").append(cols[0]).append(", ").append(cols[3]);
            sb.append(", ").append(cols[4]).append(", ").append(cols[5]).append(", ,").append(department);
            System.out.println(sb.toString());
        }
    }
    
    private static String ftColumn(String s) {
        if (s == null || s.trim().equals("")) 
            return "\"\"";
        return s;
    }
    
    public static void main(String[] s) throws FileNotFoundException, IOException {
                    List<MergeContacts.Contact> contacts = getSupportList();
            contacts.addAll(getERList());
            int count = 1;
            for(MergeContacts.Contact c : contacts) {
                StringBuilder sb = new StringBuilder();
                   sb.append(Integer.toString(count++)).append(", ");
                   sb.append(ftColumn(c.agency)).append(", ");
                   sb.append(ftColumn(c.cellPhone)).append(", ");
                   sb.append(ftColumn(c.comment)).append(", ");
                   sb.append(ftColumn(c.county)).append(", ");
                   sb.append(ftColumn(c.department)).append(", ");
                   sb.append(ftColumn(c.email)).append(", ");
                   sb.append(ftColumn(c.homePhone)).append(", ");
                   sb.append(ftColumn(c.name)).append(", ");
                   sb.append(ftColumn(c.system)).append(", ");
                   sb.append(ftColumn(c.type)).append(", ");
                   sb.append(ftColumn(c.vendor)).append(", ");
                   sb.append(ftColumn(c.workPhone));
                   System.out.println(sb.toString());
            }

    }
}
