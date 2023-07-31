<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Enter New Password</title>
</head>
<body>
<%@include file="../jspf/Header.jspf" %>
<h1>Hello ${merchant.getName()} Otp Verification Is Success</h1>
<h2>Enter The New Password</h2>
<form action="/merchant/reset-password" method="post">
<input type="hidden" name="email" value="${merchant.getEmail()}">
password:-<input type="password" name="password">
<button>Reset Password</button>
<button type="reset">Cancel</button>
</form>
<%@include file="../jspf/Footer.jspf" %>
</body>
</html>