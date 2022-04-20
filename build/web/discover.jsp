<%-- 
    Document   : discover
    Created on : Apr 17, 2022, 2:51:58 PM
    Author     : chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Twitter - Discover</title>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

        <link rel="stylesheet" href="css/main.css" type="text/css"></link>
        <style>
            .followBtn{
                text-decoration: none;
                padding: 20px;
                background: #004ede;
                border-radius: 15px;
                color: lavender;
                font-weight: bold;
                margin-top: 20px;
                font-size: 20px;
            }
            .followBtn:hover{
                color: navy;
                background-color: rgba(33, 17, 25, .25);
                box-shadow: 3px 3px 3px rgba(33, 17, 25, 1);
            }
            span{
                font-weight: bold;
                margin-left: 10px;
            }
        </style>

    </head>
    <body>
        <header>
            <img src="assets/twitter-logo.png" id="twitter-logo"/>
            <h2>Welcome to Twitter, ${username}!</h2>
            <div style="
                 display: flex; 
                 justify-content: center; 
                 width: 40%;
                 ">
                <a href="Twitter" class="profile">Home</a>
                <a href="Profile" class="profile">Profile</a>
                <a href="Logout" id="logout">Logout</a>
            </div>
            
            
        </header>
        
        <h2 style="text-align: center;
            margin-top: 40px;
            font-weight: bold;
            font-size: 40px;
            ">Users</h2>
        
        <section style="
                 width: 90%;
                 display: flex;
                 column-count: 2;
                 margin-bottom: 50px;
                 height: auto;
                 margin-right: auto;
                 margin-left: auto;
                 flex-wrap: wrap;
                 justify-content: center;
                 column-gap: 50px;
                 row-gap: 50px;
                 ">
            <c:forEach var="user" items="${users}">
                <article style="
                     display: flex;
                     flex-direction: column;
                     width: 40%;
                     background-color: lavender;
                     border: 5px inset navajowhite;
                     margin-bottom: 20px;
                     justify-content: space-evenly;
                     align-items: center;
                     box-shadow: 5px 5px 5px rgba(33, 17, 25, .25);
                     border-radius: 15px;
                     padding-bottom: 50px;
                     padding-top: 50px;
                     ">
                    <img src="GetImage?username=${user.username}" width="100" height="100" style="
                            border-radius: 100%;
                            border: 1px solid black;
                            background: url('assets/person.svg') no-repeat;
                            background-size: cover;
                            margin-top: 15px;
                            " 
                        />
                    <p style="
                       margin-top: 20px;
                       font-size: 24px;
                       ">Username: <span><c:out value="${user.username}" /></span></p>
                    
                    <a href="?followed_by_user_id=${user_id}&following_user_id=${user.getId()}" style="
                       font-size: 18px;
                       " id="${user.getId()}" class="followBtn">Follow</a>
                </article>
            </c:forEach>
        </section>   
    </body>
    <script type="text/javascript" >
        <c:forEach var="follow" items="${following}">
                        console.log(${follow.getFollowing_user_id()});
                        try{
                            var following_id = ${follow.getFollowing_user_id()};
                            document.getElementById(following_id).innerText = "Unfollow";
                        }catch(e){
                            console.log(e);
                        }
                        
        </c:forEach>
            
    </script>
</html>
