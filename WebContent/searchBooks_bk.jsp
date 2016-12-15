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
			<%
			List<Book> books = (List<Book>) request.getAttribute("books");
			if (books != null) {
				if (books.size() == 0) {
			%>
					<div class="row">
						<hr/>
						<h3>Not found</h3>
					</div>
				<%					
				} else {
				%>
					<div class="row">
						<hr/>
						<h3>Result (<%= books.size() %>)</h3>
					</div>
				<%
					int i = 1;
					for (Book book : books) {
				%>
						<div class="row">
							<span class="result-item">
								<a href="getBookDetails?id=<%= book.getId() %>"><%= i %>). [<%= book.getCode() %>] "<%= book.getTitle() %>" - <%= book.getAuthor() %> - <%= book.getYear() %></a>
							</span>
						</div>
			<%	
					}
				}
			} 
			%>
		</div>
	</body>
</html>