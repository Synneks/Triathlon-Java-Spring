package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class JDBCUtil {
    private Properties props;
    private static Connection instance=null;

    public JDBCUtil(Properties props){
        this.props=props;
    }

    private Connection getNewConnection(){
        String driver=props.getProperty("driver");
        String url=props.getProperty("url");
        String user=props.getProperty("user");
        String pass=props.getProperty("password");
        Connection con=null;
        try {
            Class.forName(driver);
            con= DriverManager.getConnection(url,user,pass);
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver "+e);
        } catch (SQLException e) {
            System.out.println("Error getting connection "+e);
        }
        return con;
    }

    public Connection getConnection(){
        try {
            if (instance==null || instance.isClosed())
                instance=getNewConnection();

        } catch (SQLException e) {
            System.out.println("Error DB "+e);
        }
        return instance;
    }

}