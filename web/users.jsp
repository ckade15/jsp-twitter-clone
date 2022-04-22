<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Twitter - Home</title>
        
        <link rel="stylesheet" href="css/main.css" type="text/css"></link>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
        
        <script type="text/javascript">
            $(document).ready(function(){
                
                $("#add").hover(function(){
                    $(this).attr("src", "assets/white_add.svg");
                });
                $("#add").mouseleave(function(){
                    $(this).attr("src", "assets/black_add.svg");
                });
                
                
            });
        </script>
        <style>
            #like{
                background-image: url('assets/b_like.svg');
            }
            #like:hover{
               background-image: url('assets/w_like.svg'); 
            }
        </style>
    </head>
    <body>
        <header>
            <img src="assets/twitter-logo.png" id="twitter-logo"/>
            <h2>Welcome to Twitter, ${username}!</h2>
            <div style="
                 width: 40%;
                 display: flex; 
                 justify-content: center; 
                 margin-right: 50px;
                 place-content: center;
                 place-items: center;
                 ">
                <a href="Discover" class="profile">Discover</a>
                <a href="Profile" class="profile">Profile</a>            
                <a href="Logout" id="logout" class="profile" >Logout</a> 
            </div>
        </header>
        <section style="
                width: 60%;
                display: flex;
                place-items: center;
                margin-right: auto;
                margin-left: auto;
                ">
            <h2 style="
                font-size: 60px;
                text-shadow: 2px 2px 2px black;
                ">Feed</h2>
                <div style="
                     height: auto;
                     width: 100%;
                     display: flex;
                     place-items: center;
                     margin-top: 10px;
                     display: flex;
                     justify-content: end;
                     ">
                    <p style="
                       font-size: 35px;
                       color: navy;
                       font-weight: bold;
                       ">Create Tweet!</p>
                    <a href="CreateTweet">
                        <img src="assets/black_add.svg" style="
                             width: 60px;
                             height: 60px;
                             margin-top: 10px;
                             border-radius: 100%;
                             background: lightblue;
                             margin-left: 30px;
                             " 
                             id="add"
                        />
                    </a>
                </div>
        </section>
        <section style="
                 display: flex;
                 flex-wrap: wrap;
                 column-count: 3;
                 column-gap: 30px;
                 row-gap: 30px;
                 margin-left: auto;
                 margin-right: auto;
                 margin-bottom: 40px;
                 margin-top: 40px;
                 width: 100%;
                 padding-bottom: 50px;
                 justify-content: center;
                 place-items: center;
                 ">
            <c:if test="${(homeTweets == null)}">
                
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
                         padding-top: 30px;
                         padding-bottom: 30px;
                         
                         margin-bottom: 15px;
                         border-radius: 15px;
                         border: 4px inset lavender;
                         ">
                        <div style="
                             display: flex;
                             justify-content: space-between;
                             padding-right: 40px;
                             padding-left: 60px;
                             place-items: center;
                             width: 100%;
                             ">
                            <p style="
                               font-size: 24px;
                               margin-left: 35px;
                               ">Author:&nbsp;&nbsp;
                                <c:if test="${(tweet.author != username)}" >
                                    <a href="PublicProfile?username=${tweet.author}" style="
                                        font-size: 24px;
                                        margin-left: 35px;
                                        text-decoration: none;

                                        ">${tweet.author}</a> 
                                    
                                </c:if>
                                <c:if test="${(tweet.author == username)}" >
                                    <a href="Profile" style="
                                        font-size: 24px;
                                        margin-left: 35px;
                                        text-decoration: none;

                                        ">${tweet.author}</a> 
                                    
                                </c:if>
                            </p>
                            <img src="GetImage?username=${tweet.author}" width="80" height="100" style="
                                margin-top:20px;
                                margin-right: 50px;
                                margin-left: 40px;
                                border-radius: 100%;
                                border: 2px solid #0000fa;
                                background-image: url('assets/person.svg');
                                background-size: contain;
                                background-repeat: no-repeat;
                                " id="profile-pic" />
                        </div>
                        <p style="
                           font-size: 24px;
                           ">Time:&nbsp;&nbsp;&nbsp;<c:out value="${tweet.timestamp}" /><p>

                        <p style="
                           font-size: 24px;
                           margin-bottom: 40px;
                           margin-top: 10px;
                           width: 78%;
                           "
                           ><c:out value="${tweet.text}" /></p>
                        <p>
                            <c:if test="${(tweet.getFilename() != null || tweet.getFilename().equalsIgnoreCase(''))}" >
                                <img class="post" src="GetTweetImage?filename=${tweet.getId()}"  style="
                                     width: 300px;
                                     height: 300px;
                                     margin-bottom: 40px;
                                     border: 1px solid black;
                                     "/>
                            </c:if>
                        </p>
                        <c:if test="${(tweet.author != username)}" >
                            <div style="
                                 display: flex;
                                 justify-content: space-evenly;
                                 place-items: center;
                                 width: 100%;
                                 ">
                                <p style="
                                   font-size: 24px;
                                   ">${tweet.like_count} Likes</p>
                                <a href="?like_tweet_id=${tweet.id}" id="like" style="
                                   background-repeat: no-repeat;
                                   background-size: cover;
                                   width: 60px;
                                   height: 60px;
                                   " ></a>
                            </div>
                            
                        </c:if>
                        <c:if test="${(tweet.author == username)}" >
                            <div style="
                                 display: flex;
                                 justify-content: space-evenly;
                                 place-items: center;
                                 width: 100%;
                                 ">
                                <p style="
                                   font-size: 24px;
                                   ">${tweet.like_count} Likes</p>
                            </div>
                            
                        </c:if>
                    </div>
                </c:forEach>
            </c:if>
            
        </section>


    </body>
</html>
