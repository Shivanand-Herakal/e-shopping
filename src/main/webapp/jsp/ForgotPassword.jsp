<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Forgot Password</title>
    <style>
        /* CSS styles */
        body {
            background-color: #f1f1f1;
            font-family: Arial, sans-serif;
        }

        h1 {
            margin-top: 50px;
            text-align: center;
            font-size: 24px;
            color: #333;
        }

        form {
            text-align: center;
            margin-top: 50px;
        }

        input[type="text"] {
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
            outline: none;
        }

        button {
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            background-color: #4CAF50;
            color: #fff;
            cursor: pointer;
            margin-left: 10px;
        }

        button[type="reset"] {
            background-color: #ccc;
        }

        a {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #333;
        }

        /* Background animation */
        body {
            background-image: linear-gradient(45deg, #f3ec78, #af4261, #355070);
            background-size: 400% 400%;
            animation: gradient 15s ease infinite;
        }

        @keyframes gradient {
            0% {
                background-position: 0% 50%;
            }
            50% {
                background-position: 100% 50%;
            }
            100% {
                background-position: 0% 50%;
            }
        }
    </style>
</head>
<body>
    <h1>${fail}</h1>
    <h1>${pass}</h1>
    <h1 style="background-color: pink;color: skyblue">Hello ${merchant.getName()} Enter OTP</h1>
    <form action="/merchant/forgotpassword/${merchant.getEmail()}" method="post">
        Enter OTP: <input type="text" name="otp" placeholder="Enter OTP">
        <button>Verify</button><button type="reset">Cancel</button>
    </form>
    <br><br>
    <a href="/merchant/resend-forgot-opt/${merchant.getEmail()}">Click Here To Resend</a>
</body>
</html>
