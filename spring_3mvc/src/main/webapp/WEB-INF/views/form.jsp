<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>day3</title>
</head>
<body>
	<h3>사용자 입력 폼</h3>
	<form action="regist" method="post">
		<input name="id" placeholder="아이디입력">
		<input name="amount" placeholder="주문수량">
		<input name="devDate" type="date">
		<input type="submit" value="주문">
	</form>
</body>
</html>