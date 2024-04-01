<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>차량 등록 페이지</title>
<style>
nav {
	display: flex;
	justify-content: space-between;
	align-items: center
}

nav a {
	margin-right: 10px
}
</style>
</head>
<body>
	<nav>
		<h1>차량 등록 페이지</h1>
		<%@ include file="/nav.jsp" %>
	</nav>
	<form action="${root}/tv" method="post">
		<fieldset>
			<input type="hidden" name="action" value="regist"> <br>
			<label> 제품 번호 <input type="text" name="pn"></label> <br>
			<label> 제품 이름 <input type="text" name="name"></label> <br>
			<label> 브랜드 <input type="number" name="brand"></label> <br>
			<label> 가격 <input type="text" name="price"></label> <br>
			
			</label> <br> <input type="submit" value="등록"> 
			<br> 
			<a href="${root}/tv?action=list">목록으로</a>
		</fieldset>
	</form>
</body>
</html>