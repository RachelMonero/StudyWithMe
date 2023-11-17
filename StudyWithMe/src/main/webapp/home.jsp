<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection,java.sql.DriverManager,java.sql.ResultSet,java.sql.Statement" %>
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
    Statement statement = null;
    ResultSet resultSet = null;
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create an Account</title>
</head>
<body>
    <h1 id="logo"> Study With Me </h1>

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


  <%
try {
    connection = DriverManager.getConnection(connectionUrl, dbUser, dbPassword);
    statement = connection.createStatement();
    String sql = "Select * from study_group";
    resultSet = statement.executeQuery(sql);
%>

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


