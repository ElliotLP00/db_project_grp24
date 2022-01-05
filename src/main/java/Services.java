import java.sql.Connection;
import java.sql.DriverManager;

public abstract class Services {
    public Connection getDatabaseConnection() {
        String url = "jdbc:postgresql://pgserver.mau.se:5432/db_project_grp24";
        String user = "ai0234";
        String password = "xrvq55h1";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
