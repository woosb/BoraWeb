<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<!-- <meta name="viewport" content="width=device-width" initial-scale="1">
 --><link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">

<title>JSP 게시판 웹 사이트</title>
</head>
<body>
<%
	String userID = null;
	if(session.getAttribute("userID") != null){
		userID = (String)session.getAttribute("userID");
	}else if(session.getAttribute("userID") == null){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('회원만 글쓰기가 가능 합니다.')");
		script.println("location.href('login_view.jsp')");
		script.println("</script>");
	}
%>
	<nav class="navbar navbar-default">
		<div class="navbar-hreader">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="main_view.jsp">JSP 게시판 웹 사이트</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="main_view.jsp">메인</a></li>
				<li class="active"><a href="bbs_view.jsp">게시판</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
					회원관리
					<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="logout.do">로그아웃</a></li>
					</ul> 
				</li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<form action="write.do" method="post">
				<table class="table table-stripted" style="text-align: center; border: 1px solid #dddddd">
					<thead>
						<tr>
							<th colspan="2" style="background-color:#eeeeee; text-align: center;">게시판 글쓰기 양식</th>					
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" class="form-control" placeholder="글 제목" name="bbsTitle" maxlength="50"></td>
						</tr>
						<tr>
							<td><textarea class="form-control" placeholder="글 내용" name="bbsContent" maxlength="2048" style="height: 300px;"></textarea></td>
						</tr>					
					</tbody>
				</table>
				<input type="submit" class="btn btn-primary pull-right" value="글쓰기">
			</form>
		</div>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>