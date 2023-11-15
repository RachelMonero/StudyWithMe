package application.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import application.beans.Chat;
import application.beans.User;
import application.dao.ChatDAO;
import application.dao.Search;


@WebServlet("/chat")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
private List<Chat> chatMessages ;
	
	/**
	 * 
	 */
	

	public ChatServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(request, response);
		
		response.setContentType("text/html;charset=UTF-8");
		
		
		HttpSession session = request.getSession();	
		
		String email = (String) session.getAttribute("email");
		System.out.println("email retrieved in chat servlet :"+email);// to check if correct email is retrieved
		session.setAttribute("email", email);
		
		String username= Search.whatsUsername(email);
		session.setAttribute("username", username);
		System.out.println("username retrieved in chat servlet :"+username);// to check if correct user name is retrieved
		
		String groupId = request.getParameter("group");
		
		//Session attributes
		String groupName = Search.whatsGroupName(groupId);
		session.setAttribute("group", groupId);
		session.setAttribute("groupName", groupName);
		System.out.println("retrived groupname inchat servlet :"+groupName);// to check if correct group name is retrieved
		
		ChatDAO chatDao = new ChatDAO();
		chatMessages = chatDao.getChatMessagesForGroup(groupId);
		
		request.setAttribute("group", groupId);
		request.setAttribute("chatMessages", chatMessages);

		
		RequestDispatcher  dispatcher = request.getRequestDispatcher("/chat.jsp");// delete /WEB-INF/, move chat.jsp to webapp
		dispatcher.forward(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(request, response);
		
		
		String groupId = request.getParameter("group");
		String chatMessage  =request.getParameter("chat");
		String username = (String) request.getSession().getAttribute("username");
			
		// create a chat object 
		Chat chat = new Chat();
		chat.setChat(chatMessage);
		chat.setGroupId(groupId);
		chat.setCreator(username);
		//chat.setCreator(user);
		
		//create dao object to call the dao methods
		ChatDAO chatDAO = new ChatDAO();
		chatDAO.addChatMessage(chat);
		
	
		response.sendRedirect("/StudyWithMe/chat?group=" +groupId);
	}
	
	
	

}

