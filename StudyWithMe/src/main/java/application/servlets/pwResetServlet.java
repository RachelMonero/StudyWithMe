package application.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.connection.DBConnection;
import application.dao.Validate;

/**
 * Servlet implementation class pwResetServlet
 */
@WebServlet("/pwreset")
public class pwResetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pwResetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
	    PrintWriter pw = response.getWriter();
		
		String email = request.getParameter("pwreset_email");
	    String answer = request.getParameter("pwreset_answer");
	    String newpassword = request.getParameter("newpassword");
		String checknewpassword = request.getParameter("checknewpassword");
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DBConnection.getConnectionToDatabase();
	   		
     
       if(Validate.verifyUser(email, answer)){
    	   
    	   if(newpassword != null && checknewpassword != null && newpassword.equals(checknewpassword)) {
    		   	  
    	    	  System.out.println("Email: " + email);
    	          System.out.println("Answer: " + answer);
    	          System.out.println("password: " + newpassword);
    	    	  
    	   	  String resetpwSql = "UPDATE user SET password =? WHERE email = ?;";

    	  		  PreparedStatement pStatement = connection.prepareStatement(resetpwSql);
    	  		  pStatement.setString(1,newpassword);
    	  		  pStatement.setString(2,email);
    			  pStatement.execute();
    				
    				
    			  pw.print("Password has been reset, successfully");
    			  RequestDispatcher rd = request.getRequestDispatcher("newpassword.html");
    			  rd = request.getRequestDispatcher("landing.html");
    			  rd.include(request, response);
    			  
    			  
    				
    	      } else {
    	      pw.print("Password doesn't match");
    	     }   
      
     
       
       
       }else{
       pw.print("Incorrect information. Please enter valid email.");
       RequestDispatcher rd = request.getRequestDispatcher("pwreset.html");
       rd.include(request, response);
       }} catch(Exception e2) {System.out.println(e2);
     
     }
	}


}
