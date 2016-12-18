<html>
	<head>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/index.css">
	<body>
		<div>
			<h1>Librarian Login</h1>
		</div>
		<div class="row">
			<span class="error">${errors["unknown"]}</span>
		</div>
		<form action="login" method="post" >
			<div class="row">
				<label>Username:</label> <input type="text" name="username" value="${param.username}"/> <span class="error">${errors["username"]}</span>
			</div>
			<div class="row">
				<label>Password:</label> <input type="password" name="password" value="${param.password}"/> <span class="error">${errors["password"]}</span>
			</div>
			<div class="row center-text">
				<input type="Submit" value="Login"/>
			</div>
		</form>
	</body>
</html>