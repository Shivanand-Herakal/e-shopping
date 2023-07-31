<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ADD PRODUCT</title>
</head>
<body>
<%@include file="../jspf/Header.jspf" %>
<fieldset>
<legend>ADD PRODUCT </legend>
<h1>Hello ${merchant.getName() } ADD THE PRODUCT DETAILS </h1>
<form action="/merchant/product-add" method="post" enctype="multipart/form-data">
NAME:<input type="text" name="name" placeholder="Enter the product name" required="required"><br><br>
PRICE:<input type="text" name="price" placeholder="Enter the product name" required="required"><br><br>
DESCRIPTION:<input type="text" name="description" placeholder="Enter the product name" required="required"><br><br>
STOCK:<input type="text" name="stock" placeholder="Enter the product name" required="required"><br><br>
IMAGE:<input type="file" name="pic"><br><br>
<button>ADD PRODUCT</button><button type="reset">Cancel</button>
</form>
<br><br>
<a href="/jsp/MerchantHome.jsp"><button>Back</button></a>
</fieldset>
<%@include file="../jspf/Footer.jspf" %>
</body>
</html>