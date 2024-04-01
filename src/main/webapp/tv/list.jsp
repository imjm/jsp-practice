<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="root" value="${pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>차량 목록 페이지</title>
<style type="text/css">
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

nav a {
margin-right: 10px
}
</style>
</head>
<body>
<nav>
	<nav>
		<h1>TV 목록 페이지</h1>
		<%@ include file="/nav.jsp" %>
	</nav>
	<a href="${root}">메인 화면으로</a>
	<a href="${root}/tv?action=mvregist">등록하기</a>
	<table>
		<thead>
			<tr>
				<th>제품 번호</th>
				<th>제품 이름</th>
				<th>브랜드</th>
			</tr>
			<c:forEach var="tv" items="${list}">
			<tr>
				<th><a href="${root}/tv?action=detail&pn=${tv.pn}">${tv.pn}</a></th>
				<th>${tv.name}</th>
				<th>${tv.brand}</th>
			</tr>
			</c:forEach>
		</thead>
		<tbody>

		</tbody>
	</table>
</body>
</html>