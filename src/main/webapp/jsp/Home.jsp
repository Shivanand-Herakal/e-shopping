<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<style >
button{
background: red;
width: auto;
border-radius: 5px;
color: white;
}


</style>
</head>
<body>

<%@include file="../jspf/Header.jspf" %>
<center>
<div class="container">
${fail }
<a href="/admin/login"><button>Admin</button></a>
<a href="/merchant/login"><button>Merchant</button></a>
<a href="/customer/login"><button>Customer</button></a>

</div></center>
<%@include file="../jspf/Footer.jspf" %>
</body>
</html>