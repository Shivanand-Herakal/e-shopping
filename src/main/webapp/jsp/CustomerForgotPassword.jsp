<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="/customer/forgotpassword" method="post">
Email Address:<input type="email" name="email"><br>
<button>Send Link</button> <button type="reset">Cancel</button>
</form>
<br><br>
<a href="/customer/login"><button>Back</button></a>
</body>
</html>