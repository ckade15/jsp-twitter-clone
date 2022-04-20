<%-- 
    Document   : create_tweet
    Created on : Apr 18, 2022, 8:40:46 PM
    Author     : chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Twitter - Home</title>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
        <script type="text/javascript">
            
        </script>
        <link rel="stylesheet" href="css/main.css" type="text/css"></link>
        <style>
            body{
                font-family: Georgia;
            }
            #create{
                margin-top: 40px;
                font-size: 20px;
                padding: 20px;
                border-radius: 10px;
                color: #333333;
                background-color: #00ffff;
                font-weight: bold;
                letter-spacing: 2px;
                box-shadow: 2px 2px 2px graytext;
                cursor: pointer;
                margin-bottom: 40px;
            }
            #create:hover{
                background-color: #999999;
                color: #5400c5;
            }
        </style>
    </head>
    <body>
        <header style="
                display: flex;
                width: auto;
                padding: 25px;
                ">
            <img src="assets/twitter-logo.png" id="twitter-logo"/>
            <div style="
                 width: 60%;
                 display: flex; 
                 justify-content: center; 
                 place-content: center;
                 place-items: center;
                 ">
                <a href="Twitter" class="profile" >Home</a>
                <a href="Discover" class="profile">Discover</a>
                <a href="Profile" class="profile">Profile</a>            
                <a href="Logout" id="logout" class="profile" >Logout</a> 
            </div>


            
            
        </header>
            
            <form id="form" action="CreateTweet" method="post" enctype="multipart/form-data" style="
                 width: 50%;
                 margin-right: auto;
                 margin-left: auto;
                 margin-top: 50px;
                 margin-bottom: 50px;
                 display: flex;
                 flex-direction: column;
                 justify-content: center;
                 place-items: center;
                 padding: 15px;
                 background: #54c8ff;
                 border-radius: 15px;
                  ">
                <h2>Create Tweet</h2>
                <label style="
                       font-size: 22px;
                       margin-bottom: 15px;
                       margin-right: 310px;
                       color: navy;
                       font-weight: bold;
                       margin-top: 15px;
                       ">Content: </label>
                <textarea type="text" name="text" style="
                          width: 60%;
                          height: 200px;
                          max-width: 60%;
                          max-height: 200px;
                          min-width: 60%;
                          min-height: 200px;
                          font-size: 18px;
                          " maxlength="250"></textarea>
                <p id="characters"></p>
                <label style="
                       margin-bottom: 20px;
                       font-size: 18px;
                       color: navy;
                       font-weight: bold;
                       ">Upload an image with your tweet!</label>
                <input type="file" accept="image/*" name="filename" style="
                       margin-left: 60px;
                       font-size: 16px;
                       "/>
  

                <input type="submit" id="create" value="Post Tweet"/>
                
            </form>
    </body>
</html>

