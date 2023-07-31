<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@include file="../jspf/Header.jspf"%>

	<h1>${pass}</h1>
	<h1 style="color: red">${fail}</h1>
	<div>
		<center>
			<h2>WELCOME TO CUSTOMER LOGIN PAGE</h2>
			<form action="/customer/login" method="post">
				Email:<input type="text" name="email" placeholder="Enter email" required="required"><br> 
				Password:<input type="text"name="password" placeholder="Enter password" required="required"><br>
				<button type="reset">Cancel</button>
				<button type="submit">Submit</button>
				</form>
				<br><br>
				<a href="/customer/forgotpassword">Forgot Password?</a> <br><br>
				<a href="/customer/signup">New?Click Here To Signup</a><br><br>
				<a href="/"><button>Back</button></a>
		</center>
	</div>

	<%@include file="../jspf/Footer.jspf"%>
</body>
</html>