package sample.chatbox;

import sample.chatbox.MyHttpClient;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class PrxoyChecker {

    private static Logger logger = Logger.getLogger(PrxoyChecker.class);
    private static ProxyManager proxyManager = new ProxyManager();
    private static String TEST_SITE = "www.cantoni.mobi";
    private static int TIME_OUT = 110000;

    private static synchronized String getData() {
        String data = proxyManager.getDataData(false);
        if (data != null) {
            proxyManager.addData(data, 99);
        }
        return data;
    }

    private static synchronized void updateData(String data, int status) {
        proxyManager.addData(data, status);
    }

    public static void main(String args[]) {
        int attempt1 = 5;
        int attempt2 = 30;
        try {
            for (int i = 0; i <= attempt2; i++) {
                new PrxoyChecker().doCheck(attempt1);
            }

        } catch (Exception e) {
            logger.error("", e);
        } finally {
            try {
                // proxyManager.shutdown();
            } catch (Exception e) {
                logger.error("", e);
            }
        }
    }

    public void doCheck(int attempt) throws InterruptedException, Exception {
        int count = 0;
        ClientThread[] u = new ClientThread[attempt];
        while (count < attempt) {
            String data = getData();
            u[count] = new ClientThread(data);
            u[count].start();
            count++;
        }
        for (int i = 0; i < attempt; i++) {
            if (u[i].isAlive()) {
                u[i].join();
                i = 0;
            }
        }
    }

    class ClientThread extends Thread {

        String data;
        MyHttpClient httpClient;
        CBValidations validate = new CBValidations();

        public ClientThread(String data) {
            this.data = data;
            httpClient = new MyHttpClient(TEST_SITE, 80);
            httpClient.setConTimeout(TIME_OUT);
            httpClient.setSocketTimeout(TIME_OUT);
            validate.setContains("<title>Mobile Sites  - Cantoni.mobi</title>");
        }

        public void run() {
            int status = 1;
            try {
                if (data == null) {
                    return;
                }
                String[] arr = StringUtils.split(data, ":");
                httpClient.setProxy(arr[0], Integer.parseInt(arr[1]));
                StringBuffer out = httpClient.doGet("/", null, null);
                logger.info(out);
                status = validate.isValidGetResponse(httpClient.getStatusCode(), out);
            } catch (Exception e) {
                logger.error("" + e);
                status = validate.STATUS_IOE;
            } finally {
                updateData(data, status);
                logger.info(data + " " + status + " " + httpClient.getStatusCode());
            }
        }
    }
}
