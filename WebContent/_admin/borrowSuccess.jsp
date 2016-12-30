<%@ page import="com.vathanakmao.libmgmt.model.Book" %>
<%@ page import="java.util.List" %>

<html>
	<head>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/index.css">
	</head>
	<body>
		<%
		request.setAttribute("curPage", "borrowSuccess");
		%>
		<%@ include file="/WEB-INF/jspf/adminMenu.jspf" %>
		<div>
			<h4>Book has been successfully borrowed.</h4>
		</div>
	</body>
</html>