package application.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
	    		
	    		removeMember(connection, groupId, userId);
	    		RequestDispatcher rd = request.getRequestDispatcher("mygroup.jsp"); 
				rd.include(request, response);
	    		
	    	} 
	    	
	    	} else {
	    		pw.print("Please select the group.");
	    		
	    	}
	    } catch(Exception e) {System.out.println(e);
	    
	} pw.close();
	
}
}
