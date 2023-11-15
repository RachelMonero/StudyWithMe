<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.Connection,java.sql.DriverManager,javax.servlet.http.HttpSession,java.sql.ResultSet,java.sql.PreparedStatement,application.dao.Search" %>
<%
    String driverName = "com.mysql.jdbc.Driver";
    String connectionUrl = "jdbc:mysql://localhost:3306/study_with_me";
    String dbUser = "cst8288";
    String dbPassword = "8288";
   

    try {
        Class.forName(driverName);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    HttpSession session1 = request.getSession();
    String email = (String) session1.getAttribute("email"); 
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Group</title>
</head>
<body>
     <h1 id="logo"> Study With Me </h1>

    <form>
        <h2>My Group</h2>
        <div class="navibar">
            <a class="home" href="home.jsp">Home</a>
            <a class="profile" href="profile.jsp">Profile</a>
            <a class="post" href="post.jsp">Post</a>
            <a class="post" href="mygroup.jsp">MyGroup</a>
            <a class="signout" href="landing.html">Signout</a>
            
        </div>
    </form>
    <br/>
    
    <form action="manage" method="post">
       
        
        <input type="submit" value="Leave" name="action" id="leave">
        
        <table>
            <tr>
                <th>Chat</th>
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
            try {
                connection = DriverManager.getConnection(connectionUrl, dbUser, dbPassword);
                
            	
                

                String myGroupSql = "SELECT sg.groupId, sg.groupName, sg.subject, sg.meetupDate, sg.location, sg.duration, sg.maxMember, sg.genderPref, sg.description FROM user AS u JOIN group_member AS gm ON u.userId = gm.userId JOIN study_group AS sg ON gm.groupId = sg.groupId WHERE u.email = ?";
                preparedStatement  = connection.prepareStatement(myGroupSql);
                preparedStatement.setString(1,email);
                resultSet = preparedStatement.executeQuery();
            %>

            <%
            while (resultSet.next()) {
                String groupName = resultSet.getString("groupName");
                String groupId = resultSet.getString("groupId");
            %>
            <tr>
            <td><a href ="/StudyWithMe/chat?group=<%= groupId %>">❒</a></td>
                <td><input type="checkbox" name="groupNames" value="<%= groupName %>" id="<%= groupName %>"></td>
                <td><%= groupName %></td>
                <td><%= resultSet.getString("subject") %></td>
                <td><%= resultSet.getString("meetupDate") %></td>
                <td><%= resultSet.getString("location") %></td>
                <td><%= resultSet.getString("duration") %></td>
                <td><%= resultSet.getInt("maxMember") %></td>
                <td><%= resultSet.getString("genderPref") %></td>
                <td><%= resultSet.getString("description") %></td>
                
            </tr>
            <%
            }
            %>

            <%
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            }
            %>
        </table>
    </form>
</body>
</html>