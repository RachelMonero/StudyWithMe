package application.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import application.connection.DBConnection;
import application.dao.Search;
import application.dao.Validate;

/**
 * Servlet implementation class PostServlet
 */
@WebServlet("/post")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
// new = adding groupAmin. this will add group user to group_member table as admin.
    private void addGroupAdmin(Connection connection, String groupId, int userId) {
        String addGroupMemberSql = "INSERT INTO group_member (memberId, userId, groupId, permissionId, isAdmin) VALUES (?,?,?,?,?)";
        String memberId = UUID.randomUUID().toString();
        try (PreparedStatement pStatement = connection.prepareStatement(addGroupMemberSql)) {
            pStatement.setString(1, memberId);
            pStatement.setInt(2, userId);
            pStatement.setString(3, groupId);
            pStatement.setInt(4, 2);  // 2 is group admin permission / 1= system admin permission /3=member permission/4=non member permission 
            pStatement.setInt(5, 1);  // isAdmin as 0 (=not admin) or 1 (=admin)
            pStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();  
        }
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html;charset=UTF-8");
	       PrintWriter pw = response.getWriter();
	       
	       
	       String groupId = UUID.randomUUID().toString();
	       String groupName = request.getParameter("groupname");
	       String subject = request.getParameter("subject");
	       
	        	       
	       Date meetupDate = null;
	       String meetupDateStr = request.getParameter("meetupdate");
	       try {
	    	    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
	    	    java.util.Date parsedDate = sdf.parse(meetupDateStr);
	    	    meetupDate = new java.sql.Date(parsedDate.getTime());
	    	} catch (ParseException e) {
	    	    e.printStackTrace();
	    	}
	       String location = request.getParameter("location");
	       String duration = request.getParameter("duration");	     
	       int maxMember = Integer.parseInt(request.getParameter("max_member"));
	       String genderPref = request.getParameter("gender_pref");
	       String description = request.getParameter("description");
	       Timestamp createdDate = new Timestamp(System.currentTimeMillis());
	       
	try {
		  Class.forName("com.mysql.jdbc.Driver");
		  Connection connection = DBConnection.getConnectionToDatabase();
	   
		  if(groupName!=null && subject !=null) {
	
		  if(Validate.verifyGroup(groupName)) {
			  pw.println("<h4>Sorry, the group name is in use. Please choose other group name.</h4>");  
		  
		  }else{
		
			  
			  String createGroupSql = "INSERT INTO study_group (groupId, groupName, subject, meetupDate, location, duration, maxMember, genderPref, description, createdDate,adminId) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

		      PreparedStatement pStatement = connection.prepareStatement(createGroupSql);
		      pStatement.setString(1,groupId);
			  pStatement.setString(2,groupName);
			  pStatement.setString(3,subject);
			  pStatement.setDate(4,(java.sql.Date)meetupDate);
			  pStatement.setString(5,location);
			  pStatement.setString(6,duration);
			  pStatement.setInt(7,maxMember);
			  pStatement.setString(8,genderPref);
			  pStatement.setString(9,description);
			  pStatement.setTimestamp(10,createdDate);
			 
			  
			  HttpSession session = request.getSession();
			  // changed to email
			  String email = (String) session.getAttribute("email"); 
			  int userId = Search.whatsUserId(email);/// use whatsUsername function to find username 			  
			 
			  pStatement.setInt(11,userId); // assign username as admin of the group
			  pStatement.execute();
			  pw.print("Group has created successfully.");
			  
			  // new = calling addGroupAdmin method.
			  addGroupAdmin(connection,groupId,userId);
			  
			  RequestDispatcher rd = request.getRequestDispatcher("home.jsp"); //changed to jsp
		      rd.include(request, response);
		      
		    
		      
		  }}else {
			  pw.print("Please enter group name.");
		  }
		  

     } catch(Exception e2) {System.out.println(e2);
   } pw.close();
	}


}
