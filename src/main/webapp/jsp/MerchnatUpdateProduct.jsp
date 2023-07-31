<%@page import="java.util.List"%>
<%@page import="Ekart_Shop.dao.MerchantDao"%>
<%@page import="Ekart_Shop.dto.Merchant"%>
<%@page import="Ekart_Shop.dto.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ADD PRODUCT</title>
<style >
body{
background-color: skyblue;
color: darkgoldenrod;
}
input{
color: green;

}
legend{
color: purpule;
}
</style>
</head>
<body>
<%@include file="../jspf/Header.jspf" %>
<h1>${pass}</h1>
<fieldset>
<legend>UPDATE PRODUCT </legend>

<h1>Hello ${merchant.getName() } UPDATE THE PRODUCT DETAILS </h1>
<form action="/merchant/product-update" method="post" enctype="multipart/form-data">
ID:<input type="text" name="id" value="${product.getId() }"  readonly="readonly"><br><br>
NAME:<input type="text" name="name" value="${product.getName()}" required="required"><br><br>
PRICE:<input type="text" name="price" value="${product.getPrice() }" required="required"><br><br>
DESCRIPTION:<input type="text" name="description" value="${product.getDescription()}" required="required"><br><br>
STOCK:<input type="text" name="stock" value="${product.getStock()}" required="required"><br><br>

<button>UPDATE PRODUCT</button><button type="reset">Cancel</button>
</form>
<br><br>
</fieldset>
<a href="/jsp/MerchantHome.jsp"><button>Back</button></a>
<br><br>
<%@include file="../jspf/Footer.jspf" %>
</body>
</html>