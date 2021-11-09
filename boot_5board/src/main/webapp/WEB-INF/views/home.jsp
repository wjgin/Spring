<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<a href="community/list">커뮤니티</a>
<!-- 404오류는 요청 url과 컨트롤러의 매핑 url 일치하는지 확인하세요. -->

</body>
</html>
