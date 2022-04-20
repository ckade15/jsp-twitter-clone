<%-- 
    Document   : public_profile
    Created on : Apr 19, 2022, 10:00:02 PM
    Author     : chris
--%>

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
        <style>
            #clear{
                color: beige;
                padding: 10px;
                font-size: 14px;
                border-radius: 15px;
                cursor: pointer;
                background-color: #d32b1c;
            }
            #clear:hover{
              background-color: lightsalmon;  
              color: #333333;
            }
        </style>
    </head>
    <body>
        <header>
            <img src="assets/twitter-logo.png" id="twitter-logo"/>
            
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
            <h2>${username}'s Profile</h2>        
            <c:if test="${(filename != null)}">
                <img src="GetImage?username=${username}" width="340" height="400" style="
                     margin-top:20px;
                     margin-right: 50px;
                     " id="profile-pic" />
                
            </c:if>
        </section>
        <hr style="
            width: 60%;
            height: 2px;
            margin: 200px auto;
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
                 width: 80%;
                 padding-bottom: 50px;
                 background-color: lightgray;
                 border-radius: 15px;
                 border: 5px inset cadetblue;
                 justify-content: center;
                 place-items: center;
                 ">
            <p style="
               font-size: 40px;
               color: navy;
               font-weight: bold;
               margin-top: 35px;
               ">${username} Tweets</p>
            </a>
            <div style="
                 width: 100%;
                 display: flex;
                 flex-wrap: wrap;
                 column-count: 2;
                 justify-content: center;
                 place-items: center;
                 column-gap: 30px;
                 row-gap: 30px;
                 ">
                <c:forEach var="tweet" items="${tweets}">
                    <div style="
                         width: 30%;
                         background-color: beige;
                         display: flex;
                         flex-direction: column;
                         height: auto;
                         justify-content: center;
                         place-items: center;
                         margin-top: 30px;
                         padding: 50px;
                         margin-bottom: 15px;
                         border-radius: 15px;
                         border: 4px inset lavender;
                         ">
                        <p>Author: ${tweet.author} </p>
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
                                     border: 1px solid black;
                                     "/>
                            </c:if>
                        </p>
                        <a href="DeleteTweet?tweet_id=${tweet.getId()}" id="delBtn" style="margin-bottom: 30px; margin-top: 10px;">Delete Tweet</a>
                    </div>
                </c:forEach>
            </div>
        </section>

    </body>
</html>
