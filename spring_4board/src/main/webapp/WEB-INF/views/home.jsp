<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- session을 가져오기 default = true, false시 안나옴 -->
<%@ page session="true" %>

<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<hr/>
<a href="community/">커뮤니티</a>
<a href="community/list">커뮤니티 리스트</a>
<a href="community/list2">리스트 2 테스트</a>
<a href="gallery/">갤러리 가기</a><br>
<!-- model객체 customer와 session애트리뷰터 customer 이름이 같기에 sessionScope을 명시해줘서 가져오기 -->
<c:if test="${sessionScope.customer != null }">
	${sessionScope.customer.name}님(${sessionScope.customer.email}) 환영 합니다.<br>
	패스워드 확인 (${customer.password})<br>
	<a href="customer/detail">나의 정보</a>
	<a href="logout">로그아웃</a>
</c:if>

<c:if test="${sessionScope.customer == null }">
	<a href="login">로그인</a>
	<a href="customer/join?start=1">회원가입</a>
</c:if>

<!-- 애트리뷰트의 저장영역: page, request, session, application
	동일한 이름의 애트리뷰트가 존재할 경우
	el로 표시한다면 page -> request -> session, application 순서로 찾아옴
	(model은 requestScope이다)
 -->

</body>
</html>
