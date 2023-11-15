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
import application.dao.Search;
import application.dao.Validate;


@WebServlet("/editProfile")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public EditProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();

        try {
        	
        	Class.forName("com.mysql.jdbc.Driver");
  		    Connection connection = DBConnection.getConnectionToDatabase();
            
        	 HttpSession session5 = request.getSession();
        	 String email = (String) session5.getAttribute("email");
        	 System.out.println(email);
        	 String userID = request.getParameter("userId");
        	 String username = Search.whatsUsername(email);// need to check
        	 System.out.println(username);
        	 
             String newusername = request.getParameter("username");
             String newFirstName = request.getParameter("firstName");
             String newLastName = request.getParameter("lastName");
             String newPassword = request.getParameter("password");
             String newEmail = (request.getParameter("email").toLowerCase());
             String newSchoolName = (request.getParameter("schoolName").toLowerCase());
             String newSchoolType = (request.getParameter("schoolType").toLowerCase());
             int newSchoolId= Search.findSchoolId(newSchoolName, newSchoolType);
           
             
            
             String updateProfileSql = "UPDATE user SET username=?, firstName=?,lastName=?, password=?,email=?,schoolId=? WHERE email=?";
             PreparedStatement preparedStatement = connection.prepareStatement(updateProfileSql);
             
            
             preparedStatement.setString(1, newusername);
             preparedStatement.setString(2, newFirstName);
             preparedStatement.setString(3, newLastName);
             preparedStatement.setString(4, newPassword);
             preparedStatement.setString(5, newEmail);
             preparedStatement.setInt(6,newSchoolId);
             preparedStatement.setString(7, email);
             
             
               
             
            
            
            preparedStatement.executeUpdate();
            System.out.println("Profile has updated");

            response.sendRedirect("profile.jsp");
        } catch (Exception e) {

            e.printStackTrace();

        } finally {
            pw.close();
        }
    }
}