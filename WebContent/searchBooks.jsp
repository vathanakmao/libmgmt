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
			<%
				String field = request.getParameter("field");
			%>
			<div class="row">
				<label>&nbsp;</label>
				<input type="radio" name="field" value="1" <% if(field==null || field.equals("1")) out.print("checked"); %>/>Title
				<input type="radio" name="field" value="2" <% if("2".equals(field)) out.print("checked"); %>/>Code
				<input type="radio" name="field" value="3" <% if("3".equals(field)) out.print("checked"); %>/>Author
				<input type="radio" name="field" value="4" <% if("4".equals(field)) out.print("checked"); %>/>Year
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
					<c:forEach var="book" items="${books}">
						<div class="row">
							<span class="result-item">
								<a href="getBookDetails?id=${book.id}">${index}). [${book.code}] "${book.title}" - ${book.author} - ${book.year}</a>
							</span>
						</div>
						<c:set var="index" value="${index+1}"/>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<h2>hello</h2>
				</c:otherwise>
			</c:choose>
		</div>
	</body>
</html>