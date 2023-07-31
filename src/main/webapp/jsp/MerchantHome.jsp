<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Merchant Home</title>
</head>
<body>
<%@include file="../jspf/Header.jspf" %>
<h1> WELCOME TO MERCHANT HOME</h1>
${pass}
<br><br>
<a href="/merchant/product-add"><button>ADD PRODUCT</button></a>
<a href="/merchant/product-view"><button>VIEW ALL PRODUCTS</button></a>
<a href="/logout"><button>Logout</button></a>
<%@include file="../jspf/Footer.jspf" %>
</body>
</html>