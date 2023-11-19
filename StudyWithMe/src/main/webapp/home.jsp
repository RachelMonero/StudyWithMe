<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection,java.sql.DriverManager,java.sql.ResultSet,java.sql.PreparedStatement, application.connection.DBConnection, application.dao.Search" %>
<%
   // New. Nov 17 ---change to preparedstatement
   
    ResultSet resultSet = null;
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create an Account</title>
</head>
<body>
 <%
  
try {
	
	 Class.forName("com.mysql.jdbc.Driver");
	 Connection connection = DBConnection.getConnectionToDatabase();
    // new. Nov 17 --from here --- getting schoolId from session and find schoolName.
	 HttpSession session2= request.getSession();
	 int schoolId = (int) session2.getAttribute("schoolId"); 
	 String schoolName = Search.findSchoolName(schoolId);
	// new. Nov 17 -- to here	
	 

	 String sameSchoolPostSql = "SELECT sg.groupId, sg.groupName, sg.subject, sg.meetupDate, sg.location, sg.duration, sg.maxMember, sg.genderPref, sg.description, sg.createdDate, sg.adminId FROM study_group sg JOIN user u ON sg.adminId = u.userId WHERE u.schoolId = ?";
	
	 PreparedStatement pStatement = connection.prepareStatement(sameSchoolPostSql);
	 pStatement.setInt(1,schoolId);
     resultSet = pStatement.executeQuery();
%>
 
    <h1 id="logo"> Study With Me @<%=schoolName %> </h1>
   

    <form>
        <h2>Welcome Back!</h2>
        <div class="navibar">
            <a class="home" href="home.jsp">Home</a>
            <a class="profile" href="profile.jsp">Profile</a>
            <a class="post" href="post.jsp">Post</a>
            <a class="post" href="mygroup.jsp">MyGroup</a>
            <a class="post" href="groupsearch.jsp">GroupSearch</a> 
            <a class="signout" href="landing.html">Signout</a>
            
        </div>
    </form>



<form action="join" method="post">


    <input type="submit" value="Join a Group" id="joinGroup">
    
    <table>
        <tr>
            <th>Select</th>
            <th>Group Name</th>
            <th>Subject</th>
            <th>Meetup Date</th>
            <th>Location</th>
            <th>Duration</th>
            <th>Maximum</th>
            <th>Preference</th>
            <th>Description</th>
        </tr>

        <%
        while (resultSet.next()) {
            String groupName = resultSet.getString("groupName");
        %>
        <tr>
            <td><input type="radio" name="groupName" value="<%= groupName %>" id="<%= groupName %>"></td>
            <td><%= groupName %></td>
            <td><%= resultSet.getString("subject") %></td>
            <td><%= resultSet.getDate("meetupDate") %></td>
            <td><%= resultSet.getString("location") %></td>
            <td><%= resultSet.getString("duration") %></td>
            <td><%= resultSet.getInt("maxMember") %></td>
            <td><%= resultSet.getString("genderPref") %></td>
            <td><%= resultSet.getString("description") %></td>
        </tr>
        <%
        }
        %>
    </table>
</form>

<%
} catch (Exception e) {
    e.printStackTrace();
}
%>
</body>
</html>



