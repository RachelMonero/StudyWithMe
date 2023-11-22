<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.Connection,java.sql.DriverManager,javax.servlet.http.HttpSession,java.sql.ResultSet,java.sql.PreparedStatement,application.dao.Search,application.dao.Validate, application.connection.DBConnection" %>
<%

    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    HttpSession session3 = request.getSession();
    String email = (String) session3.getAttribute("email"); 
   
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Profile</title>
    
</head>
<body>
    <p><b><a id="home" href="http://localhost:8080/StudyWithMe/home.jsp">Back</a></b></p>
     <h1 id="logo"> Study With Me </h1>

    <form>
        <h2>My Profile</h2>
        
        <div class="navibar">
            <a class="home" href="home.jsp">Home</a>
            <a class="profile" href="profile.jsp">Profile</a>
            <a class="post" href="post.jsp">Post</a>
            <a class="post" href="mygroup.jsp">MyGroup</a>
            <a class="post" href="groupsearch.jsp">GroupSearch</a> 
            <a class="signout" href="landing.html">Signout</a>
            
        </div>
    </form>
    <br/>
    <form action="editProfile" method="post">
        

        <table>
            <%
            try {
            	Class.forName("com.mysql.jdbc.Driver");
           	    Connection connection = DBConnection.getConnectionToDatabase();

                String myProfileSql = "SELECT * FROM user WHERE email= ?";
                preparedStatement = connection.prepareStatement(myProfileSql);
                preparedStatement.setString(1, email);
                resultSet = preparedStatement.executeQuery();
                
            while (resultSet.next()) {
                String userID = resultSet.getString("userId");
                String username = resultSet.getString("username");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String password = resultSet.getString("password");
                String answer = resultSet.getString("answer");
                String schoolId = resultSet.getString("schoolId");
            %>
            <tr>
                <td>Username: </td>
                <td><input type="text" name="username" id="username" value="<%= username %>"></td>
                
               
            </tr>
            
            <tr>
                <td>First Name: </td>
                <td><input type="text" name="firstName" id="firstName" value="<%= firstName %>"></td>
                
            </tr>
            <tr>
                <td>Last Name: </td>
                <td><input type="text" name="lastName" id="lastName" value="<%= lastName %>"></td>
                
            </tr>
            <tr>
                <td>Email: </td>
                <td><input type="text" name="email" id="email" value="<%= email %>"></td>
                
            </tr>
            <tr>
                <td>Password: </td>
                
                <td><input type="text" name="password" id="password" value="<%= password %>"></td>
                
            </tr>
            <tr>
                <td>Who was your childhood hero? </td>
                <td><input type="text" name="answer" id="answer" value="<%= answer %>"></td>
                 
            </tr>
            <%
                try {
                    String mySchoolSql = "SELECT * FROM school WHERE schoolId= ?";
                    preparedStatement = connection.prepareStatement(mySchoolSql);
                    preparedStatement.setString(1, schoolId);
                    ResultSet schoolResultSet = preparedStatement.executeQuery();

                    while (schoolResultSet.next()) {
                        String schoolName = schoolResultSet.getString("schoolName");
                        String schoolType = schoolResultSet.getString("schoolType");
            %>
            <tr>
                <td>School Name: </td>
                <td><input type="text" name="schoolName" id="schoolName" value="<%= schoolName %>"></td>
                
                
            </tr>
          <tr>
                <td>School Type: </td>
                <td><input type="text" name="schoolType" id="schoolType" value="<%= schoolType %>"></td>
                
                
            </tr>
            <% } schoolResultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            } }
            %>

            <%
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                
            }
            %>
        </table>
        <input type="submit" value="Edit">
    </form>
    
    
        
</body>
</html>