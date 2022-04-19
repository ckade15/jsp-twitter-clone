<%-- 
    Document   : create_user
    Created on : Apr 14, 2022, 2:12:07 PM
    Author     : chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Twitter - Create Account</title>
        <link rel="stylesheet" href="css/create.css" type="text/css"></link>
    </head>
    <body>
        <section style="
                 margin-top: 50px;
                 ">
            <img src="assets/twitter-logo.png" id="twitter-logo"/>
            <h2>Create Account</h2>
            <form action="CreateAccount" method="post">
                <label>User Name</label>
                <input type="text" name="username" id="user"/><br>
                <label>Password</label>
                <input type="password" name="password" id="pass"/><br>
                <p>${error}</p>

                <input type="hidden" name="action" value="createUser"/>

                <input type="submit" value="Create Account" id="submit"/>
                <div class="create">
                    <p>Already have an account?</p>&nbsp;&nbsp;
                    <a href="login.jsp">Login Page</a>
                </div>
            </form>
        </section>
    </body>
</html>
