<!DOCTYPE html>
<%@page import="Ekart_Shop.dto.Customer"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
Body{
background-image: url("");
width: 100vw;
background-color: pink;
}
table{
width:100vw; 
}
button {
	color: blue;
	background-color: white;
	transition: background-color 0.3s ease;
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

<h1>${pass}</h1>

<%
  List<Customer> customers = (List<Customer>) request.getAttribute("customers");
%>
<h1>VIEW ALL THE CUSTOMER</h1>
<table border="1px" cellpadding="25px">
  <tr >
    <th>Name</th>
    <th>Email</th>
    <th>Dob</th>
    <th>Gender</th>
    <th>Address</th>  
  </tr>
  <% for (Customer custmer: customers) { %>
    <tr>
      <td><%=custmer.getName()%></td>
      <td><%=custmer.getEmail()%></td>
      <td><%=custmer.getDob()%></td>
      <td><%=custmer.getGender()%></td>
      <td><%=custmer.getAddress()%></td>
    </tr>
  <% } %>
  
</table>
<a href="/jsp/AdminHome.jsp" style="display: block; text-align: center; margin-top: 20px;">
  <button class="a">Back</button></a>

</body>
</html>
