<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SriAyur</title>
</head>
<body>

	<h1>Shopping Cart</h1>
	<pre>Current Shopping Cart Items: </pre>
	
	<form action="ActionServlet" method="post">
		<pre>What would you like to do:</pre>
		<input type="button" value="Add Item" />
		<input type="button" value="Edit Cart">
		<input type="button" value="Remove Item">
	</form>

</body>
</html>