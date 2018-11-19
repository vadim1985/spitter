<%--
  Created by IntelliJ IDEA.
  User: comp
  Date: 13.11.2018
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<sec:authentication var="principal" property="principal" />
<sec:authorize access="isAnonymous()">
    <H1>Hello</H1>
    <br><h3><a href="/login">Login</a> or <a href="/newSpitter">Register</a> </h3>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_SPITTER')">
    <h3>Hello ${principal.username}</h3>
</sec:authorize>
<br>
<table border="1" bordercolor="#008080">
    <c:forEach var="spittle" items="${spittles}">
        <tr>
            <td align="center"><a href="/spitter/${spittle.spitter.id}">${spittle.spitter.username}</a></td>
            <td>>${spittle.text} <br><br> ${spittle.when}</td>
        </tr>
    </c:forEach>
</table>

<sec:authorize access="hasRole('ROLE_SPITTER')">
<form name="newMessage" action="newMessage" method="post">
    <h3>Please, enter your message:</h3>
    <input type="hidden" name="user" value="${principal.username}">
    <p><textarea name="message"></textarea></p>
    <p><input type="submit"></p>
</form>
</sec:authorize>
</body>
</html>
