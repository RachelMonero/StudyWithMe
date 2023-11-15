package application.beans;

import java.util.UUID;

public class Course {
	private UUID courseId;
	private String courseName;
	private int semester;
	private String schoolName;
	
	public Course( String courseName, int semester, String schoolName) {
		super();
		this.courseId = UUID.randomUUID();
		this.courseName = courseName;
		this.semester = semester;
		this.schoolName = schoolName;
	}

	public UUID getCourseId() {
		return courseId;
	}

	public void setCourseId(UUID courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	

}
