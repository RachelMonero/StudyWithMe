<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Study With Me</title>
</head>
<body>
<h1 id="logo"> Study With Me </h1>

   <form action="signin" method="post">
   <h2>Sign in</h2>
   <p>New to Study With Me? <b><a id="signup" href="http://localhost:8080/StudyWithMe/signup.html">Create an Account</a></b></p>
   <label>Email: </label>
   <input type="text" name="signin-email" id="signin-email"><br/>
   <label>Password: </label>
   <input type="password" name="password" id="password">
   <input type="submit" value="Sign in" id="signIn">   
   </form>
   <p><b><a id="signup" href="http://localhost:8080/StudyWithMe/pwreset.html">Forgot your password?</a></b></p>

</body>
</html>