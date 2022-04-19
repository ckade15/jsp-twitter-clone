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
        
        <link rel="stylesheet" href="css/main.css" type="text/css"></link>
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
        
        <h2 style="text-align: center">Users</h2>
        <table style="
               margin-right: auto;
               margin-left:auto;
               width: 70%;
               background-color: lavender;
               padding: 30px;
               margin-bottom: 50px;
               ">
            <tr>
                <th>Id</th>
                <th>Username</th>
                <th></th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td><c:out value="${user.id}" /></td>
                    <td><c:out value="${user.username}" /></td>
                    <td><img src="GetImage?username=${user.username}" width="60" height="60" style="
                        border-radius: 100%;
                        border: 1px solid black;
                        background: url('assets/person.svg') no-repeat;
                        background-size: cover;
                        " 
                    /><td>
                    
                </tr>
            </c:forEach>
        </table>

        
    </body>
</html>
