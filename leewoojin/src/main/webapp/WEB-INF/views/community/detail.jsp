<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>     
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 상세보기</title>

<script type="text/javascript">
	//메인글 삭제 확인
	function deleteOk(idx,cpage){
		var yn= confirm( '글을 삭제하시겠습니까?');
		
		if(yn){
			alert('글 ' + idx +'를 삭제합니다.');
			//수정해야 실행됩니다.
			location.href='delete?idx='+idx+'&page='+cpage;
		}
	}
</script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/freeboard.css">  <!-- 상대경로 ../ 한단계 위로 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/modal1.css">
</head>
<body onload="resetOk()">  <!-- 문서가 생성될때 실행 -->
<h3>동아리 커뮤니티</h3>
<hr>
 <table style="width:80%;margin:auto;">
 	<tr><td width="20%" class="td1">제목</td>
 		<td width="40%" class="input1">${bean.subject}</td>
 		<td width="20%" class="td1">조회수</td>
 		<td class="input1">${bean.readCount}</td>
 	</tr>
 	<tr><td class="td1">작성자</td>
 		<td class="input1">${bean.name} 
 		<span style="font-size:70%;padding-left: 30px;">(${bean.ip})</span></td>
 		<td class="td1">작성날짜</td>
 		<td class="input1">
 		<%-- <fmt:parseDate pattern="yyyy-MM-dd'T'HH:mm" value="${bean.wdate }" var="wdate"/> --%>
 		<fmt:formatDate value="${bean.wdate }" pattern="yyyy-MM-dd"/>
 		</td>
 	</tr>
 	<tr><td class="td1">내용</td>
 		<td colspan="3" class="input1" style="text-align: left;">
 		<div  style="height: 300px;">
 		<pre>${bean.content }</pre></div></td>   <!-- 엔터,탭,기호 등 텍스트 그대로 출력할 때 사용 -->
 	</tr>
 	<tr><td colspan="4" align="center"><br>
 	<c:if test="${customer != null && customer.name eq bean.name }">
 	<a class="button" href="update?idx=${bean.idx }&page=${page}&field=${field}&findText=${findText}">수정</a>
 	<a class="button" onclick="javascript:deleteOk(${bean.idx },${page });">삭제</a>
 	</c:if>
 	<a class="button" href="list?page=${page }&field=${field}&findText=${findText}">목록</a><br><br><br>
 	</td></tr>
 </table>
</body>
</html>
