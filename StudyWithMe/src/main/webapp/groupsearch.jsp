<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Create an Account</title>
</head>
<body>
    <p><b><a id="home" href="http://localhost:8080/StudyWithMe/home.jsp">Back</a></b></p> <%--changed to jsp --%> 
<h1 id="logo"> Study With Me </h1>

   <form action="post" method="post"> 
    <h2>Create 'Study With Me' Group</h2>


    <label>Search on Subject: </label>
    <input type="text" name="searchsubject" id="searchsubject"><br/>

    <input type="submit" value="search" id="search">
  </form>



</body>
</html>