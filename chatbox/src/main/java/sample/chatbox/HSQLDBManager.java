package sample.chatbox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;

/**
 * Manages the HSQL db suing the properties file present in classpath.
 * @author dxm6974
 */
public abstract class HSQLDBManager {
    private static Logger logger = Logger.getLogger(HSQLDBManager.class);
    private static String DB_FILE;
    // internal connection, not to be shared.
    private static Connection connection = null;

    public HSQLDBManager(String dbFile) {
        DB_FILE = dbFile;
        connection = getConnection();
    }

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            con = DriverManager.getConnection("jdbc:hsqldb:file:" + DB_FILE,
                    "sa", "");
        } catch (SQLException se) {
            logger.error("ERROR: to get HSQLDB JDBC connection", se);
        } catch (ClassNotFoundException ce) {
            logger.error("ERROR: failed to load HSQLDB JDBC driver.", ce);
        } catch (Exception e) {
            logger.error("ERROR: failed to get HSQLDB connection.", e);
        }
        return con;
    }

    /**
     * Shutdown the instance of DB.
     * Internal 'connection' should be the last connection to close.
     * @throws java.sql.SQLException
     */
    public void shutdown() throws SQLException {
        Statement st = connection.createStatement();
        st.execute("SHUTDOWN");
        connection.close();
    }

    /**
     * To close SQL objects.
     * @param obj
     * @throws java.sql.SQLException
     */
    public void close(Object obj) {
        if (obj == null) return;
        try {
        if (obj instanceof PreparedStatement) ((PreparedStatement) obj).close();
        if (obj instanceof Statement) ((Statement) obj).close();
        if (obj instanceof ResultSet) ((ResultSet) obj).close();
        if (obj instanceof Connection) ((Connection) obj).close();
        } catch (Exception e) {
            logger.error("failed to close ", e);
        }
    }
}
