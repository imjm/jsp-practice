<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>차량 상세 페이지</title>
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
	<h1>차량 상세 페이지</h1>
	<%@ include file="/nav.jsp" %>
	</nav>
	<a href="${root}/tv?action=list">목록으로</a>
	<table>
		<tr>
			<th>제품 번호</th>
			<td>${dto.pn}</td>
		</tr>
		<tr>
			<th>제품 이름</th>
			<td>${dto.name }</td>

		</tr>
		<tr>
			<th>브랜드</th>
			<td>${dto.brand }</td>
		</tr>
		<tr>
			<th>가격</th>
			<td>${dto.price }</td>
		</tr>
		<tr>
	</table>
	<a href="${root}/tv?action=mvupdate&pn=${dto.pn}">수정</a>
	<a href="${root}/tv?action=delete&pn=${dto.pn}">삭제</a>
</body>
</html>