<%--
  Created by IntelliJ IDEA.
  User: wrros
  Date: 4/3/2019
  Time: 2:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="css/myform.css"/>
    <title>Login</title>
    <style>
        body {
            background-color: #7f6d8c;
        }
    </style>
</head>
<body>
<div class="myForm">


<form method = "post" action ="${pageContext.request.contextPath}/checkLogin">
    <h1>Login form</h1>
    <lable for = "phone">Phone</lable>
        <input type = "text" name = "phone" placeholder="+_(___)_____">
    <br>
    <lable for = "password">Password</lable>
        <input type = "password" name = "password">

        <input type="submit" name = "submit">
    </lable>
</form>
</div>

</body>
</html>
