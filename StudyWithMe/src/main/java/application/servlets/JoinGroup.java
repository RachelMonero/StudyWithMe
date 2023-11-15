package application.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
 * Servlet implementation class JoinGroup
 */
@WebServlet("/join")
public class JoinGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public JoinGroup() {
        super(); // TODO Auto-generated constructor stub
    }
    
    private void addGroupMember(Connection connection, String groupId, int userId) {
        String addGroupMemberSql = "INSERT INTO group_member (memberId, userId, groupId, permissionId, isAdmin) VALUES (?,?,?,?,?)";
        String memberId = UUID.randomUUID().toString();
        try (PreparedStatement pStatement = connection.prepareStatement(addGroupMemberSql)) {
            pStatement.setString(1, memberId);
            pStatement.setInt(2, userId);
            pStatement.setString(3, groupId);
            pStatement.setInt(4, 3);  // 2 is group admin permission / 1= system admin permission /3=member permission/4=non member permission 
            pStatement.setInt(5, 0);  // isAdmin as 0 (=not admin) or 1 (=admin)
            pStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();  
        }
    }

    
    public static boolean inTheGroup(int userId, String groupId) {
    	
    	boolean isUserInGroup=false;
    		
    		
    		try {
    			 Class.forName("com.mysql.jdbc.Driver");
    			 Connection connection = DBConnection.getConnectionToDatabase();
    			
    			 String getUserIdSql ="SELECT userId FROM group_member WHERE userId=? AND groupId=?;";
    			PreparedStatement pStatement = connection.prepareStatement(getUserIdSql);
    			pStatement.setInt(1,userId);
    			pStatement.setString(2,groupId);
    			
    			ResultSet rs = pStatement.executeQuery();
    			
    			isUserInGroup=rs.next();
    	       
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
 
    return isUserInGroup;
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html;charset=UTF-8");
	       PrintWriter pw = response.getWriter();
	       
	       String groupName = request.getParameter("groupName");
	       
	       try {
	 		  Class.forName("com.mysql.jdbc.Driver");
	 		  Connection connection = DBConnection.getConnectionToDatabase();
	 		 //calling useremail which was used to login
			  HttpSession session = request.getSession();
			  String email = (String) session.getAttribute("email"); 
	 	   
	 		  if(groupName!=null) {
	 			  

				  int userId = Search.whatsUserId(email);
				  String groupId = Search.whatsGroupId(groupName);
				  
				
				  
					if(Validate.isValidSeat(groupId)) {
						
						
						
						if(!inTheGroup(userId,groupId)) {
				  // new = calling addGroupAdmin method.
					
				  addGroupMember(connection,groupId,userId);
				  
				  RequestDispatcher rd = request.getRequestDispatcher("home.jsp"); //changed to jsp
			      rd.include(request, response);
			      pw.print("Joined");
			      }else {
			    	  pw.print("You are a member of this group, already.");  
			      }
	 			  
				  }else {
					  
					  pw.print("Sorry, the group is full.");  
				  }
					
	 		 
	 			  }
	       }catch(Exception e2) {System.out.println(e2);
	       }
	}

}
