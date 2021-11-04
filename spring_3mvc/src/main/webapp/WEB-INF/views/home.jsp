<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!-- 초기에 없던 encoding 태그 추가 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>

	<P>The time on the server is ${serverTime}.</P>
	<%-- ${} : EL serverTime은 에트리뷰트  --%>


	<hr>
	<ul>
		<li><a href="/">그냥 / </a></li>
		<li><a href="/day3">/day3</a></li>
		<li><a href="test">homecontroller test</a></li>
		<li><a href="hello">homecontroller hello</a></li>
		<li><a href="form">homecontroller form</a></li>
	</ul>

	<ul>
		<li><a href=""></a></li>
		<li><a href=""></a></li>
		<li><a href=""></a></li>
	</ul>
	<ul>
		<li><a href=""></a></li>
		<li><a href=""></a></li>
		<li><a href=""></a></li>
	</ul>
	<ul>
		<li><a href=""></a></li>
		<li><a href=""></a></li>
		<li><a href=""></a></li>
	</ul>

</body>
</html>
