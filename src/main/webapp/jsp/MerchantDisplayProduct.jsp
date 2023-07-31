<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="Ekart_Shop.dto.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style>
  body {
    background-color: #f2f2f2;
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
  }

  h1 {
    text-align: center;
    margin: 20px 0;
  }

  table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
  }

  th, td {
    padding: 10px;
    text-align: left;
  }

  th {
    background-color: #333;
    color: #fff;
  }

  tr:nth-child(even) {
    background-color: #f2f2f2;
  }

  tr:hover {
    background-color: #ddd;
  }

  button {
    background-color: #4CAF50;
    color: #fff;
    border: none;
    padding: 8px 16px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 14px;
    transition-duration: 0.4s;
    cursor: pointer;
  }

  button:hover {
    background-color: #45a049;
  }
</style>

</head>
<body>
<%@include file="../jspf/Header.jspf" %>

<h1>${pass}</h1>
<%
  List<Product> products = (List<Product>) request.getAttribute("products");
%>
<h1>VIEW ALL THE PRODUCTS</h1>
<table border="1" cellpadding="5px" >
  <tr>
    <th>Id</th>
    <th>Name</th>
    <th>Image</th>
    <th>Price</th>
    <th>Stock</th>
    <th>Description</th>
    <th>Delete</th>
    <th>Update</th>
  </tr>
  <% for (Product product : products) { %>
    <tr>
      <td><%= product.getId() %></td>
      <td><%= product.getName() %></td>
      <td>
        <% String base64 = Base64.encodeBase64String(product.getImage()); %>
        <img src="data:image/jpeg;base64,<%= base64 %>" alt="Picture" style="width: 100px; height: auto;">
      </td>
      <td><%= product.getPrice()%></td>
      <td><%= product.getStock()%></td>
      <td><%= product.getDescription() %></td>
      <td><a href="/merchant/product-delete/<%=product.getId()%>"><button>Delete</button></a></td>
      <td><a href="/merchant/product-update/<%=product.getId()%>"><button>Update</button></a></td>
    </tr>
  <% } %>
</table>

<a href="/jsp/MerchantHome.jsp"><button>BACK</button></a>
<br><br>
<%@include file="../jspf/Footer.jspf" %>
</body>
</html>
