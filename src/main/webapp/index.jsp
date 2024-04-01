<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<style>
nav {
display : flex;
justify-content : space-between;
align-items : center
}
nav a {
margin-right: 10px
}
</style>
</head>
<body>
	<nav>
		<h1>메인 페이지 입니다.</h1>
		<%@ include file="/nav.jsp" %>
	</nav>
	<a href="${root}/tv?action=list">TV 목록 페이지</a>
	<br>
	<br>

</body>
</html>