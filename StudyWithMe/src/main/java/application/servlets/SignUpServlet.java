package application.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
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
import application.service.EmailService;
import application.service.TokenGenerator;


@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SignUpServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		   response.setContentType("text/html;charset=UTF-8");
	       PrintWriter pw = response.getWriter();
	       
	       String username = request.getParameter("username");
	       String firstname = request.getParameter("firstname");
	       String lastname = request.getParameter("lastname");   
	       String password = request.getParameter("password");
	       String email = (request.getParameter("email").toLowerCase());
	       
	       String schoolname = request.getParameter("school");
	       String schooltype = request.getParameter("school_type");
	      
	    // new. 11/15/23 -- removed int schoolId = Search.findSchoolId(schoolname, schooltype);
	       String answer = (request.getParameter("answer").toLowerCase());
	 
	       
	try {
		  Class.forName("com.mysql.jdbc.Driver");
		  Connection connection = DBConnection.getConnectionToDatabase();
		  
		  // new. 11/15/23 -- add 
		  if (schoolname != null && !schoolname.isEmpty() && schooltype != null && !schooltype.isEmpty()) {
			    int schoolId = Search.findSchoolId(schoolname.toLowerCase(), schooltype.toLowerCase());

		  
		  
	   // new. 11/15/23 -- remove  schoolname != null &&  schooltype != null 
		  if(username!=null && firstname!=null && lastname !=null && email != null && password!=null &&  answer!=null) {
			  		  
	
		  if(Validate.findUsername(username)) {
			  pw.println("<h4>Sorry, username is invalid. Please choose other username.</h4>");  
		  }else if(Validate.findEmail(email)) {
			  pw.print("<h4>Sorry, this email address is already in use.</h4>"); 
		// new.from here 11/15/23 --add if statement 	  
		  } else if(schoolId==0) {
			  pw.print("<h4>Sorry, our services are currently not available for your school.</h4>");
		//	new. to here 11/15/23 
			  
		  } else{
		
			// new.from here  10:23 pm 11/14/2023
			  String signUpSql = "insert into user values(?,?,?,?,?,?,?,?,?)";
		      PreparedStatement pStatement = connection.prepareStatement(signUpSql);
		      pStatement.setString(1,null);
			  pStatement.setString(2,username);
			  pStatement.setString(3,firstname);
			  pStatement.setString(4,lastname);
			  pStatement.setString(5,password);
			  pStatement.setString(6,email);			 
			  pStatement.setString(7,answer);	
			  pStatement.setInt(8,schoolId);
			  pStatement.setInt(9, 0);
			  pStatement.execute();
			  
			  //added singleton pattern
			  TokenGenerator tokenGenerator = TokenGenerator.getInstance();
			  String vCode = tokenGenerator.generatedToken();
			  System.out.print(vCode);
			 //added singleton pattern till here 
			  
			  // System.out.print(vCode);-- delete
			  //new.Nov 19 --- add from here
			  EmailService emailService = new EmailService();
			  String container = vCode;
			  emailService.sendEmail(email, container);
			  //new.Nov 19 --- add to here
			  HttpSession session= request.getSession();
          	  session.setAttribute("vCode", vCode);
              session.setAttribute("signupEmail", email); // this email is saved in session for verification purpose.
          	  
			
			  RequestDispatcher rd = request.getRequestDispatcher("verification.jsp"); 
			  
			  
		      rd.forward(request, response);
		      		    		      
		  }
		  }
		  }else {
			  pw.print("Please fill out all required information.");
		  }
		  
       } catch(Exception e2) {System.out.println(e2);
     } pw.close();
	}

}
