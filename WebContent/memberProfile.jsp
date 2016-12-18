<%@page import="com.vathanakmao.libmgmt.AppContext"%>
<%@page import="com.vathanakmao.libmgmt.service.*"%>
<%@page import="com.vathanakmao.libmgmt.model.*"%>
<%@page import="java.util.List"%>

<%@page session="true" %>

<%
MemberService service = (MemberService) AppContext.getInstance().getComponent("memberService");
BorrowService borrowService = (BorrowService) AppContext.getInstance().getComponent("borrowService");
Member member = service.getMemberDao().getById((String) request.getSession().getAttribute("memberId"));
List<BorrowedBookVo> borrowedBooks = borrowService.getBorrowedBooks(member.getId());
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
			<label>Address:</label> <%= member.getAddress() %>
		</div>
		<div class="row">
			<c:set var="borrowedBooks" value="<%= borrowedBooks %>"/>
			<c:if test="${borrowedBooks.size() > 0}">
				<div class="row">
					<hr/>
					<h3>Books borrowed (${borrowedBooks.size()}): </h3>
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
							<th>Date Borrowed</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="borrowedBook" items="${borrowedBooks}">
							<tr>
								<td>${index}</td>
								<td>${borrowedBook.book.code}</td>
								<td>${borrowedBook.book.title}</td>
								<td>${borrowedBook.book.author}</td>
								<td>${borrowedBook.book.year}</td>
								<td>${borrowedBook.borrow.dateBorrowed}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<c:set var="index" value="${index+1}"/>
			</c:if>
		</div>
	</body>
</html>