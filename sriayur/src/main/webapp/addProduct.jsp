<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Product</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-dark" style="background-color: #c5aa6a">
		<div class="navbar-brand">
			Add an Organic Product in your cart!
		</div>
	</nav>
	<br/>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<form action="addServlet" method="post">
					<fieldset class="form-group">
						<label>Product Name:</label>
						<input type="text" name="productname"/><br/>
			
						<label>Product Price:</label>
						<input type="number" name="productprice"/><br/>
						 						
 						<input type="submit" value="Submit"/>
 					</fieldset>
				</form>
			</div>
		</div>
	</div>
</body>
</html>