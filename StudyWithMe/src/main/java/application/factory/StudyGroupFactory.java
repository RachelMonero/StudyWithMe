package application.factory;

import java.security.Timestamp;
import java.util.Date;
import java.util.List;

import application.beans.Chat;
import application.beans.StudyGroup;
import application.beans.User;

public class StudyGroupFactory extends GroupFactory {



	@Override
	public StudyGroup createGroup(String groupId, String groupName, String subject, Date meetupDate, String location,
			String duration, int maxMember, String genderPref, String description, java.sql.Timestamp createdDate,
			int userId) {
		
		StudyGroup newGrp = new StudyGroup(groupId, groupName, subject, meetupDate, location, duration, maxMember,genderPref, description,createdDate,userId);
		return newGrp;
	}

}

