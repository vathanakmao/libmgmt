<html>
	<head>
		<link rel="stylesheet" type="text/css" href="static/css/index.css">
	</head>
	<body>
		<%
		request.setAttribute("curPage", "home");
		%>
		<%@ include file="WEB-INF/jspf/menu.jspf" %>
		<div>
			<h1>Search Books</h1>
		</div>
		<form action="searchBooks" method="post">
			<div class="row">
				<label>Books:</label>
				<input type="text" name="book"/>
			</div>
			<div class="row">
				<label>&nbsp;</label>Œp
				<input type="radio" name="field" value="title" checked="checked"/>Title
				<input type="radio" name="field" value="code"/>Code
				<input type="radio" name="field" value="author"/>Author
				<input type="radio" name="field" value="year"/>Year
			</div>
			<div class="row">
				<label>&nbsp;</label>
				<input type="submit" value="Search"/>
			</div>
		</form>
	</body>
</html>