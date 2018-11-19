<%--
  Created by IntelliJ IDEA.
  User: comp
  Date: 14.11.2018
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Create New Spitter</title>
</head>
<body>
    <form name="newSpitter" action="newSpitter" method="post">
        <h5>Login : </h5><input type="text" name="username"><br>
        <h5>Password : </h5><input type="password" name="password"><br>
        <h5>Full name : </h5><input type="text" name="fullName"><br>
        <input type="submit" value="SAVE">
    </form>
</body>
</html>
