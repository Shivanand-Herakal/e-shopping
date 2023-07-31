<!DOCTYPE html>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="Ekart_Shop.dto.Merchant"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View All Merchant</title>
<style>
  body {
    font-family: Arial, sans-serif;
    background-image: url("../images/th.jpg");
    background-repeat: repeat;
  }

  h1 {
    text-align: center;
    margin-top: 20px;
    color: white;
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
    background-color: #f2f2f2;
  }

  tr:nth-child(even) {
    background-color: #f2f2f2;
  }

  tr:hover {
    background-color: LIGHTBLUE;
  }

  a.button {
    display: block;
    text-align: center;
    margin-top: 20px;
    text-decoration: none;
  }

  button {
    padding: 10px 20px;
    background-color: #4CAF50;
    color: white;
    border: none;
    cursor: pointer;
    font-size: 16px;
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
  List<Merchant> merchants = (List<Merchant>) request.getAttribute("merchants");
%>
<h1>VIEW ALL THE MERCHANT</h1>
<table border="1px" cellpadding="25px">
  <tr>
    <th>Name</th>
     <th>Email</th>
    <th>Image</th>
    <th>Dob</th>
    <th>Gender</th>
    <th>address</th>  
  </tr>
  <% for (Merchant merchant: merchants) { %>
    <tr>
      <td><%=merchant.getName()%></td>
      <td><%=merchant.getEmail()%></td>
       <td>
        <% String base64 = Base64.encodeBase64String(merchant.getPicture()); %>
        <img src="data:image/jpeg;base64,<%= base64 %>" alt="Picture" style="width: 100px; height: auto;">
      </td>
      <td><%=merchant.getDob()%></td>
      <td><%=merchant.getGender()%></td>
      <td><%=merchant.getAddress()%></td>
    </tr>
  <% } %>
</table>

<a href="/jsp/AdminHome.jsp" class="button">
  <button>Back</button>
</a>
<%@include file="../jspf/Footer.jspf" %>
</body>
</html>
