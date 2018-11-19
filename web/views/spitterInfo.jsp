<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: comp
  Date: 14.11.2018
  Time: 0:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Spitter</title>
</head>
<body>
<table border="1" bordercolor="#008080">
    <tr>
        <td align="center"><img src="${root}/resource/images.png" width="150" height="150"/></td>

        <td><p><b><i>Login: </i></b></p> ${spitterInfo.username}
            <p><b><i>Full Name: </i></b></p> ${spitterInfo.fullName}
        </td>
    </tr>
</table>
<br>
<table border="2" bordercolor="#008080">
<c:forEach var="spittle" items="${allSpittleOfSpitter}">
    <tr>
        <td>
            >${spittle.text} <br><br> <h6>${spittle.when}</h6>
        </td>
    </tr>
</c:forEach>
</table>
<br>
<a href="/home">BACK.</a>
</body>
</html>
