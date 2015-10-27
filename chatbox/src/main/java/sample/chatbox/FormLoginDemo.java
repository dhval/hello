package sample.chatbox;

import sample.chatbox.FileParser;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.cookie.CookieSpec;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.params.HttpParams;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class FormLoginDemo {

    private static Logger logger = Logger.getLogger(FormLoginDemo.class);
    static String PROXY_HOST = "127.0.0.1";
    static int PROXY_PORT = 8118;
    static final String LOGON_SITE = "www4.cbox.ws";
    static final int LOGON_PORT = 80;
    static HttpClient client = null;
    static GetMethod authget = null;
    static PostMethod authpost = null;
    static CookieSpec cookiespec = null;
    static int lpVal = 1519247;
    static String nme = "iPersists";
    static String msg;
    static List<String> messages;
    static int success = 0;
    static FileParser file = new FileParser();
    static int status = 0;
    static final int STATUS_SUCCESS = 1;
    static final int STATUS_BAN = 2;
    static final int STATUS_IOE = 3;
    static final int STATUS_BADPROXY = 4;
    static final int STATUS_FAILED = 5;

    public FormLoginDemo() {
        super();
    }

    public static void main(String[] args) throws Exception {
        String startNme = nme;
        int county = 1338;
        Random ran = new Random();
        messages = new ArrayList<String>();
        String message = file.getGali();
        while (message != null) {
            messages.add(message);
            message = file.getGali();
        }
        ProxyManager proxyManager = new ProxyManager();
        String proxy;
        int attempt = 0;
        int boomTime = 1000;
        while ((proxy = proxyManager.getDataData(true)) != null
                && attempt < 300 && success < 15) {
            String[] arr = StringUtils.split(proxy, ":");
            PROXY_HOST = arr[0];
            PROXY_PORT = Integer.parseInt(arr[1]);
            init();
            boolean isErr = false;
            int pcnt = 0;
            status = STATUS_SUCCESS;
            while ((status == STATUS_SUCCESS) && pcnt < 10) {
                lpVal++;
                nme = startNme + Integer.toString(county++);
                int random = ran.nextInt(messages.size());
                msg = "Request#" + county + " " + messages.get(0);
                logger.info(nme + ":" + msg);
                // doGet();
                status = STATUS_SUCCESS;
                isErr = doPost();
                if (status == STATUS_SUCCESS) {
                    success++;
                    logger.info("success :- " + success);
                    Thread.sleep(200);
                    boomTime = boomTime + 100000;
                }else {
                    boomTime = boomTime - 10000;
                }
                if (boomTime < 1000) boomTime = 1000;
                 pcnt++;
                logger.info(proxy + " attempt :- " + ++attempt +
                        " status:- " + status + " success:- " + success);
            }
            Thread.sleep(boomTime);
            if (proxy != null) {
                proxyManager.addData(proxy, status);
            }
        }
        proxyManager.shutdown();
    }

    static void init() throws Exception {
        client = new HttpClient();
        client.getHostConfiguration().setProxy(PROXY_HOST, PROXY_PORT);
        client.getHostConfiguration().setHost(LOGON_SITE, LOGON_PORT, "http");
        client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
        client.getParams().setConnectionManagerTimeout(120000L);
        client.getParams().setSoTimeout(120000);
        cookiespec = CookiePolicy.getDefaultSpec();
    }

    static void doGet() throws java.io.IOException {
        authget = new GetMethod("/box");
        authget.addRequestHeader("User-Agent",
                "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.0.10) IPHONE");
        authget.addRequestHeader("User-Agent",
                "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.0.10) IPHONE");
        NameValuePair boxid = new NameValuePair("boxid", "3578082");
        NameValuePair boxtag = new NameValuePair("boxtag", "9117");
        NameValuePair chash = new NameValuePair("c", "");
        NameValuePair cid = new NameValuePair("p", "");
        NameValuePair t = new NameValuePair("sec", "ar");
        authget.setQueryString(new NameValuePair[]{boxid, boxtag, chash, cid, t});

        client.executeMethod(authget);
        logger.info("Login form get: " + authget.getStatusLine().toString() + " " + authget.getResponseBodyAsString());
        authget.releaseConnection();
    }

    static void showCookie() {
        Cookie[] logoncookies = cookiespec.match(
                LOGON_SITE, LOGON_PORT, "/", false, client.getState().getCookies());
        System.out.println("Logon cookies:");
        if (logoncookies.length == 0) {
            logger.info("No Cookie !!!");
        } else {
            for (int i = 0; i < logoncookies.length; i++) {
                logger.info("- " + logoncookies[i].toString());
            }
        }

    }

    static boolean doPost() {
        try {
            authpost = new PostMethod("/box/?boxid=3578082&boxtag=9117&sec=submit");
            // Prepare login parameters
            NameValuePair val1 = new NameValuePair("aj", "x");
            NameValuePair val2 = new NameValuePair("auth", "");
            NameValuePair val3 = new NameValuePair("eml", "");
            NameValuePair val4 = new NameValuePair("fkey", "");
            NameValuePair val5 = new NameValuePair("key", "");
            NameValuePair val6 = new NameValuePair("lp", Integer.toString(lpVal));
            NameValuePair val7 = new NameValuePair("nme", nme);
            NameValuePair val8 = new NameValuePair("pic", "");
            NameValuePair val9 = new NameValuePair("pst", msg);
            authpost.setRequestBody(
                    new NameValuePair[]{val1, val2, val3, val4, val5, val6, val7, val8, val9});
            int cStatus = client.executeMethod(authpost);
            logger.info(authpost.getResponseBodyAsString() +
                    " " + authpost.getStatusLine().toString());
            authpost.releaseConnection();
            if (cStatus != HttpStatus.SC_OK) {
                status = STATUS_BADPROXY;
                return true;
            }
            return (isError(authpost.getResponseBodyAsString()));
        } catch (Exception e) {
            logger.info("failed to post" + e);
            status = STATUS_IOE;
            return true;
        }
    }

    static boolean isError(String msg) {
        if (StringUtils.contains(msg, "you have been banned")) {
            status = STATUS_BAN;
            return true;
        }
        if (!StringUtils.contains(msg, nme)) {
            status = STATUS_FAILED;
            return true;
        }
        return false;
    }
}