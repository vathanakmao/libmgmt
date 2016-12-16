<%@ page import="com.vathanakmao.libmgmt.model.Book" %>
<%@ page import="java.util.List" %>

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
		<form action="searchBooks" method="get">
			<div class="row">
				<label>Books:</label>
				<input type="text" name="text" value="${param.text}"/>
				<span class="error">${errors["text"]}</span>
			</div>
			<div class="row">
				<label>&nbsp;</label>
				<input type="radio" name="field" value="1" ${param.field == null || param.field.equals("1") ? "checked" : ""}/>Title
				<input type="radio" name="field" value="2" ${"2" == param.field ? "checked" : ""}/>Code
				<input type="radio" name="field" value="3" ${"3" == param.field ? "checked" : ""}/>Author
				<input type="radio" name="field" value="4" ${"4" == param.field ? "checked" : ""}/>Year
				<span class="error">${errors["field"]}</span>
			</div>
			<div class="row">
				<label>&nbsp;</label>
				<input type="submit" value="Search"/>
			</div>
		</form>
		<div class="row">
			<c:choose>
				<c:when test="${books != null && books.size() == 0}">
					<div class="row">
						<hr/>
						<h3>Not found</h3>
					</div>
				</c:when>
				<c:when test="${books != null && books.size() > 0}">
					<div class="row">
						<hr/>
						<h3>Result (${books.size()})</h3>
					</div>
					<c:set var="index" scope="page" value="1"/>
					<table border="1">
						<thead>
							<tr>
								<th>No</th>
								<th>Code</th>
								<th>Title</th>
								<th>Author</th>
								<th>Year</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="book" items="${books}">
								<tr>
									<td>${index}</td>
									<td>${book.code}</td>
									<td>${book.title}</td>
									<td>${book.author}</td>
									<td>${book.year}</td>
									<td>${book.stock == 0 ? "Borrowed" : "Available"}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<c:set var="index" value="${index+1}"/>
				</c:when>
			</c:choose>
		</div>
	</body>
</html>