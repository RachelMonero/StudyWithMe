package application.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import application.beans.StudyGroup;
import application.connection.DBConnection;
import application.dao.Search;
import application.dao.Validate;

/**
 * Servlet implementation class GroupSearch
 */
@WebServlet("/groupsearch")
public class GroupSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GroupSearchServlet() {
        super();

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		  System.out.println("before try");
	       PrintWriter pw = response.getWriter();	           
	       String subject = request.getParameter("searchsubject");
	     
	       
	try {
		  Class.forName("com.mysql.jdbc.Driver");
		  Connection connection = DBConnection.getConnectionToDatabase();
	   
		  HttpSession session= request.getSession();
		  int schoolId = (int) session.getAttribute("schoolId"); 
		  
		  String schoolName = Search.findSchoolName(schoolId);
		  
		  System.out.println("Session> schoolId:"+schoolId+"schoolName:"+schoolName);
			
		  String displaySameSchoolPostSql = null;
			 
		  String selectSubject = request.getParameter("searchSubject");
		  String keyword =(request.getParameter("keyword")).toLowerCase(); 	

		  System.out.println("Select Subject: " + selectSubject);
		  System.out.println("Keyword: " + keyword);	
		      		
		  if(selectSubject!= null && !selectSubject.isEmpty() && keyword != null && !keyword.isEmpty()){
			  
			  System.out.println("Select Subject: " + selectSubject);
			  System.out.println("Keyword: " + keyword);
		  	
			 switch(selectSubject){
		  	
		  	   case "groupName":
		  		 displaySameSchoolPostSql = "SELECT sg.groupId, sg.groupName, sg.subject, sg.meetupDate, sg.location, sg.duration, sg.maxMember, sg.genderPref, sg.description, sg.createdDate, sg.adminId FROM study_group sg JOIN user u ON sg.adminId = u.userId WHERE u.schoolId = ? AND sg.groupName LIKE ?";
		  		 break;
		  	   case "subject":
		  		 displaySameSchoolPostSql = "SELECT sg.groupId, sg.groupName, sg.subject, sg.meetupDate, sg.location, sg.duration, sg.maxMember, sg.genderPref, sg.description, sg.createdDate, sg.adminId FROM study_group sg JOIN user u ON sg.adminId = u.userId WHERE u.schoolId = ? AND sg.subject LIKE ?";
		  		 break;
		  	   case "location":
		  		 displaySameSchoolPostSql = "SELECT sg.groupId, sg.groupName, sg.subject, sg.meetupDate, sg.location, sg.duration, sg.maxMember, sg.genderPref, sg.description, sg.createdDate, sg.adminId FROM study_group sg JOIN user u ON sg.adminId = u.userId WHERE u.schoolId = ? AND sg.location LIKE ?";
		  		 break;
		  	   case "duration":
		  		 displaySameSchoolPostSql = "SELECT sg.groupId, sg.groupName, sg.subject, sg.meetupDate, sg.location, sg.duration, sg.maxMember, sg.genderPref, sg.description, sg.createdDate, sg.adminId FROM study_group sg JOIN user u ON sg.adminId = u.userId WHERE u.schoolId = ? AND sg.duration LIKE ?";
		  		 break;
		  	   case "genderPref":
		  		 displaySameSchoolPostSql = "SELECT sg.groupId, sg.groupName, sg.subject, sg.meetupDate, sg.location, sg.duration, sg.maxMember, sg.genderPref, sg.description, sg.createdDate, sg.adminId FROM study_group sg JOIN user u ON sg.adminId = u.userId WHERE u.schoolId = ? AND sg.genderPref LIKE ?";
		  		 break;
		  	 }
		  	
		  } else {
			  displaySameSchoolPostSql = "SELECT sg.groupId, sg.groupName, sg.subject, sg.meetupDate, sg.location, sg.duration, sg.maxMember, sg.genderPref, sg.description, sg.createdDate, sg.adminId FROM study_group sg JOIN user u ON sg.adminId = u.userId WHERE u.schoolId = ?";
			  
		  }

		  PreparedStatement pStatement = connection.prepareStatement(displaySameSchoolPostSql);
		  pStatement.setInt(1,schoolId);

		  if(selectSubject!= null && !selectSubject.isEmpty() && keyword != null && !keyword.isEmpty()){
			  
			String theKeyword="%"+keyword+"%";  
		  	pStatement.setString(2,theKeyword);
		  	
		  }
		  
		  ResultSet resultSet = pStatement.executeQuery();
	 
		  response.setContentType("text/html");

		  pw.print("<form action=\"join\" method=\"post\">");
          pw.print("<input type=\"submit\" value=\"Join a Group\" id=\"joinGroup\">");

          pw.print("<a class=\"signout\" href=\"groupsearch.jsp\">Refresh</a>");
          pw.print("<table>");
          
          pw.print("<tr>");
          pw.print("<th>Select</th>");
          pw.print("<th>Group Name</th>");
          pw.print("<th>Subject</th>");
          pw.print("<th>Meetup Date</th>");
          pw.print("<th>Location</th>");
          pw.print("<th>Duration</th>");
          pw.print("<th>Maximum</th>");
          pw.print("<th>Preference</th>");
          pw.print("<th>Description</th>");
          pw.print("</tr>");
          
		  while (resultSet.next()) {
	            String groupName = resultSet.getString("groupName");
	            
	            pw.print("<tr>");
	            pw.print("<td><input type=\"radio\" name=\"groupName\" value=\"" + groupName + "\" id=\"" + groupName + "\"></td>");
	            pw.print("<td>" + groupName + "</td>");
	            pw.print("<td>" + resultSet.getString("subject") + "</td>");
	            pw.print("<td>" + resultSet.getDate("meetupDate") + "</td>");
	            pw.print("<td>" + resultSet.getString("location") + "</td>");
	            pw.print("<td>" + resultSet.getString("duration") + "</td>");
	            pw.print("<td>" + resultSet.getInt("maxMember") + "</td>");
	            pw.print("<td>" + resultSet.getString("genderPref") + "</td>");
	            pw.print("<td>" + resultSet.getString("description") + "</td>");
	            pw.print("</tr>");
		  }
		  pw.print("</table>");
		  
		 
		RequestDispatcher rd = request.getRequestDispatcher("groupsearch.jsp");
		rd.include(request, response);

  } catch(Exception e) {System.out.println(e);
} 
	}


}


