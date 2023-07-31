<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Enter New Password</title>
</head>
<body>
<h1>Hello ${customer.getName()} Otp Verification Is Success</h1>
<h2>Enter The New Password</h2>
<form action="/customer/reset-password" method="post">
<input type="hidden" name="email" value="${customer.getEmail()}">
password:-<input type="password" name="password">
<button>Reset Password</button>
<button type="reset">Cancel</button>

</form>
</body>
</html>