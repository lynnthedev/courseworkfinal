<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>Shopping Cart</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	</head>
	<body>
		<header>
			<nav class="navbar navbar-expand-md navbar-dark" style="background-color: grey">
				<div class="navbar-brand">
					Shopping Cart Management Page
				</div>
				<ul class="navbar-nav">
					<li>
						<a href="<%=request.getContextPath()%>/ActionServlet" class="nav-link"> Back to Shopping Cart</a>
					</li>
				</ul>
			</nav>
		</header>
		<br>
		<div class="container col-md-5">
			<div class="card">
				<div class="card-body">
					<c:if test="${product.productname != null}">
						<form action="update" method="post">
					</c:if>
					<c:if test="${product.productname == null}">
						<form action="add" method="post">
					</c:if>
							<caption>
								<h2>
									<c:if test="${product != null}"> Edit Cart </c:if>
									<c:if test="${product == null}"> Add Product into Cart </c:if>
								</h2>
							</caption>
							<c:if test="${product != null}">
								<input type="hidden" name="oriProductName" value="<c:out value='${product.productname}' />" />
							</c:if>
							<fieldset class="form-group">
								<label>Product Name</label> 
								<input type="text" value="<c:out value='${product.productname}' />" class="form-control" name="productname" required="required">
							</fieldset>
							<fieldset class="form-group">
								<label>Product Price</label>
								<input type="text" value="<c:out value='${product.productprice}' />" class="form-control" name="productprice">
							</fieldset>
							<button type="submit" class="btn btn-success">Save</button>
						</form>
				</div>
			</div>
		</div>
	</body>
</html>