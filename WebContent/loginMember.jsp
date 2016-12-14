<html>
	<head>
		<link rel="stylesheet" type="text/css" href="static/css/index.css">
	<body>
		<div>
			<h1>Member Login</h1>
		</div>
		<div class="row">
			<span class="error">${errors["unknown"]}</span>
		</div>
		<form action="loginMember" method="post">
			<div class="row">
				<label>Student/Resident ID:</label> <input type="text" name="id" value="${param.id}"/> <span class="error">${errors["id"]}</span>
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