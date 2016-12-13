<html>
	<head>
		<link rel="stylesheet" type="text/css" href="static/css/index.css">
	<body>
		<div>
			<h1>Member Registration</h1>
		</div>
		<div class="row">
			<span class="error">${errors["unknown"]}</span>
		</div>
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
				<label>Sex:</label>
				<select name="sex">
					<option <%= "M".equals(request.getParameter("sex")) ? "selected='selected'" : "" %> value="M">Male</option>
					<option <%= "F".equals(request.getParameter("sex")) ? "selected='selected'" : "" %> value="F">Female</option>
				</select> 
				<span class="error">${errors["sex"]}</span>
			</div>
			<div class="row">
				<label>Address:</label> <input type="text" name="address" value="${param.address}"/> <span class="error">${errors["address"]}</span>
			</div>
			<div class="row">
				<label>Password:</label> <input type="password" name="password" value="${param.password}"/> <span class="error">${errors["password"]}</span>
			</div>
			<div class="row">
				<label>Confirm Password:</label> <input type="text" name="confirmPassword" value="${param.confirmPassword}"/> <span class="error">${errors["confirmPassword"]}</span>
			</div>
			<div class="row center-text">
				<input type="Submit" value="Register"/>
			</div>
		</form>
	</body>
</html>