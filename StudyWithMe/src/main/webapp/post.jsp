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
      <form >
  <div class="navibar">
  <a class="home" href="home.jsp">Home</a>
  <a class="profile" href="profile.jsp">Profile</a>
  <a class="post" href="post.jsp">Post</a>
   <a class="post" href="mygroup.jsp">MyGroup</a>
  <a class="signout" href="landing.html">Signout</a>
  </div>
       
  </form>
  
   <form action="post" method="post"> 
    <h2>Create 'Study With Me' Group</h2>
  
    <label>Group Name: </label>
    <input type="text" name="groupname" id="groupname"><br/>
    <label>Subject: </label>
    <input type="text" name="subject" id="subject"><br/>
    <label>Meetup Date: </label>
     <input type="text" name="meetupdate" id="meetupdate" placeholder="mm/dd/yy"><br/>
    <label>Location: </label>
    <input type="text" name="location" id="location"><br/>
    <label>Duration: </label>
    <input type="text" name="duration" id="duration"><br/>
    <label>Maximum Group members: </label>
    <input type="text" name="max_member" id="max_member"><br/>   
    <label>Gender Preference:</label>
    <input type="radio" name="gender_pref" value="Male" id="maleOption"> Male
    <input type="radio" name="gender_pref" value="Female" id="femaleOption"> Female
    <input type="radio" name="gender_pref" value="Everyone" id="everyoneOption"> Everyone
     <br/>
    <label>Description:</label><br/> 
    <input type="text" name="description" id="description"><br/> 
    
    <input type="submit" value="Create" id="create">
   
  </form>
  
  
  
</body>
</html>