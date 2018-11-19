<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: comp
  Date: 17.11.2018
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page.</title>
</head>
<body>
<form method="post" action="login">
    <h2 >Please sign in</h2>
    <p>
        <label for="username">Username</label>
        <input type="text" id="username" name="username"  placeholder="Username" required autofocus>
    </p>
    <p>
        <label for="password" >Password</label>
        <input type="password" id="password" name="password" placeholder="Password" required>
    </p>
    <button type="submit">Sign in</button>
</form>
</body>
</html>
