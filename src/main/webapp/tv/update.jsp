<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>차량 수정</title>
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
		<h1>차량 수정 페이지</h1>
		<%@ include file="/nav.jsp" %>
	</nav>
	<form action="${root}/tv" method="post">
		<input type="hidden" name="action" value="update">
		<fieldset>
			<label> 제품 번호 <input type="text" name="pn" value="${dto.pn}" readonly></label> 
			<br> 
			<label> 제품 이름 <input type="text" name="name" value="${dto.name}"></label> 
			<br>
			<label> 브랜드 <input type="text" name="brand" value="${dto.brand}"></label> 
			<br> 
			<label> 가격 <input type="text" name="price" value="${dto.price}"></label> 
			<br> <input type="submit" value="수정"> <br> <a
				href="${root}/tv?action=detail&pn=${dto.pn}">상세 페이지</a>
		</fieldset>
	</form>
</body>
</html>