<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create an Account</title>
</head>
<body>

    <p><b><a id="home" href="home.jsp">Back</a></b></p>  
    <h1 id="logo"> Study With Me </h1>
    
        <div class="navibar">
            <a class="home" href="home.jsp">Home</a>
            <a class="profile" href="profile.jsp">Profile</a>
            <a class="post" href="post.jsp">Post</a>
            <a class="post" href="mygroup.jsp">MyGroup</a>
            <a class="post" href="groupsearch.jsp">GroupSearch</a> 
            <a class="signout" href="landing.html">Signout</a>
            
        </div>

    <form action="groupsearch" method="post"> 
       <h3>Search Study Group </h3>
    <label for="selectSubject">By</label>
    <select name="searchSubject" id="selectSubject">
      <option value="groupName">Group Name</option>
      <option value="subject">Subject</option>
      <option value="location">Location</option>
      <option value="duration">Duration</option>
      <option value="genderPref">Preference</option>
    </select>
    <input type="text" name="keyword" id="keyword">
   <input type="submit" value="search" id="search">
    </form>


    
</body>
</html>