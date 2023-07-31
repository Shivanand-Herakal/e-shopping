<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
/* CSS Styles */
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background: skyblue;
	animation: gradientAnimation 10s ease infinite;
}

@
keyframes gradientAnimation { 0% {
	background-position: 0% 50%;
}50%
{
background-position:100% 50%;
}
100%
{
background-position:0%50%;
}
}
.container {
	width: 300px;
	height: 450px;
	margin: 50px auto;
	padding: 20px;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	animation: fadeIn 1s ease-in-out;
}

@
keyframes fadeIn {from { opacity:0;
	
}

to {
	opacity: 1;
}

}
h1 {
	color: green;
	font-size: 24px;
	margin: 0 0 20px;
	text-align: center;
}

form {
	text-align: center;
}

input[type="text"], input[type="email"], input[type="password"] {
	width: 100%;
	padding: 10px;
	margin-bottom: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

input[type="radio"] {
	margin-right: 5px;
}

button[type="reset"], button[type="submit"] {
	padding: 10px 20px;
	background-color: #3f51b5;
	color: #fff;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	margin-top: 10px;
}

a {
	text-decoration: none;
	color: #3f51b5;
	font-weight: bold;
}

a:hover {
	text-decoration: underline;
}

.message {
	color: red;
	text-align: center;
	margin-top: 20px;
	animation: fadeIn 1s ease-in-out;
}
</style>
</head>
<body>
	<%@include file="../jspf/Header.jspf"%>
	${fail}
	<div class="container">
		<form action="/merchant/signup1" method="post"
			enctype="multipart/form-data">
			NAME:<input type="text" name="name" placeholder="Enter name"
				required="required"><br> 
			EMAIL:<input type="text" name="email" placeholder="Enter email" required="required"><br>
			MOBILE:<input type="text" name="mobilez placeholder="mobile number"
				required="required" pattern="{0-9}[10]"> <br> 
			PASSWORD:<input type="text" name="password" placeholder="Enter password"
				required="required"><br> 
			DOB:<input type="date"
				name="date"><br>
			GENDER: <input type="radio" name="gender" value="male"
				required> Male <input type="radio" name="gender"
				value="female" required> Female<br> 
			ADDRESS:<input
				type="text" name="address" required="required"><br>
			PICTURE:<input type="file" name="pic"
				accept="image/png, image/gif, image/jpeg" size="200KB"><br>
			<br>
			<br>
			<br>
			<button type="submit">SIGNUP</button>
			<button type="reset">CANCEL</button>
		</form>
	</div>
	<br>
	<br>
	<center>
		<a href="/merchant/login"><button>Back</button></a>
	</center>
</body>
</html>