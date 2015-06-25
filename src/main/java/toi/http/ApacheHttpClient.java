package toi.http;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

public class ApacheHttpClient implements Runnable {

    private final static String URL_TOI = "http://timesofindia.indiatimes.com/commentsdata.cms?curpg=1&pagenum=1&size=1000&pcode=TOI&appkey=TOI&sortcriteria=AgreeCount&msid=";
    private int size;

    public ApacheHttpClient(int size) {
        this.size = size;
    }

    public void run() {

        HttpClient httpClient = new DefaultHttpClient();
        try {
            for (int i = 0; i <= size; i++) {
                int counter = HttpMain.increment();
                File f = new File("/Users/dhval/toi_json/" + counter + ".json");
                if (f.exists()) {
                    System.out.println("Exists : " + counter);
                    HttpMain.jump();
                    continue;
                }
                if (counter % 25 ==0) {
                    long threadId = Thread.currentThread().getId();
                    System.out.println("Thread# " + threadId + " count# " + counter);
                }
                HttpGet httpGetRequest = new HttpGet(URL_TOI + Integer.toString(counter));
                HttpResponse httpResponse = httpClient.execute(httpGetRequest);
                if(httpResponse.getStatusLine().getStatusCode() != 200){
                    System.out.println(httpResponse.getStatusLine().getStatusCode());
                    continue;
                }
                HttpEntity entity = httpResponse.getEntity();
               byte[] buffer = new byte[1024];
                if (entity != null) {
                    InputStream inputStream = entity.getContent();
                    try {
                        int bytesRead = 0;
                        BufferedInputStream bis = new BufferedInputStream(inputStream);
                        StringBuffer sb = new StringBuffer();
                        while ((bytesRead = bis.read(buffer)) != -1) {
                            String chunk = new String(buffer, 0, bytesRead);
                            sb.append(chunk);
                        }
                        if (sb.length() > 10) {
                            System.out.println("Write to disk ..." + counter);
                            Files.write(Paths.get("/Users/dhval/toi_json/" + counter + ".json"), sb.toString().getBytes());
                        }
                    } finally {
                        try {
                            inputStream.close();
                        } catch (Exception ignore) {
                        }
                    }
                }
            }
        } catch (ClientProtocolException e) {
            // thrown by httpClient.execute(httpGetRequest)
            e.printStackTrace();
        } catch (IOException e) {
            // thrown by entity.getContent();
            e.printStackTrace();
        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpClient.getConnectionManager().shutdown();
        }
    }
}