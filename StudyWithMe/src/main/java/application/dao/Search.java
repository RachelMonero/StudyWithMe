package application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import application.beans.StudyGroup;
import application.beans.User;
import application.connection.DBConnection;

public class Search {


	public static String whatsUsername(String email) {
		
		String username = null;
			
			
			try {
				 Class.forName("com.mysql.jdbc.Driver");
				 Connection connection = DBConnection.getConnectionToDatabase();
				
				 String getUsernameSql ="SELECT username FROM user WHERE email=?;";
				 // chek if username is there
				 System.out.println("sql username "+ getUsernameSql);
				PreparedStatement pStatement = connection.prepareStatement(getUsernameSql);
				pStatement.setString(1,email);
				
				ResultSet rs = pStatement.executeQuery();
				
				if (rs.next()) {
		            
		            username = rs.getString("username");
		        }
		       
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
			
		

	return username;
	}
	
	
	public static ArrayList<StudyGroup> findMygroup(int userId) {
		ArrayList<StudyGroup> studyGroups = new ArrayList<>();
			
			try {
				 Class.forName("com.mysql.jdbc.Driver");
				 Connection connection = DBConnection.getConnectionToDatabase();
				
				 String getMyGroupSql ="SELECT groupId,groupName,subject,meetupDate,location,duration,maxMember,genderPref,description FROM study_group WHERE adminId=?;";
				PreparedStatement pStatement = connection.prepareStatement(getMyGroupSql);
				pStatement.setInt(1,userId);
				
				ResultSet rs = pStatement.executeQuery();
				
				while (rs.next()) {
		            
					    StudyGroup studyGroup = new StudyGroup();
					    studyGroup.setGroupId(rs.getString("groupId")); 
					    studyGroup.setGroupName(rs.getString("groupName")); 
					    studyGroup.setSubject(rs.getString("subject")); 
					    studyGroup.setMeetupDate(rs.getDate("meetupDate")); 
					    studyGroup.setLocation(rs.getString("location")); 
					    studyGroup.setDuration(rs.getString("duration")); 
					    studyGroup.setMaxMember(rs.getInt("maxMember")); 
					    studyGroup.setGenderPref(rs.getString("genderPref")); 
					    studyGroup.setDescription(rs.getString("description")); 
					   

					    studyGroups.add(studyGroup);
		        }
		       
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
			
		

	return studyGroups;
	}





public static int findSchoolId(String schoolname, String schooltype) {
	
	int schoolId = 0;
		
		
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection connection = DBConnection.getConnectionToDatabase();
			
			String getSchoolIdSql ="SELECT schoolId FROM school WHERE schoolname=? and schooltype=?;";
			PreparedStatement pStatement = connection.prepareStatement(getSchoolIdSql);
			pStatement.setString(1,schoolname);
			pStatement.setString(2,schooltype);
			
			ResultSet rs = pStatement.executeQuery();
			
			if (rs.next()) {
	            
	            schoolId = rs.getInt("schoolId");
	        }
	       
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return schoolId;
	
}
/// new.Nov 17 --- get schoolname by schoolId
public static String findSchoolName(int schoolId) {
	
	String schoolName = null;
		
		
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection connection = DBConnection.getConnectionToDatabase();
			
			String getSchoolIdSql ="SELECT schoolName FROM school WHERE schoolId = ?";
			PreparedStatement pStatement = connection.prepareStatement(getSchoolIdSql);
			pStatement.setInt(1,schoolId);
			
			
			ResultSet rs = pStatement.executeQuery();
			
			if (rs.next()) {
	            
	            schoolName = rs.getString("schoolName");
	        }
	       
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return schoolName;
	
}

public static int findCourseId(int schoolId, String coursename) {
	
	int courseId = 0;
		
		
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection connection = DBConnection.getConnectionToDatabase();
			
			String getCourseIdSql ="SELECT courseId FROM course WHERE coursename=? and schoolId=?;";
			PreparedStatement pStatement = connection.prepareStatement(getCourseIdSql);
			
			pStatement.setString(1,coursename);
			pStatement.setInt(2,schoolId);
			
			ResultSet rs = pStatement.executeQuery();
			
			if (rs.next()) {
	            
	            courseId = rs.getInt("courseId");
	        }
	       
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return courseId;
	
}

public static int whatsUserId(String email) {
	
	int userId = 0;
		
		
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection connection = DBConnection.getConnectionToDatabase();
			
			 String getUserIdSql ="SELECT userId FROM user WHERE email=?;";
			PreparedStatement pStatement = connection.prepareStatement(getUserIdSql);
			pStatement.setString(1,email);
			
			ResultSet rs = pStatement.executeQuery();
			
			if (rs.next()) {
	            
	            userId = rs.getInt("userId");
	        }
	       
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
	

return userId;
}
//try to find groupId by groupName - it is used for JoinGroup.java
public static String whatsGroupId(String groupName) {
	
	String groupId = null;
		
		
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection connection = DBConnection.getConnectionToDatabase();
			
			 String getGroupIdSql ="SELECT groupId FROM study_group WHERE groupName=?;";
			 PreparedStatement pStatement = connection.prepareStatement(getGroupIdSql);
			 pStatement.setString(1,groupName);
			
			 ResultSet rs = pStatement.executeQuery();
			
			 if (rs.next()) {
	            
	            groupId = rs.getString("groupId");
	         }
	       
	       } catch (Exception e) {
	          e.printStackTrace();
	    }
		
	

return groupId;
}


public static String whatsGroupName(String groupId) {
	
	String groupName = null;
		
		
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection connection = DBConnection.getConnectionToDatabase();
			
			 String getGroupNameSql ="SELECT groupName FROM study_group WHERE groupId=?;";
			PreparedStatement pStatement = connection.prepareStatement(getGroupNameSql);
			pStatement.setString(1,groupId);
			
			ResultSet rs = pStatement.executeQuery();
			
			if (rs.next()) {
	            
	            groupName = rs.getString("groupName");
	        }
	       
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
	

return groupName;
}


}