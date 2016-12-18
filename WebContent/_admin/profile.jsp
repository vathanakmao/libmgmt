<%@page import="com.vathanakmao.libmgmt.AppContext"%>
<%@page import="com.vathanakmao.libmgmt.service.*"%>
<%@page import="com.vathanakmao.libmgmt.model.*"%>
<%@page import="com.vathanakmao.libmgmt.dao.LibrarianRowMapper" %>

<%@page session="true" %>

<%
LibrarianService service = (LibrarianService) AppContext.getInstance().getComponent("librarianService");
String username = (String) request.getSession().getAttribute("librarianUsername");
Librarian librarian = service.getLibrarianDao().getUniqueBy(LibrarianRowMapper.COLUMN_USERNAME, username);
%>

<html>
	<head>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/css/index.css">
	</head>
	<body>
		<%
		request.setAttribute("curPage", "librarianProfile");
		%>
		<%@ include file="/WEB-INF/jspf/adminMenu.jspf" %>
		
		<div>
			<h1>Librarian Profile</h1>
		</div>
		<div class="row">
			<label>Username: </label> <%= librarian.getUsername() %>
		</div>
		<div class="row">
			<label>First Name: </label> <%= librarian.getFirstName() %>
		</div>
		<div class="row">
			<label>Last Name: </label> <%= librarian.getLastName() %>
		</div>
		<div class="row">
			<label>Sex: </label> <%= librarian.getSex() %>
		</div>
	</body>
</html>