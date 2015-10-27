package sample.chatbox;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.cookie.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.log4j.Logger;

public class MyHttpClient extends HttpClient {

    private static Logger logger = Logger.getLogger(MyHttpClient.class);
    final String HOST_SITE;
    final int HOST_PORT;
    private String COOKIE_POLICY = CookiePolicy.BROWSER_COMPATIBILITY;
    private CookieSpec cookiespec = null;
    private int statusCode = -1;

    public void setProxy(String PROXY_HOST, int PROXY_PORT) {
        getHostConfiguration().setProxy(PROXY_HOST, PROXY_PORT);
    }

    public void setSocketTimeout(int socketTimeout) {
        getParams().setSoTimeout(socketTimeout);
    }

    public void setConTimeout(int connectionTimeout) {
        getParams().setConnectionManagerTimeout(connectionTimeout);
    }

    public MyHttpClient(String HOST_SITE, int HOST_PORT) {
        super();
        this.HOST_SITE = HOST_SITE;
        this.HOST_PORT = HOST_PORT;
        getHostConfiguration().setHost(HOST_SITE, HOST_PORT, "http");
        getParams().setCookiePolicy(COOKIE_POLICY);
        cookiespec = CookiePolicy.getDefaultSpec();
    }

    public StringBuffer doGet(String url, Header[] requestHeader, NameValuePair[] query) throws java.io.IOException {
        GetMethod authget = null;
        StringBuffer out = null;
        try {
            authget = new GetMethod(url);
            if (requestHeader != null && requestHeader.length > 0) {
                for (Header header : requestHeader) {
                    authget.addRequestHeader(header);
                }
            }
            if (requestHeader != null && requestHeader.length > 0) {
                authget.setQueryString(query);
            }
            statusCode = executeMethod(authget);
            logger.trace("Get-Response: " + authget.getStatusLine().toString());
            InputStream da = authget.getResponseBodyAsStream();
            if (da != null) {
                out = new StringBuffer();
                BufferedReader buf = new BufferedReader(new InputStreamReader(da));
                String line;
                while ((line = buf.readLine()) != null) {
                    out.append(line);
                }
            }

        } finally {
            // else HttpClient will wait indefinitely for a connection to free up so that it can be reused.
            if (authget != null) {
                authget.releaseConnection();
            }
        }
        return out;
    }

    public InputStream doPost(String url, Header[] requestHeader, NameValuePair[] query) throws java.io.IOException {
        PostMethod authpost = null;
        try {
            authpost = new PostMethod(url);
            if (requestHeader != null && requestHeader.length > 0) {
                for (Header header : requestHeader) {
                    authpost.addRequestHeader(header);
                }
            }
            if (requestHeader != null && requestHeader.length > 0) {
                authpost.setRequestBody(query);
            }
            statusCode = executeMethod(authpost);
            logger.trace("Get-Response: " + authpost.getStatusLine().toString());
            return authpost.getResponseBodyAsStream();
        } finally {
            // else HttpClient will wait indefinitely for a connection to free up so that it can be reused.
            if (authpost != null) {
                authpost.releaseConnection();
            }
        }
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Cookie[] getCookies() {
        Cookie[] logoncookies = cookiespec.match(
                HOST_SITE, HOST_PORT, "/", false, getState().getCookies());
        return logoncookies;
    }
}