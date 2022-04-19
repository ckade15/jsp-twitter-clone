<%-- 
    Document   : login
    Created on : Apr 14, 2022, 2:02:40 PM
    Author     : chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Twitter - Login</title>
        <link rel="stylesheet" href="css/login.css" type="text/css"></link>
    </head>
    <body>
        <section style="
                 margin-top: 40px;
                 ">
            <img src="assets/twitter-logo.png" id="twitter-logo"/>
            <h2>Login</h2>
            <h3>${message}</h3>
            <form action="Login" method="post">
                <label>User Name</label>
                <input type="text" name="username" id="user"/>
                <label>Password</label>
                <input type="password" name="password" id="pass"/>
                <p>${error}</p>

                <input type="hidden" name="action" value="login" />

                <input type="submit" value="Login" id="submit" style="box-shadow: 3px 3px 3px darkgray" />
                <div id="create">
                    <p>Not a Twitter user yet?</p>&nbsp;&nbsp;
                    <a href="create_user.jsp">Create Account</a>
                </div>
            </form>
        </section>
    </body>
</html>
