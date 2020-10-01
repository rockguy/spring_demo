<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html lang="en">
<head>
    <title>Welcome</title>
    <!-- Подключаем bootstrap CSS,
        Spring boot автоматически замапит ресурс благодаря зависимости webjars в pom.xml -->
    <%--<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />--%>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!--
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" />
</head>
<body>
<div class="wrapper fadeInDown">
    <div id="formContent">
        <h2>
            Введите выши данные в форму и ожидайте подтверждения на ваш адрес электронной почты.
        </h2>

        <!-- Login Form -->
        <form action="/sign" method="post">
            <input type="text" id="login" class="fadeIn second" name="login" placeholder="логин">
            <input type="text" id="password" class="fadeIn third" name="password" placeholder="пароль">
            <input type="text" id="email" class="fadeIn fourth" name="email" placeholder="электронная почта">
            <input type="text" id="fio" class="fadeIn fifth" name="fio" placeholder="ФИО">
            <input type="submit" class="fadeIn sixth" value="Зарегистрироваться">
        </form>

        <!-- Remind Passowrd -->

    </div>
</div>

<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>