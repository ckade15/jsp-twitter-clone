<%-- 
    Document   : profile
    Created on : Apr 14, 2022, 10:21:25 PM
    Author     : chris
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Twitter - Profile</title>
        <link rel="stylesheet" href="css/profile.css" type="text/css" />
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $("#submit").attr("disabled", true);
                $("#add").hover(function(){
                    $(this).attr("src", "assets/white_add.svg");
                });
                $("#add").mouseleave(function(){
                    $(this).attr("src", "assets/black_add.svg");
                });
                $("#filename").change(function(){
                    console.log($("#filename").length);
                    if($(this).length > 0){
                        $("#submit").attr("disabled", false);
                    } 
                });
                $("#clear").click(function(){
                   $("#filename").attr("value", ""); 
                   $("#submit").attr("disabled", true);
                });
                var bCSS = "border: 3px solid red; background-image: url('assets/del_white.svg');background-position: 5px;height: 30px; width: 30px;background-repeat: no-repeat;background-size: auto;position: absolute;margin-left: 460px;margin-top: 180px;border-radius: 100%;background-color: navy;padding: 10px;";
                var wCSS = "border: 3px solid black; background-image: url('assets/del_black.svg');background-position: 5px;height: 30px;width: 30px;background-repeat: no-repeat; background-size: auto;position: absolute;margin-left: 460px;margin-top: 180px;border-radius: 100%;background-color: lightgray;padding: 10px;";
                $("#delete").hover(function(){
                    $(this).attr("style", wCSS);
                    console.log(bCSS);
                });
                
                $("#delete").mouseleave(function(){
                    $(this).attr("style", bCSS);
                });
                
                
            });
            
        </script>
    </head>
    <body>
        <header>
            <img src="assets/twitter-logo.png" id="twitter-logo"/>
            <h2>Welcome to Twitter, ${username}!</h2>
            <div style="
                 width: 50%;
                 display: flex;
                 
                 ">
                <a href="Twitter" class="profile">Home</a>
                <a href="Discover" class="profile">Discover</a> 
                <a href="Logout" id="logout"  >Logout</a> 
            </div>
            
        </header>
            <section style="
                     margin-top: 20px;
                     ">
                      
            <c:if test="${(filename != null)}">
                <img src="GetImage?username=${username}" width="340" height="400" style="
                     margin-top:20px;
                     margin-right: 50px;
                     " id="profile-pic" />
                <div style="
                     position: absolute;
                     width: 100%;
                     ">
                    <a href="#" style="
                       background-image: url('assets/del_white.svg');
                       background-position: 5px;
                       height: 30px;
                       width: 30px;
                       background-repeat: no-repeat;
                       background-size: auto;
                       position: absolute;
                       margin-left: 460px;
                       margin-top: 180px;
                       border-radius: 100%;
                       background-color: navy;
                       padding: 10px;
                       border: 3px solid red;
                       " id="delete"></a>
                </div>
            </c:if>
                
                

                <form action="Upload" method="post" enctype="multipart/form-data" style="
                      background: gainsboro;
                      padding: 20px;
                      width: 40%;
                      border: 3px outset navy;
                      ">
                    <h5>Account Information</h5>  
                    <p>Username: ${username}</p>
                    <c:if test="${(filename == null)}">
                        <p>Upload a profile picture</p>
                    </c:if>
                    <c:if test="${(filename != null)}">
                        <p>Update your profile picture</p>
                    </c:if>

                        <div style="
                             display: flex;
                             justify-content: center;
                             place-items: center;
                             
                             ">
                        <input type="file" accept="image/*" name="filename" style="
                               font-size: 15px;
                               margin-left: 60px;" id="filename"/>
                        <input type="button" onClick="document.getElementById('filename').value = '';"
                               style="
                               color: beige;
                               padding: 10px;
                               font-size: 14px;
                               border-radius: 15px;
                               cursor: pointer;
                               background-color: #d32b1c;
                               "
                               value="Clear"
                               id="clear"
                               />
                    </div>
                    <input type="hidden" name="action" value="upload"/>
                    <input type="submit" value="Upload" class="profile" id="submit" style="
                           margin-left: 100px;
                           margin-top: 40px;
                           "/>
            </form>
            
            
        </section>
        <hr style="
            width: 60%;
            height: 2px;
            margin: 70px auto;
            background-image: linear-gradient(to top, #020024, #091579, #00d4ff);
            border-radius: 15px;
            ">
        <!-- Display users tweets with the option to delete tweets -->
        <section style="
                 display: flex;
                 flex-direction: column;
                 margin-left: auto;
                 margin-right: auto;
                 margin-bottom: 40px;
                 margin-top: 40px;
                 width: 60%;
                 padding-bottom: 50px;
                 background-color: lightgray;
                 border-radius: 15px;
                 border: 5px inset cadetblue;
                 justify-content: center;
                 place-items: center;
                 ">
            <p style="
               font-size: 26px;
               color: navy;
               font-weight: bold;
               margin-top: 35px;
               ">Your Tweets</p>
            <a href="CreateTweet">
                <img src="assets/black_add.svg" style="
                     width: 50px;
                     height: 50px;
                     margin-left: 500px;
                     margin-top: 10px;
                     border-radius: 100%;
                     background: lightblue;
                     " 
                     id="add"
                />
            </a>
            <c:forEach var="tweet" items="${tweets}">
                <div style="
                     width: 50%;
                     background-color: beige;
                     display: flex;
                     flex-direction: column;
                     height: auto;
                     justify-content: center;
                     place-items: center;
                     margin-top: 30px;
                     padding-top: 30px;
                     padding-bottom: 30px;
                     margin-bottom: 15px;
                     border-radius: 15px;
                     ">
                    <p>Time:&nbsp;&nbsp;&nbsp;<c:out value="${tweet.timestamp}" /><p>
                    <p style="
                       margin-bottom: 40px;
                       margin-top: 10px;"
                       >Content:&nbsp;&nbsp;&nbsp;<c:out value="${tweet.text}" /></p>
                    <p>
                        <c:if test="${(tweet.getFilename() != null || tweet.getFilename().equalsIgnoreCase(''))}" >
                            <img class="post" src="GetTweetImage?filename=${tweet.getId()}"  style="
                                 width: 300px;
                                 height: 300px;
                                 "/>
                        </c:if>
                    </p>
                    <a href="#" id="delBtn" style="margin-bottom: 30px; margin-top: 10px;">Delete Tweet</a>
                </div>
            </c:forEach>
            
        </section>

    </body>
</html>