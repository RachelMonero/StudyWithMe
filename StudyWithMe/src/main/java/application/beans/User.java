package application.beans;

import java.util.List;
import java.util.UUID;

public class User {
	
	//private UUID userID; // private UUID useruuid; private int userID; need to go with one.
	private int userID;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String school;
	private String schoolId;
	private String answer;
	private int password;
	private List<Course> course;
	private List<StudyGroup> studyGroup;
	
	public User(int userID, String username, String email,String school, List<Course> course, 
			List<StudyGroup> studyGroup
			) {
		super();
		//this.userID = UUID.randomUUID();
		this.userID = userID;
		this.username = username;
		this.email = email;
		this.course = course;
		this.studyGroup = studyGroup;
	}
	//new
	public User(int id, String username, String firstName2, String lastName, String password,String email, 
			String school, String answer) {
		// TODO Auto-generated constructor stub
	}
	public User() {
		super();
		
		this.userID = userID;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		//new
		this.password=password;
		this.answer = answer;
		
		;
	}
	

	public User(String user) {
		// TODO Auto-generated constructor stub
		this.username = user; 
	}
/*
	public UUID getUserID() {
		return userID;
	}

	public void setUserID(UUID userID) {
		this.userID = userID;
	}
*/
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Course> getCourse() {
		return course;
	}

	public void setCourse(List<Course> course) {
		this.course = course;
	}

	public List<StudyGroup> getStudyGroup() {
		return studyGroup;
	}

	public void setStudyGroup(List<StudyGroup> studyGroup) {
		this.studyGroup = studyGroup;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}
	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	/// new
	public void setPassword(String string) {
		this.password= password;
		
	}
	public int getPassword() {
		return password;
	}
	

}
