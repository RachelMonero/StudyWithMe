package application.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import application.connection.DBConnection;


@WebServlet("/verifyUser")
public class VerifyUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	PreparedStatement preparedStatement = null;
	
    public VerifyUserServlet() {
        super();
        
    }
 //this is to update is_verified of user table
    private void verify(Connection connection, String email) {
    	String vSql = "UPDATE user SET is_verified = ? WHERE email = ?";
    	try(PreparedStatement preparedStatement = connection.prepareStatement(vSql)){
       
        preparedStatement.setInt(1, 1); //  0 = not verified, 1 = verified
        preparedStatement.setString(2, email);
        preparedStatement.executeUpdate();        
        
        } catch (Exception e) {
            e.printStackTrace();  
        }
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	    PrintWriter pw = response.getWriter();
	    
	    HttpSession session = request.getSession();
	    String vCode = (String) session.getAttribute("vCode");
	    System.out.println("vCode:"+vCode);
	    String email = (String) session.getAttribute("signupEmail");
	    System.out.println("email:"+email);
	    
	    String userInputCode = request.getParameter("verification-code");
	    
	   try { 
	    Class.forName("com.mysql.jdbc.Driver");
		  Connection connection = DBConnection.getConnectionToDatabase();
		  
	    if(userInputCode==null) {
	    	pw.print("Please enter verification code");
	    }else if(userInputCode.equals(vCode)) {
	    	verify(connection, email);
	    	session.removeAttribute("vCode"); // the generated vCode must be temporary one time use. after the verification, remove the vCode from session.
            response.sendRedirect("landing.html");
	    }else {
	    	pw.print("Verification failed. Please try again.");
	    	
	    }
	   } catch(Exception e) {
		   System.out.println(e);
	   } pw.close();
	  
	   
	}

}
