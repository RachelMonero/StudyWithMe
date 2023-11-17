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


@WebServlet("/manage")
public class GroupManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GroupManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void removeMember(Connection connection, String groupId, int userId) {
        String removeMemberSql = "DELETE FROM group_member WHERE groupId=? and userId=?";
        
        try (PreparedStatement pStatement = connection.prepareStatement(removeMemberSql)) {
            pStatement.setString(1,groupId);
            pStatement.setInt(2, userId);
            
            pStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();  
        }
    }
    
    private void removeAllChat(Connection connection, String groupId) {
        String removeAllChatSQL = "DELETE FROM chats WHERE groupId=?";
        
        try (PreparedStatement pStatement = connection.prepareStatement(removeAllChatSQL)) {
            pStatement.setString(1,groupId); 
            pStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();  
        }
    } 
  //new. Nov16 --- find admin Id
    public static int whatsAdminId(Connection connection, String groupName) {
    	
    	int adminId = 0;
    		
    		
    		try {
    			
   		        String getAdminIdSql ="SELECT adminId FROM study_group WHERE groupName=?;";
    			PreparedStatement pStatement = connection.prepareStatement(getAdminIdSql);
    			pStatement.setString(1,groupName);
    			
    			ResultSet rs = pStatement.executeQuery();
    			
    			if (rs.next()) {
    	            
    	            adminId = rs.getInt("adminId");
    	        }
    	       
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    		
    return adminId;
    }
    
    private void removeAllMembers(Connection connection, String groupId) {
        String removeAllMembersSQL = "DELETE FROM group_member WHERE groupId=?";
        
        try (PreparedStatement pStatement = connection.prepareStatement(removeAllMembersSQL)) {
            pStatement.setString(1,groupId); 
            pStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();  
        }
    } 
    
    private void removeGroup(Connection connection, String groupId) {
        String removeGroupSql = "DELETE FROM study_group WHERE groupId=?";
        
        try (PreparedStatement pStatement = connection.prepareStatement(removeGroupSql)) {
            pStatement.setString(1,groupId);        
            pStatement.execute();
            
        } catch (Exception e) {
            e.printStackTrace();  
        }
    }
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	    PrintWriter pw = response.getWriter();
	    
	    String action=request.getParameter("action");
        
	    
	    HttpSession hs = request.getSession();
	    String email = (String) hs.getAttribute("email"); 
	    
	    try {
	 		  Class.forName("com.mysql.jdbc.Driver");
	 		  Connection connection = DBConnection.getConnectionToDatabase();
	    
	    int userId = Search.whatsUserId(email);
	    String[] selectedGroupNames= request.getParameterValues("groupNames");
	 
	    
	    if(selectedGroupNames!=null) {
	    	
	    	  
	    	for(String groupName : selectedGroupNames) {
	    		
	    		String groupId = Search.whatsGroupId(groupName);
	    		
	    		System.out.println(groupId);
	    		//find adminId in each group
	    		int adminId = whatsAdminId(connection,groupName);
	    		
	    		if ( adminId != userId) {   
	    			
	    			removeMember(connection, groupId, userId);
		    		
					
	    		} else {  
   			
	    			    System.out.println(adminId);
	  		    	
	    		    	removeAllMembers(connection,groupId);
	    		    	
	    		    	removeAllChat(connection,groupId);
	    		        
	    		    	removeGroup(connection,groupId);

	    			    		
	    	      } 
	    	
	    	  } RequestDispatcher rd = request.getRequestDispatcher("mygroup.jsp"); 
				rd.include(request, response);
				
	    	}else {
	    		pw.print("Please select the group.");
	    		
	         }
	    } catch(Exception e) {
	    	System.out.println(e);
	    
	    } pw.close();
	
}
}
