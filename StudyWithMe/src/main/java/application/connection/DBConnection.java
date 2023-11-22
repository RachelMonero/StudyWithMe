package application.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {


    private static final String dbUser = "cst8288";
    private static final String dbPassword = "8288";
    private static final String connString = "jdbc:mysql://@localhost:3306/study_with_me";
    
    public static Connection getConnectionToDatabase() {
        Connection connection = null;
    
        
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	
        	
        	connection= DriverManager.getConnection(connString, dbUser, dbPassword);
        } catch(ClassNotFoundException e) {
        	System.out.println("Please check your MySQL JDBC Diver location.");
        	e.printStackTrace();
        }
        catch(SQLException e) {
        	System.out.println("Connection Failed.");
        	e.printStackTrace();
        }
        if(connection != null){
        	
        } 
        return connection;
   }
}

