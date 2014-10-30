package sample.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author Dhval
 */
public class HttpClient {
    
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

        private String type;
        private String name;
        private String email;
        private String county;
        private String agency;
        private String department;
        private String system;
        private String vendor;
        private String workPhone;
        private String cellPhone;
        private String homePhone;
        private String comment;

        @Override
        public String toString() {
            return "Contact{" + "type=" + type + ", name=" + name + ", email=" + email + ", county=" + county + ", agency=" + agency + ", department=" + department + ", system=" + system + ", vendor=" + vendor + ", workPhone=" + workPhone + ", cellPhone=" + cellPhone + ", homePhone=" + homePhone + ", comment=" + comment + '}';
        }
        
        
    }

    public static void main(String[] args) {
        try {


            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost quest = new HttpPost("http://mints.mooo.com/helpdesk/add");
            List<Contact> contacts = getSupportList();
            contacts.addAll(getERList());
            for(Contact c : contacts) {
                
              if (c.email.contains(": ")) {
                  int index = c.email.indexOf(": ");
                  c.email = c.email.substring(index+1).trim();
              }

            String input = "{\"name\": \""+c.name +"\","
                   + " \"comment\": \""+c.comment +"\","
                    + " \"email\": \""+c.email +"\","
                    + " \"county\": \""+c.county +"\","
                    + " \"agency\": \""+c.agency +"\","
                    + " \"department\": \""+c.department +"\","
                    + " \"system\": \""+c.system +"\","
                    + " \"vendor\": \""+c.vendor +"\","
                    + " \"workPhone\": \""+c.workPhone +"\","
                    + " \"homePhone\": \""+c.homePhone +"\","
                    + " \"cellPhone\": \""+c.cellPhone +"\","
                    + " \"type\": \""+c.type +"\"}";
            StringEntity entity = new StringEntity(input);
            quest.setEntity(entity);

            quest.setHeader("Accept", "application/json");
            quest.setHeader("Content-Type", "application/json");
            quest.setHeader("User-Agent", HttpHeaders.USER_AGENT);

            HttpResponse response = httpClient.execute(quest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : \n" + c.toString() + "\n"
                        + response.getStatusLine().getStatusCode() + response.toString());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String output;
            System.out.println("Output from Server .... \n" + c.toString());
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            }
            httpClient.getConnectionManager().shutdown();

        } catch (ClientProtocolException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

}
