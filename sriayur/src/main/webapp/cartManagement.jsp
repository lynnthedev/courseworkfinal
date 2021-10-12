<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart Management</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<header>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a href="#" class="navbar-brand">Your Shopping Cart</a>
        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse8">
            <span class="navbar-toggler-icon"></span>
        </button>
    </nav>
</header>
 <br>
 <div class="row">
 <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
 <div class="container">
 <h3 class="text-center">[Your Shopping Cart Items]</h3>
 <hr>
 <div class="container text-left">
 <a href="<%=request.getContextPath()%>/addProduct.jsp" class="btn btn-outline-primary">Add Product</a>
 </div>
 <br>
 <table class="table table-bordered">
 <thead>
 <tr>
 	<th>Product Name</th>
 	<th>Product Price</th>
 	<th>Action</th>
 </tr>
 </thead>
 <tbody><!-- for (Todo todo: todos) { -->
 <c:forEach var="products" items="${listCart}">
	 <script>
	 	console.log("${products.productname}");
	 </script>
	 <tr>
	 	<td>
	 		<c:out value="${products.productname}" />
	 	</td>
	 	<td>
	 		<c:out value="${products.productprice}" />
	 	</td>
	 	<td>
	 		<a href="ActionServlet/edit?productname=<c:out value='${products.productname}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; 
	 		<a href="ActionServlet/delete?productname=<c:out value='${products.productname}' />">Delete</a>
 		</td>
	 </tr>
 </c:forEach>
 <!-- } -->
 </tbody>
 </table>
 </div>
 </div>
</body>
</html>