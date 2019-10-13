<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Form Processing</title>
</head>
<body>
<h3>Parameter value:</h3>
<br>
<%String userName = request.getParameter("userName");
String password = request.getParameter("password");
String firstName = request.getParameter("firstName");
String lastName = request.getParameter("lastName");
String gender = request.getParameter("gender");
String[] addr = request.getParameterValues("address");
%>

Username: <%=userName%><br>
Password: <%=password%><br>
First name: <%=firstName%><br>
Last name: <%=lastName%><br>
Gender: <%=gender%><br>
Address: <br>
<%if(addr!=null){
	for(String x: addr){
		if(x.isEmpty()) continue;
%>
- <%=x%><br>		
<%
	}
}else{
%>
No address.	
<%
}
%>

</body>
</html>