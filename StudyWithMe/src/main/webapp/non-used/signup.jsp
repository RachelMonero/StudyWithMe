<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Create an Account</title>
</head>
<body>
	<p><b><a id="signup" href="http://localhost:8080/StudyWithMe/landing.html">Back</a></b></p>
<h1 id="logo"> Study With Me </h1>

   <form action="signup" method="post">
    <h2>Create an Account</h2>
  
    <label>Username: </label>
    <input type="text" name="username" id="username"><br/>
    <label>First Name: </label>
    <input type="text" name="firstname" id="firstname"><br/>
    <label>Last Name: </label>
     <input type="text" name="lastname" id="lastname"><br/>
    <label>Email: </label>
    <input type="text" name="email" id="email"><br/>
    <label>Password: </label>
    <input type="password" name="password" id="password"><br/>
    <label>School Name: </label>
    <input type="text" name="school" id="school"><br/>
    <label>Who was your childhood hero?</label>
    <input type="text" name="answer" id="answer">
    
    <input type="submit" value="Sign up" id="signUp">
   
  </form>
</body>
</html>