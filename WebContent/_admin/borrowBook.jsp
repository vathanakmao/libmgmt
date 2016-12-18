<%@ page import="com.vathanakmao.libmgmt.model.Book" %>
<%@ page import="java.util.List" %>

<html>
	<head>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/index.css">
	</head>
	<body>
		<%
		request.setAttribute("curPage", "home");
		%>
		<%@ include file="/WEB-INF/jspf/adminMenu.jspf" %>
		<div>
			<h1>Borrow Book</h1>
		</div>
		<form action="borrowBook" method="post">
			<div class="row">
				<label>Book Code:</label>
				<input type="text" name="bookCode" value="${param.bookCode}"/>
				<span class="error">${errors["bookCode"]}</span>
			</div>
			<div class="row">
				<label>Student/Resident ID:</label>
				<input type="text" name="memberId" value="${param.memberId}"/>
				<span class="error">${errors["memberId"]}</span>
			</div>
			<div class="row">
				<label>&nbsp;</label>
				<input type="submit" value="Submit"/>
			</div>
		</form>
	</body>
</html>