<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Study Group Chats</title>
</head>
<body>
<h2> Chat Messages for Group Name: ${sessionScope.groupName}</h2><br>
<div class="navibar">
            <a class="home" href="home.jsp">Home</a>
            <a class="profile" href="profile.jsp">Profile</a>
            <a class="post" href="post.jsp">Post</a>
            <a class="post" href="mygroup.jsp">MyGroup</a>
            <a class="signout" href="landing.html">Signout</a>
            
        </div><br>
        
<!-- <h3> User name: ${sessionScope.username}</h3><br> // move this username right in front of chat input box -->



        
		<table border = "1">
		
				<tr>
				<!--  <th>chatId</th> <th>groupId</th> -->
				
				<th>creator</th>
				<th>chat</th>
				<th>timeDateStamp</th>
								
				</tr>
		
		<c:forEach var="chat" items="${chatMessages}">
			<tr>
			<td>${chat.creator}</td>
			<td>${chat.chat}</td>
			<td>${chat.timeDateStamp}</td>
			<!-- <td>${chat.chatId}</td>
			<td>${chat.groupId}</td> -->
			
			</tr>
		</c:forEach>
		
		</table>
		
		<h3>Add a new Chat</h3>
		<form action='/StudyWithMe/chat' method = 'post'>
		<!--  
		<p>User: <input type = 'text' name ='username'></p><br>--> 
		<p>${sessionScope.username}: <input type = 'text' name ='chat'><input type = 'hidden' name ='group' value="${sessionScope.group}">
		<input type = 'submit' name ='Submit' value='Enter'></p><br>  <!-- add value 'Enter' for submit button   -->  
		
		</form>

</body>
</html>