<%@page import="com.vathanakmao.libmgmt.AppContext"%>
<%@page import="com.vathanakmao.libmgmt.service.*"%>
<%@page import="com.vathanakmao.libmgmt.model.*"%>

<%@page session="true" %>

<%
MemberService service = (MemberService) AppContext.getInstance().getComponent("memberService");
Member member = service.getMemberDao().getById((String) request.getSession().getAttribute("memberId"));
%>

<html>
	<head>
		<link rel="stylesheet" type="text/css" href="static/css/index.css">
	</head>
	<body>
		<%
		request.setAttribute("curPage", "memberProfile");
		%>
		<%@ include file="WEB-INF/jspf/menu.jspf" %>
		
		<div>
			<h1>Member Profile</h1>
		</div>
		<div class="row">
			<label>Student/Resident ID: </label> <%= member.getId() %>
		</div>
		<div class="row">
			<label>First Name: </label> <%= member.getFirstName() %>
		</div>
		<div class="row">
			<label>Last Name: </label> <%= member.getLastName() %>
		</div>
		<div class="row">
			<label>Sex: </label> <%= member.getSex() %>
		</div>
		<div class="row">
			<label>Address:</label> <%= member.getAddress() %>"
		</div>
	</body>
</html>