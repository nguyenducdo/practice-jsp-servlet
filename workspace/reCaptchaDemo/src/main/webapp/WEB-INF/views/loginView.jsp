<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>

<!-- reCAPTCHA Libary -->
<script src='https://www.google.com/recaptcha/api.js?hl=en'></script>

</head>
<body>

	<h3>Login:</h3>

	<p style="color: red;">${errorString}</p>

	<form name="loginForm" method="POST" action="doLogin">
		<table border="0">
			<tr>
				<td>User Name</td>
				<td><input type="text" name="userName" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" /></td>
			</tr>
		</table>

		<!-- reCAPTCHA -->
		<div class="g-recaptcha"
			data-sitekey="6LcTv7AUAAAAANUEPTf552tadKULUX_qTovO1wh0"></div>

		<input type="submit" value="Submit" />

	</form>
	<p style="color: blue;">User Name: tom, Password: tom001</p>

</body>
</html>