package application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import application.beans.StudyGroup;
import application.connection.DBConnection;

public class StudyGroupDAOImp implements StudyGroupDAO {

	@Override
	public void studyGroupToDatabase(StudyGroup newGrp) {
		try(Connection connection = DBConnection.getConnectionToDatabase()){
			
		
		
		// insert Data into database
		 String createGroupSql = "INSERT INTO study_group (groupId, groupName, subject, meetupDate, location, "
		 		+ "duration, maxMember, genderPref, description, createdDate,adminId) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		
	    PreparedStatement pStatement = connection.prepareStatement(createGroupSql);
	      pStatement.setString(1,newGrp.getGroupId());
		  pStatement.setString(2,newGrp.getGroupName());
		  pStatement.setString(3,newGrp.getSubject());
		  pStatement.setDate(4,(java.sql.Date)(newGrp.getMeetupDate()));
		  pStatement.setString(5,newGrp.getLocation());
		  pStatement.setString(6,newGrp.getDuration());
		  pStatement.setInt(7,newGrp.getMaxMember());
		  pStatement.setString(8,newGrp.getGenderPref());
		  pStatement.setString(9,newGrp.getDescription());
		  pStatement.setTimestamp(10,newGrp.getCreatedDate());
		  pStatement.setInt(11,newGrp.getAdminId()); // assign username as admin of the group
		 
		  pStatement.executeUpdate();
		  //System.out.println("Group has been created successfully.");
		  
	
	}catch (SQLException e) {
		e.printStackTrace();
	}
		
		
  }
}
