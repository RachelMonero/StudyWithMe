package application.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import application.beans.StudyGroup;
import application.dao.Search;

@WebServlet("/mygroup")
public class MyGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  
    public MyGroupServlet() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.setContentType("text/html;charset=UTF-8");
	      PrintWriter pw = response.getWriter();
	      
	 
	      HttpSession session = request.getSession();
		  String email = (String) session.getAttribute("currentUser"); 
		  int userId = Search.whatsUserId(email);
		 		
		 
		  
		  ArrayList<StudyGroup> studyGroups = (ArrayList<StudyGroup>) Search.findMygroup(userId);
		  
		  
	     
		  request.setAttribute("studyGroups",studyGroups);
		pw.print(studyGroups);
		  
		  RequestDispatcher rd = request.getRequestDispatcher("mygroup.jsp"); 
		  rd.forward(request, response);
		  
		  
	}

}
	
