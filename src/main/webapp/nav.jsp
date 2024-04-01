<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<c:if test="${memberDto eq null }">
		<a href="${root}/member?action=mvlogin">로그인</a>
		<a href="${root}/member?action=mvjoin">회원가입</a>
	</c:if>
	<c:if test="${memberDto ne null }">
		<div>
			<span> ${memberDto.name} 님 로그인 중</span> 
			<a href="${root}/member?action=logout">로그아웃</a>
		</div>
	</c:if>