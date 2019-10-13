<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User info</title>
</head>
<body>
	<jsp:include page="_menu.jsp"></jsp:include>
	<h3>Hello ${loginedUser.userName}</h3>
	User name: <b>${loginedUser.userName}</b>
	<br/>
	Gender: ${loginedUser.gender}<br/>
</body>
</html>