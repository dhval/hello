/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.chatbox;

import java.io.*;

/**
 *
 * @author dhval
 */
public class FileParser {

    BufferedReader galiStream = null;
    BufferedReader proxyStream = null;

    public FileParser() {
        try {
        galiStream = new BufferedReader(new FileReader("slang"));
        proxyStream = new BufferedReader(new FileReader("proxy"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getGali() throws Exception {
        String gali = galiStream.readLine();
        return gali;
    }

    public String getProxy() throws Exception {
        String gali = proxyStream.readLine();
        return gali;
    }
}
