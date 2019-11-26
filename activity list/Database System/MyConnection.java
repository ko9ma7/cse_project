package dbp2014136124;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author JMH
 * 2018-11-27
 */
public class MyConnection {
    public static Connection con = null; // one data member
    
    public static Connection makeConnection() { // return Connection
         // Load and register the Driver  
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            JOptionPane.showMessageDialog(null, "Driver is loaded and registered");
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Driver is a problem in a loading the Driver");
            
            return null;
        }
        
        // To make a connection
        String strurl = "jdbc:sqlserver://DESKTOP-MDL0U7N\\SQLEXPRESS:1433;databaseName=newMyDatabase";
        
        try { // if connection success
            con = DriverManager.getConnection(strurl, "JMH", "@wlaudghk4261"); // url, username, password
            JOptionPane.showMessageDialog(null, "Connected");
            
            return con;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Connection Failed");
            
            return null;
        }
    }
}
