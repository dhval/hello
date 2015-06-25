package api.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Dhval
 */
public class DHExtracter {
    
    private static String readFile() throws FileNotFoundException, IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new FileReader(new File("dhfields.txt")));
        String line;
        while((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }
    
    private static String addTab(String record) {
        String out = "";
        int length = record.length();
        for(int i=0; i< length; i+=65) {
             int size = (i+65 < length) ? i+65 : length;   
             out += record.substring(i, size) + "\t";
         }
        return out;
    }
    
    private static String printText(String line)  throws IOException {
        int length = line.length();
        int begin = 0;
        String out = "";
        for(int i=0; i<length; i+=65) {
            char delimiter = line.charAt(i+3);
            if (delimiter == 'Y' || delimiter == 'A') {
                out = out + "\n" + addTab(line.substring(begin, i));
                begin = i;
            }
        }
        // printText last record
        if (begin < length)
            out = out + "\n" + addTab(line.substring(begin, length));
        return out;                
    }
    
    static String createEl(String line, int begin, int end) {
        String out = "";
        int length = line.length();
        for(int i=begin; i<length && i <end; i+=65) {
            String el = line.substring(i, i+3);
            int size = (i+65 < length) ? i+65 : length;
            String data = line.substring(i+4, size).trim();
            out += "<" + el + ">" + data + "</" + el + ">";
        }
        char delimiter = line.charAt(begin+3);
        if (!out.equals(""))
            out = "<Data>" + "<Indicator>" + delimiter + "</Indicator>" + out + "</Data>";
        return out;
    }

    private static String printXML(String line)  throws IOException {
        int length = line.length();
        int begin = 0;
        String out = "";
        for(int i=0; i<length; i+=65) {
            char delimiter = line.charAt(i+3);
            if (delimiter == 'Y' || delimiter == 'A') {
                out = out + createEl(line, begin, i);
                begin = i;
            }
        }
        // printText last record
        if (begin < length)
            out = out + createEl(line, begin, length); 
        return out;
    }
    
    private static void writeXML(String text) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("out.xml")));
        bw.write(text);
        bw.close();
    }
    
    public static void main(String[] s) throws IOException {
        String line = readFile();
        // writeXML("<Root>" + printXML(line) + "</Root>");
        writeXML(printText(line));
    }
}
