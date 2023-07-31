<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
    <style>
        /* CSS styles */
        body {
            background-color: #f1f1f1;
            font-family: Arial, sans-serif;
        }

        h1 {
            margin-top: 20px;
            text-align: center;
            font-size: 24px;
            color: #333;
        }

        h1:first-child {
            color: green;
        }

        h1:last-child {
            color: red;
        }

        form {
            text-align: center;
            margin-top: 50px;
        }

        input[type="text"],
        input[type="password"] {
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
            outline: none;
            transition: border-color 0.3s ease;
        }

        input[type="text"]:hover,
        input[type="password"]:hover {
            border-color: #4CAF50;
        }

        button[type="submit"],
        button[type="reset"] {
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            background-color: #4CAF50;
            color: #fff;
            cursor: pointer;
            margin-left: 10px;
            transition: background-color 0.3s ease;
        }

        button[type="submit"]:hover,
        button[type="reset"]:hover {
            background-color: #45a049;
        }

        a {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #333;
            transition: color 0.3s ease;
        }

        a:hover {
            color: #4CAF50;
            
        }

        /* Animation */
        @keyframes slideInFromLeft {
            0% {
                transform: translateX(-100%);
                opacity: 0;
            }
            100% {
                transform: translateX(0);
                opacity: 1;
            }
        }

        @keyframes fadeIn {
            0% {
                opacity: 0;
            }
            100% {
                opacity: 1;
            }
        }

        .container {
            animation: slideInFromLeft 0.5s ease forwards;
        }

        h2 {
            animation: fadeIn 1s ease forwards;
        }

    </style>
</head>
<body>
    <%@include file="../jspf/Header.jspf"%>

    <h1>${pass}</h1>
    <h1 style="color: red">${fail}</h1>

    <div class="container">
        <center>
            <h2>WELCOME TO MERCHANT LOGIN PAGE</h2>
            <form action="/merchant/login" method="post">
                Email: <input type="text" name="email" placeholder="Enter email" required="required"><br>
                Password: <input type="password" name="password" placeholder="Enter password" required="required"><br>
                <button type="reset">Cancel</button>
                <button type="submit">Submit</button>
            </form>
            <br><br>
            <a href="/merchant/forgotpassword">Forgot Password?</a><br><br>
            <a href="/merchant/signup">New? Click Here To Signup</a><br><br>
            <a href="/"><button>Back</button></a>
        </center>
    </div>

    <%@include file="../jspf/Footer.jspf"%>
</body>
</html>
