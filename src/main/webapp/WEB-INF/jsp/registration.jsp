<%--
  Created by IntelliJ IDEA.
  User: wrros
  Date: 4/3/2019
  Time: 2:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%--<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>--%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>




<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="css/myform.css"/>


    <title>Registration</title>
    <style>
        body {
            background-color: #7f6d8c;
        }
    </style>
</head>
<body>
<div class="myForm">
<h1>Registration form</h1>

<form method = "post" action ="${pageContext.request.contextPath}/completeRegistration">

    <lable for = "fname">First Name</lable>
    <input type = "text" id="fname" name="firstName" placeholder="Your name..">
    <br>
    <label for="lname">Last Name</label>
    <input type="text" id="lname" name="lastName" placeholder="Your last name..">
    <br><br>
    <lable for="phone">Phone   </lable>
    <input type = "text" id="phone" name = "phone" placeholder="+_(___)_____">
    <br>
    <lable for="password">Password</lable>
    <input type = "password" id="password" name = "password" placeholder="Your password">

    <input type="submit" value="Submit">
</form>
</div>

    </lable>
</form>

</body>
</html>
