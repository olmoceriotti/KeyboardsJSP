<!DOCTYPE html>
<html>
    <head>
        <title>Random number generator (Java Server Page example)</title>
    </head>
    <body>
        <%@ page import="java.util.Random" %>
        <h1>Random number generator (Java Server Page example)</h1>
        <%! Random rand = new Random(); %>
        <p>This is a random number generator: <%= rand.nextInt() %></p>
        <p>Back to the <a href="index.html"> home</a> or <a href="random">reload</a> to get a new random number.</p>
    </body>
</html>
