<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html lang="en">
<head>
  <title>spring mvc01</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
 
<div class="container">
  <h2>spring mvc01</h2>
  <div class="panel panel-default">
    <div class="panel-heading">board</div>
    <div class="panel-body">
    <table class="table table-boardered table-hover">
    <tr>
    	<td>번호</td>
    	<td>제목</td>
    	<td>작성자</td>
    	<td>작성일</td>
    	<td>조회수</td>
    </tr>
    
    <c:forEach var="vo" items="${list }">
    <tr>
    	<td>${vo.idx }</td>
    	<td><a href="boardContent.do?idx=${vo.idx}">${vo.title }</td>
    	<td>${vo.writer }</td>
    	<td>${fn:split(vo.indate," ")[0]}</td>
    	<td>${vo.count }</td>
    </tr>	
    </c:forEach>
    	
    </table>
    <button class="btn btn-primary btn-sm"> 글쓰기</button>
    <a href="boardForm.do" class="btn btn-primary btn-sm"> 글쓰기</a>
    </div>
    <div class="panel-footer">Panel footer</div>
  </div>
</div>

</body>
</html>
