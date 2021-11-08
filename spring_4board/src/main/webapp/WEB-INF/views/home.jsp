<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ page session="false" %> --%>
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
<a href="community">커뮤니티</a>
<a href="community/list">커뮤니티 리스트</a>
<a href="community/list2">리스트 2 테스트</a>
<c:if test="${customer != null }">
	${customer.name}님(${customer.email}) 환영 합니다.<br>
	<a href="">나의 정보</a>
	<a href="logout">로그아웃</a>
</c:if>

<c:if test="${customer == null }">
	<a href="login">로그인</a>
</c:if>
</body>
</html>
