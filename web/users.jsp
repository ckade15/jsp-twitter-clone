<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Twitter - Home</title>
        
        <link rel="stylesheet" href="css/main.css" type="text/css"></link>
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
        
        <h2>Users</h2>
        <table>
            <tr>
                <th>Id</th>
                <th>Username</th>
                <th>Password Hash</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td><c:out value="${user.id}" /></td>
                    <td><c:out value="${user.username}" /></td>
                    <td><c:out value="${user.password}" /></td>
                </tr>
            </c:forEach>
        </table>

        <h2>Add user</h2>
        <form action="Twitter" method="post">
            <label>User Name</label>
            <input type="text" name="username"/><br>
            <label>Password</label>
            <input type="password" name="password"/><br>
            
            <input type="hidden" name="action" value="createUser"/>

            <input type="submit" value="Add User"/>
        </form>
        
        <h2>Update user</h2>
        <form action="Twitter" method="post">
            <label>ID</label>
            <input type="text" name="id"/><br>
            <label>User Name</label>
            <input type="text" name="username"/><br>
            <label>Password</label>
            <input type="password" name="password"/><br>
            
            <input type="hidden" name="action" value="updateUser"/>

            <input type="submit" value="Update User"/>
        </form>
        
        <h2>Delete user</h2>
        <form action="Twitter" method="post">
            <label>ID</label>
            <input type="text" name="id"/><br>
            
            <input type="hidden" name="action" value="deleteUser"/>

            <input type="submit" value="Delete User"/>
        </form>
    </body>
</html>
