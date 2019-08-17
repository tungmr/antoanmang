<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Page</title>

<!-- Bootstrap core CSS -->
<link href="http://localhost:8080/AwesomeNews/template/admin/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>
	<div class="container">
		<h2>Admin Area</h2>
		<form action="/action_page.php">
			<div class="form-group">
				<label for="username">Username:</label> <input type="text"
					class="form-control" id="username" placeholder="Enter username"
					name="username">
			</div>
			<div class="form-group">
				<label for="password">Password:</label> <input type="password"
					class="form-control" id="password" placeholder="Enter password"
					name="password">
			</div>
			<div class="checkbox">
				<label><input type="checkbox" name="remember">
					Remember me</label>
			</div>
			<button type="submit" class="btn btn-success">Submit</button>
		</form>
	</div>
</body>
</html>