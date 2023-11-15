package application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import application.beans.User;
import application.connection.DBConnection;

public class Validate {
	

	
	
	public static boolean findUser(String email,String password) {
		
		boolean isFindUser=false;
		
			
			
			try {
				 Class.forName("com.mysql.jdbc.Driver");
				 Connection connection = DBConnection.getConnectionToDatabase();
				
				String validationSql = "select*from user where email=? and password=?";
				PreparedStatement pStatement = connection.prepareStatement(validationSql);
				pStatement.setString(1,email);
				pStatement.setString(2,password);
				ResultSet rs = pStatement.executeQuery();
				isFindUser=rs.next();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		return isFindUser;
	}
	
    public static boolean findEmail(String email) {
		
		boolean isFindEmail=false;
		
			
			
			try {
				 Class.forName("com.mysql.jdbc.Driver");
				 Connection connection = DBConnection.getConnectionToDatabase();
				
				String validationSql = "select*from user where email=?";
				PreparedStatement pStatement = connection.prepareStatement(validationSql);
				pStatement.setString(1,email);
				ResultSet rs = pStatement.executeQuery();
				isFindEmail=rs.next();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		return isFindEmail;
	}
    public static boolean findUsername(String username) {
	
	    boolean isFindUsername=false;
	
		
		
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection connection = DBConnection.getConnectionToDatabase();
			
			String validationSql = "select*from user where username=?";
			PreparedStatement pStatement = connection.prepareStatement(validationSql);
			pStatement.setString(1,username);
			ResultSet rs = pStatement.executeQuery();
			isFindUsername=rs.next();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	    return isFindUsername;
    }
   
public static boolean verifyUser(String email,String answer) {
		
		boolean isVerifyUser=false;
		
			
			
			try {
				 Class.forName("com.mysql.jdbc.Driver");
				 Connection connection = DBConnection.getConnectionToDatabase();
				
				String validationSql = "select*from user where email=? and answer=?";
				PreparedStatement pStatement = connection.prepareStatement(validationSql);
				pStatement.setString(1,email);
				pStatement.setString(2,answer);
				ResultSet rs = pStatement.executeQuery();
				isVerifyUser=rs.next();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	
	return isVerifyUser;
}

public static boolean verifyGroup(String groupName) {
	
	boolean isVerifyGroup=false;
	
		
		
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection connection = DBConnection.getConnectionToDatabase();
			
			String gValidationSql = "select*from study_group where groupName=?";
			PreparedStatement pStatement = connection.prepareStatement(gValidationSql);
			pStatement.setString(1,groupName);
			
			ResultSet rs = pStatement.executeQuery();
			isVerifyGroup=rs.next();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	

return isVerifyGroup;
} 


public static User finduserDetails(String email, String password) {
	User user = null;
	
	try {
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection connection = DBConnection.getConnectionToDatabase();
		
				
				 String validationSql = "SELECT *FROM user WHERE email=? AND password=?";
					PreparedStatement pStatement = connection.prepareStatement(validationSql);
					pStatement.setString(1,email);
					pStatement.setString(2,password);
					ResultSet rs = pStatement.executeQuery();
					if(rs.next()) {
						user = new User(
								rs.getInt("userId"), 
								rs.getString("username"), 
								rs.getString("firstName"), 
								rs.getString("lastName"),
								rs.getString("password"),
								rs.getString("email"),
								rs.getString("school"),
								rs.getString("answer"));
					}
				
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return user;
	
}


 //new - this is for join the group
 

public static boolean isValidSeat(String groupId) {
	
	boolean isValidSeat=false;
		
		
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection connection = DBConnection.getConnectionToDatabase();
			
			 String countMember ="SELECT (SELECT COUNT(userId) FROM group_member WHERE groupId=?) AS members, maxMember FROM study_group WHERE groupId=?;";
			PreparedStatement pStatement = connection.prepareStatement(countMember);
			pStatement.setString(1,groupId);
			pStatement.setString(2,groupId);
			
			ResultSet rs = pStatement.executeQuery();
			
			if (rs.next()) {
	            
				 int numOfMember = rs.getInt("members");
				 int maxMembers=rs.getInt("maxMember");
				 
				 if(numOfMember < maxMembers){
				 
				 isValidSeat=true;
				 
				 }
				 
	        }
	       
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
	

return  isValidSeat;
}



}





