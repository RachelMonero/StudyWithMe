<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Create an Account</title>
</head>
<body>
	<p><b><a id="signin" href="http://localhost:8080/StudyWithMe/landing.html">Back</a></b></p>
   <h1 id="logo"> Study With Me </h1>

   <form action="pwreset" method="post">
    <h2>Password Reset</h2>
  
    <label>Email: </label>
    <input type="text" name="pwreset_email" id="pwreset_email"><br/>
    <label>Who was your childhood hero?</label>
    <input type="text" name="pwreset_answer" id="pwreset_answer"><br/><br/>
    <label>New Password: </label>
    <input type="password" name="newpassword" id="newpassword"><br/>
    <label>Re-enter New Password: </label>
    <input type="password" name="checknewpassword" id="checknewpassword">
     
    <input type="submit" value="Submit" id="submit">
   
  </form>
</body>
</html>