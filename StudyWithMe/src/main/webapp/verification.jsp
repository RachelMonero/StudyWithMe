<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Verification</title>
</head>
<body>
<p><b><a id="home" href="http://localhost:8080/StudyWithMe/landing.html">Back</a></b></p>

<form action="verifyUser" method="post">
    <h2>Enter your verification code</h2>
  
    <p> We have sent an email to verify your details. Please enter the code below.</p>
    <label>Verification Code </label><br/>
    <input type="text" name="verification-code" id="verification-code"><br/>
    <input type="submit" value="Verify" id="verification">
</form>


</body>
</html>