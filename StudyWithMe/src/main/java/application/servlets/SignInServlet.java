package application.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import application.beans.User;
import application.connection.DBConnection;
import application.dao.Search;
import application.dao.Validate;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * Servlet implementation class SignInServlet
 */
@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInServlet() {
        super();
        
    }
   
    private int getUserStatus(Connection connection, String email) {
        int userStatus = -1;

        String vSql = "SELECT is_verified FROM user WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(vSql)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    userStatus = resultSet.getInt("is_verified");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userStatus;
    }
    
   
    
    //new. Nov 17 ---from here-- bring schoolId
    private int getSchoolId(Connection connection, String email) {
        int schoolId = -1;

        String getlschoolIdSql = "SELECT schoolId FROM user WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(getlschoolIdSql)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                	schoolId = resultSet.getInt("schoolId");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return schoolId;
    }
    
    //new.Nov 17 ---- to here
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
	    PrintWriter pw = response.getWriter();
		
		String email = (request.getParameter("signin-email").toLowerCase());
	    String password = request.getParameter("password");

	 
	    try {
		     Class.forName("com.mysql.jdbc.Driver");
		     Connection connection = DBConnection.getConnectionToDatabase();

		    int isVerifiedUser = getUserStatus(connection,email);
		    
		    
		    int schoolId = getSchoolId(connection,email);
		    
		    
		    if(isVerifiedUser==1) {
		    	System.out.println("isVerifiedUser:"+isVerifiedUser);

            if(Validate.findUser(email, password)){
                
            	
            	HttpSession session= request.getSession();
    
            	session.setAttribute("email", email);
            	
            	session.setAttribute("schoolId",schoolId); ///new.nov 17 ---- set schoolId
            	

            	pw.print("Welcome Back!");      
            	RequestDispatcher rs = request.getRequestDispatcher("home.jsp"); //changed to jsp
       rs.forward(request, response);
       }else{
       pw.print("Incorrect information. Please enter valid email and/or password.");
       RequestDispatcher rs = request.getRequestDispatcher("landing.html");
       rs.include(request, response);
       }
	   } else {
		   
		   pw.print("Please verify your account");
		   RequestDispatcher rs = request.getRequestDispatcher("verification.jsp");
           rs.include(request, response);
		    
       }} catch(Exception e2) {System.out.println(e2);
     
     }
	}
	 

	}


