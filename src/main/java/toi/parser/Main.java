package toi.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import toi.http.ApacheHttpClient;
import toi.entity.MySQLDAO;
import toi.entity.Article;
import toi.entity.Comment;
import toi.entity.User;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dhval on 3/22/15.
 */
@Service
public class Main {

    private static int start = 26728317, end;
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);
    final static Pattern pattern = Pattern.compile("([0-9]*)\\.json$");

    @Autowired
    private JSONParser parser;

    @Autowired
    private MySQLDAO sqldao;

    @Value("${toi.path}")
    private String BASE_PATH;

    private void add(int file) throws Exception {
        TOIObject object = parser.read(file);
        if (object == null) {
            LOG.warn("Bad file #" + file);
            return;
        } else if (sqldao.findArticleById(object.msid) != null) {
            LOG.warn("Already Processed #" + file);
            return;
        }
        LOG.info(object.url);
        LOG.info("Comments #" + object.users.size());
        Article article = TOIObject.toArticle(object);
        article = sqldao.save(article);
        for (TOIObject.User user : object.users) {
            User u = TOIObject.toUser(object,user);
            u = sqldao.save(u);
            Comment c = TOIObject.toComment(object,user);
            c = sqldao.save(c);
        }
    }

    private List<Integer> files() throws Exception {
        List<Integer> files = new ArrayList<>();
        try(DirectoryStream<Path> dir = Files.newDirectoryStream(Paths.get(BASE_PATH))) {
            for(Path path: dir) {
                Matcher matcher = pattern.matcher(path.toString());
                if (matcher.find())
                    files.add(Integer.parseInt(matcher.group(1)));
            }
        }
        return files;
    }

    public static void main(String[] s) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-toi.xml");
        Main main = ctx.getBean(Main.class);
        for(Integer file : main.files()){
            LOG.info("File #" + file);
            main.add(file);
        }
    }
}
