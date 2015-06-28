package wls;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import weblogic.security.internal.SerializedSystemIni;
import weblogic.security.internal.encryption.ClearOrEncryptedService;

/**
 *
java -classpath ~/java/weblogic12/wls/wlserver/server/lib/weblogic.jar:. wls.WebLogicDecryptor \
 /Users/Dhval/share/wlAdmin/Oracle/user_projects/domains/JNET_Domain \
 /Users/Dhval/share/wlAdmin/Oracle/user_projects/domains/JNET_Domain/config/jdbc/OmbrePool-3539-jdbc.xml

*   mount -t smbfs smb://c-dmudawal:Poonam0*@10.1.26.51/e$ /Users/Dhval/share/wlAdmin/
 * @author C-credman
 */
public class WebLogicDecryptor {

    private static final String PREFIX = "{AES}";
    private static final String XPATH_EXPRESSION
            = "//node()[starts-with(text(), '" + PREFIX + "')] | //@*[starts-with(., '" + PREFIX + "')]";

    private static ClearOrEncryptedService ces;
    private static ClearOrEncryptedService myCes;

    public static void main(String[] args) throws Exception {
        String domain;
        String inputFile;
        if (args.length == 2) {
            domain = args[0];
            inputFile = args[1];
        } else {
            domain = "/Users/Dhval/share/wlAdmin/Oracle/user_projects/domains/JNET_Domain";
            inputFile = "/Users/Dhval/share/wlAdmin/Oracle/user_projects/domains/JNET_Domain/config/jdbc/OmbrePool-3539-jdbc.xml";
        }

        ces = new ClearOrEncryptedService(SerializedSystemIni.getEncryptionService(new File(domain).getAbsolutePath()));
        File file = new File(inputFile);
        if (file.getName().endsWith(".xml")) {
            processXml(file);
        } else if (file.getName().endsWith(".properties")) {
            processProperties(file);
        }
    }

    private static void processXml(File file) throws Exception {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
        XPathExpression expr = XPathFactory.newInstance().newXPath().compile(XPATH_EXPRESSION);
        NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            print(node.getNodeName(), node.getTextContent());
        }
    }

    private static void processProperties(File file) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream(file));
        for (Map.Entry p : properties.entrySet()) {
            if (p.getValue().toString().startsWith(PREFIX)) {
                print(p.getKey(), p.getValue());
            }
        }
    }

    private static void print(Object attributeName, Object encrypted) {
        String decrypted = ces.decrypt((String) encrypted);
        System.out.println("Node name: " + attributeName);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted + "\n");
        System.out.println("Encrypted: " + myCes.encrypt(decrypted));
    }

}
