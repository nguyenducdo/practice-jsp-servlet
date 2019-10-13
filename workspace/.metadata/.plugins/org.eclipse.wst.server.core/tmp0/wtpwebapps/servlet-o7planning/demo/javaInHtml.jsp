<%@ page import="java.util.Random,java.text.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% Random rand = new Random(); 
		int value = rand.nextInt(3);
		if(value==0){
	%>
	<h1>Random value = <%=value%></h1>
	<% } else if(value==1){ %>
	<h1>Random value = <%=value%></h1>
	<% } else{%>
	<h1>Random value = <%=value %></h1>
	<%} %>
	
	<a href="<%=request.getRequestURI()%>"><%=request.getRequestURI()%></a>
	<hr>
	<a href="https://www.google.com/">google.com</a>
	
	<br>
	<jsp:include page="../demo2/jspStandardActions.jsp"></jsp:include>
</body>
</html>