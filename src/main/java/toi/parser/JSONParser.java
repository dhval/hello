package toi.parser;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dhval on 3/23/15.
 */
@Component
public class JSONParser {

    @Value("${toi.path}")
    private String BASE_PATH;
    private String filePath;
    private int fileId;

    private static final Logger LOG = LoggerFactory.getLogger(JSONParser.class);

    final static Pattern pattern = Pattern.compile("^([0-9]*) days ago");

    private Date calcDate(String text) throws IOException {
        Path path = Paths.get(filePath);
        BasicFileAttributes attributes =
                Files.readAttributes(path, BasicFileAttributes.class);
        FileTime creationTime = attributes.creationTime();
        Matcher m = pattern.matcher(text);
        Integer days = 0;
        if (m.find())
            days = Integer.parseInt(m.group(1));
        else
            return null;
        Date date = new Date(creationTime.toMillis() - days * 24 * 3600 * 1000l); //Subtract n days
        return date;
    }

    private StringBuilder readFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
        }
        return sb;
    }

    private void addUser(List<TOIObject.User> users, JSONObject json) throws  Exception {
        if (json.isNull("user_detail")) {
            return;
        }
        TOIObject.User user = new TOIObject.User();
        user.date = json.isNull("C_D") ? null : calcDate(json.get("C_D").toString());
        user.commentId = Long.parseLong(json.get("GID").toString());
        user.comment = StringUtils.abbreviate(json.get("C_T").toString(), 250);
        json = json.getJSONObject("user_detail");
        if (json.isNull("_id")) {
            LOG.warn("Uanble to find _id in userdetail, " + json.toString());
            return;
        }
        user.id = Long.parseLong(json.get("_id").toString());
        user.uid = json.get("uid").toString();
        user.sso = json.get("sso").toString();
        user.first = json.get("F_N").toString();
        user.last = json.get("L_N").toString();
        user.city = json.isNull("CITY") ? null : json.get("CITY").toString();
        users.add(user);
    }

    private TOIObject readJSON(StringBuilder sb) throws  Exception {
        JSONArray array = new JSONArray(sb.toString());
        if (array.length() < 2)
            return null;
        TOIObject object = new TOIObject();
        List<TOIObject.User> users = new ArrayList<TOIObject.User>();
        // The first array object just has meta information.
        JSONObject json = array.getJSONObject(1);
        object.msid = Long.parseLong(json.get("msid").toString());
        object.tittle =  json.isNull("tet") ?
                null : StringUtils.abbreviate(json.get("tet").toString(), 250);
        object.url = json.isNull("teu") ?
                null : StringUtils.abbreviate(json.get("teu").toString(), 250);
        for (int i = 1; i < array.length(); i++) {
            json = array.getJSONObject(i);
            addUser(users, json);
            if (!json.isNull("CHILD")) {
                JSONArray children = json.getJSONArray("CHILD");
                for (int count = 0; count < children.length(); count++) {
                    addUser(users, children.getJSONObject(count));
                }
            }
        }
        object.users = users;
        return object;
    }

    public TOIObject read(int fileId) throws Exception {
        this.fileId = fileId;
        filePath = BASE_PATH + fileId + ".json";
        File file = new File(filePath);
        if (!file.exists())
            return null;
        StringBuilder sb = readFile();
        if (sb.length() <5)
            return  null;
        TOIObject object = readJSON(sb);
        return object;
    }

    public static void main(String[] s) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-toi.xml");
        JSONParser main = ctx.getBean(JSONParser.class);
        TOIObject object = main.read(26645751);
        LOG.info(object.url);
        LOG.info("Comments #" + object.users.size());
        for (TOIObject.User user : object.users) {
            if (user.date != null)
                LOG.info(user.date.toString());
            LOG.info(user.sso);
        }
    }
}
