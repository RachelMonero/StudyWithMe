package application.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
 * Servlet implementation class GroupSearch
 */
@WebServlet("/groupsearch")
public class GroupSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	   // 	doGet(request, response);
		  System.out.println("before try");
	       PrintWriter pw = response.getWriter();
	             
	       String subject = request.getParameter("searchsubject");
	     
	       
	try {
		  Class.forName("com.mysql.jdbc.Driver");
		  Connection connection = DBConnection.getConnectionToDatabase();
	   
		  if(subject !=null) {
			  String searchSql = "SELECT * FROM study_group WHERE SUBJECT = ?";

		      PreparedStatement pStatement = connection.prepareStatement(searchSql);
		      pStatement.setInt(1, 1001);
		      
		    
			  
			  HttpSession session = request.getSession();
			  // changed to email
			  String email = (String) session.getAttribute("email"); 
			  int userId = Search.whatsUserId(email);/// use whatsUsername function to find username 			  
			 
			  pStatement.setInt(11,userId); // assign username as admin of the group
			  pStatement.execute();
			  pw.print("ran the search query.");
			  
			 
			  
			  RequestDispatcher rd = request.getRequestDispatcher("home.jsp"); //changed to jsp
		      rd.include(request, response);
		      
		    
		      
		  }else {
			  pw.print("did not execute");
		  }
		  

  } catch(Exception e2) {System.out.println(e2);
} pw.close();
	}


}


