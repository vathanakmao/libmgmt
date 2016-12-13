<html>
	<head>
		<link rel="stylesheet" type="text/css" href="static/css/index.css">
	<body>
		<form action="registerMember" method="post">
			<div class="row">
				<label>Student/Resident ID:</label> <input type="text" name="id" value="${param.id}"/> <span class="error">${errors["id"]}</span>
			</div>
			<div class="row">
				<label>First Name:</label> <input type="text" name="firstName" value="${param.firstName}"/> <span class="error">${errors["firstName"]}</span>
			</div>
			<div class="row">
				<label>Last Name:</label> <input type="text" name="lastName" value="${param.lastName}"/> <span class="error">${errors["lastName"]}</span>
			</div>
			<div class="row">
				<label>Sex:</label> <input type="text" name="sex" value="${param.sex}"/> <span class="error">${errors["sex"]}</span>
			</div>
			<div class="row">
				<label>Address:</label> <input type="text" name="address" value="${param.address}"/> <span class="error">${errors["address"]}</span>
			</div>
			<div class="row">
				<label>Password:</label> <input type="text" name="password" value="${param.password}"/> <span class="error">${errors["password"]}</span>
			</div>
			<div class="row">
				<label>Confirm Password:</label> <input type="text" name="confirmPassword" value="${param.confirmPassword}"/> <span class="error">${errors["confirmPassword"]}</span>
			</div>
			<div class="row">
				<input type="Submit" value="Register"/>
			</div>
		</form>
	</body>
</html>