<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
  /* CSS styles */
  body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-image: url('../images/pass.jpg');
    background-repeat: no-repeat;
    background-size: cover;
  }

  form {
    margin-top: 50px;
    text-align: center;
  }

  input[type="email"] {
    padding: 10px;
    width: 200px;
    margin-bottom: 10px;
  }
  h1{
  color:skyblue;
  text-decoration: underline;
 
  }

  button {
    background-color: #4CAF50;
    border: none;
    color: white;
    padding: 10px 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s ease;
    margin-right: 10px;
  }

  button:hover {
    background-color: #45a049;
  }

  a button {
    background-color: #808080;
    align-items: center;
    align-items: center;
  }

  a button:hover {
    background-color: #696969;
  }
  span{
  color: red;
  }
</style>
</head>
<body>
<%@include file="../jspf/Header.jspf" %><center>
<h1 >RECOVERY PASSWORD PAGE</h1>
  <form action="/merchant/forgotpassword" method="post">
   <span s>Email Address:</span>  <input type="email" name="email"><br>
    <button>Send OTP</button> <button type="reset">Cancel</button>
  </form>
  <br><br>
  <a href="/merchant/login"><button>Back</button></a>
</center>
<%@include file="../jspf/Footer.jspf" %>
</body>
</html>
