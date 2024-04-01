<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
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
		<h1>회원가입 페이지</h1>
	</nav>
	<form action="${root}/member" method="post">
		<fieldset>
			<input type="hidden" name="action" value="join"> <br>
			<label> 아이디 <input type="text" id="id" name="id"></label> <a href="#" onclick="checkDuplicate()">중복확인</a><br>
			<label> 비밀 번호 <input type="text" name="pw"></label> <br>
			<label> 이름 <input type="text" name="name"></label> <br>
			<label> 나이 <input type="text" name="age"></label> <br>
			
			</label> <br> <input type="submit" value="등록"> 
			<br> 
			<a href="${root}/member?action=mvlogin">로그인</a>
		</fieldset>
	</form>
	 <script>
      let isUseId = false;
      let input = document.querySelector("#userid");
      let resultDiv = document.querySelector("#result-view");
      input.addEventListener("change", function () {
        let checkid = input.value;
        let len = checkid.length;
        let url = "${root}/member?action=idcheck&id=" + checkid;
        fetch(url)
          .then((response) => response.text())
          .then((data) => resultViewText(data));
      });

      function resultViewText(data) {
          if(data==1){
              resultDiv.innerText=input.value+" 는 사용불가합니다.";
              isUseId=false;
          }else{
              resultDiv.innerText=input.value+" 는 사용가능합니다.";
              isUseId=true;
          }
      }

      let button = document.querySelector("#regist");
      button.addEventListener("click", function () {
          if(isUseId){
              document.querySelector("#signup").submit();
          }
        });
    </script>
</body>
</html>