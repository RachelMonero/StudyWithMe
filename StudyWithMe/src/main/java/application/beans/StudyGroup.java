package application.beans;


import java.security.Timestamp;
import java.util.Date;
import java.util.List;


public class StudyGroup {
 private String groupId;
 private String groupName;
 private String subject; 
 private Date meetupDate; 
 private String location;
 private String duration; 
 private int maxMember; 
 private String genderPref; 
 private String description; 
 private Timestamp createdDate; 
 private int adminId;
 private List<User> members;
 private Chat chat;

 
 
public StudyGroup(String groupId, String groupName, String subject, Date meetupDate, String location, String duration, int maxMember, String genderPref, String description,List<User> members, Chat chat, Timestamp createdDate, int adminId) {
	super();
	this.groupId = groupId;
	this.groupName = groupName;
	this.subject= subject;
	this.meetupDate = meetupDate;
	this.location = location;
	this.duration= duration;
	this.maxMember= maxMember;
	this.genderPref=genderPref;
	this.description= description;
	this.members = members;	
	this.chat = chat;
	this.createdDate = createdDate;
	this.adminId  =adminId; // new
}

public StudyGroup() {
	
}
public String getGroupId() {
	return groupId;
}
public void setGroupId(String groupId) {
	this.groupId = groupId;
}
public String getGroupName() {
	return groupName;
}
public void setGroupName(String groupName) {
	this.groupName = groupName;
}

public String getSubject() {
	return subject;
} 

public void setSubject(String subject) {
	this.subject = subject;
}

public Date getMeetupDate() {
	return meetupDate;
}

public void setMeetupDate(Date meetupDate) {
	this.meetupDate = meetupDate;
}

public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}

public String getDuration() {
	return duration;
}

public void setDuration(String duration) {
	this.duration = duration;
}

public int getMaxMember() {
	return maxMember;
}

public void setMaxMember(int maxMember) {
	this.maxMember = maxMember;
}

public String getGenderPref() {
	return genderPref;
}

public void setGenderPref(String genderPref) {
	this.genderPref = genderPref;
} 

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}
 
public int getAdminId() {
	return adminId;
}

public void setAdminId(int adminId) {
	this.adminId = adminId;
}
public Timestamp getCreatedDate() {
	return createdDate;
}
public void setCreatedDate(Timestamp createdDate) {
	this.createdDate = createdDate;
}
public List<User> getMembers() {
	return members;
}
public void setMembers(List<User> members) {
	this.members = members;
}
public Chat getChat() {
	return chat;
}
public void setChat(Chat chat) {
	this.chat = chat;
}
 
 //private Permissions permission;
 //private List<User> notifications;;
 
 
 
}
