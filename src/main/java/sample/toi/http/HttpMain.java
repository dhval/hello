package sample.toi.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dhval on 3/24/15.
 */
@Service
public class HttpMain {
    private static final Logger LOG = LoggerFactory.getLogger(HttpMain.class);
    final static Pattern pattern = Pattern.compile("([0-9]*)\\.json$");
    private static int MIN = 36706546-1;

    private static volatile Integer counter = 0;

    public static int increment() {
        synchronized (counter) {
            return ++counter;
        }
    }

    public static int jump() {
        synchronized (counter) {
            counter +=1000;
            return counter++;
        }
    }

    @Value("${toi.path}")
    private String BASE_PATH;

    private int setStart() throws Exception {
        int start = 0, last = 0;
        try(DirectoryStream<Path> dir = Files.newDirectoryStream(Paths.get(BASE_PATH))) {
            for(Path path: dir) {
                last = start;
                Matcher matcher = pattern.matcher(path.toString());
                if (matcher.find()) {
                    start = Integer.parseInt(matcher.group(1));
                    LOG.info(start + " --- " + last);
                    if (last > MIN && Math.abs(last - start) > 1000)
                        return last;
                }
            }
        }
        return start;
    }

    public static final int WORKER_SIZE = 50000;
    public static final int POOL_SIZE = 100;
    public  static void execute(int start) {
        ExecutorService executor = Executors.newFixedThreadPool(POOL_SIZE);//creating a pool of 10 threads
        for (int i = 1; i <= POOL_SIZE; i++) { // worker count
            Runnable worker = new ApacheHttpClient(WORKER_SIZE); // worker size
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {   }
        System.out.println("Finished all threads");
    }
//40711412√√
    public static void main(String[] s) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-toi.xml");
        HttpMain main = ctx.getBean(HttpMain.class);
        counter = main.setStart();
        counter = 6_030_000;
        LOG.info("-----------------------------------------");
        LOG.info("-----------------------------------------");
        LOG.info("----------------" + counter + "----------");
        LOG.info("-----------------------------------------");
        LOG.info("-----------------------------------------");
        execute(counter);
    }

}
