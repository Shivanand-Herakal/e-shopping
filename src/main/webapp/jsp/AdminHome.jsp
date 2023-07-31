<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
  /* CSS styles */
  body {
    background-image: url('../images/png.jpg');
    background-size: cover;
    background-repeat: no-repeat;
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
  }  
  h1 {
    color: white;
    text-align: center;
    margin-top: 50px;
  }

  .a {
    background-color: #4CAF50; /* Green */
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin-top: 20px;
    cursor: pointer;
    transition: background-color 0.3s ease; /* Transition effect */
  }

  .a:hover {
    background-color: red; /* Darker green on hover */
  }
</style>
</head>
<body>
<%@include file="../jspf/Header.jspf" %>
<center>
  <h1>ADMIN HOME</h1><br><br>
  <a href="/admin/product-approve"><button>APPROVE PRODUCT</button></a><br><br>
  <a href="/admin/customer-approve"><button> CUSTOMER APPROVE</button></a><br><br>
  <a href="/admin/merchant-approve"><button> MERCHANT APPROVE</button></a><br><br>
  <a href="/admin/payment-add"><button>Add Payment Method</button></a><br><br>
  <a href="/"><button class="a">Logout</button></a>
</center>
<%@include file="../jspf/Footer.jspf" %>
</body>
</html>
