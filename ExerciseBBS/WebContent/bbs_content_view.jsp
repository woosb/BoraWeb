<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.javalec.bbs.BbsDAO" %>
<%@ page import="com.javalec.bbs.BbsDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<!-- <meta name="viewport" content="width=device-width" initial-scale="1">
 --><link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<title>JSP 게시판 웹 사이트</title>
<style type="text/css"> 
	a, a:hover{
		color: #000000;
		text-decoration: none;
	}
</style>
</head>
<body>
<%
	String userID = null;
	if(session.getAttribute("userID") != null){
		userID = (String)session.getAttribute("userID");
	}
	int pageNumber = 1;
	if(request.getParameter("pageNumber") != null){
		pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
	}
	int bbsID = 0;
	if(request.getParameter("bbsID") != null){
		bbsID = Integer.parseInt(request.getParameter("bbsID"));
		session.setAttribute("bbsID", bbsID);
	}
	if(bbsID == 0){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('유효하지 않은 글 입니다.')");
		script.println("location.href = 'bbs.jsp'");
		script.println("</script>");
	}
	BbsDTO bbsDTO = new BbsDAO().getBbs(bbsID);
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
			<%
				if(userID == null){
			%>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
						접속하기
						<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li><a href="login_view.jsp">로그인</a></li>
							<li><a href="join_view.jsp">회원가입</a></li>
						</ul> 
					</li>
				</ul>
			<%
				}else{
			%>
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
			<%
				}
			%>
		</div>
	</nav>
	

	<div class="container">
		<div class="row">
			<table class="table table-stripted" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3" style="background-color:#eeeeee; text-align: center;">게시판 글보기</th>					
					</tr>
				</thead>
			<tbody>
					<tr>
						<td style="width: 20%;">글 제목</td>
						<td colspan="2"><%= bbsDTO.getBbsTitle().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>")  %></td>
					</tr>
					<tr>
						<td>작성자</td>
						<td colspan="2"><%= bbsDTO.getUserID() %></td>
					</tr>
					<tr>
						<td>작성일자</td>
						<td colspan="2"><%= bbsDTO.getBbsDate().substring(0, 11)+bbsDTO.getBbsDate().substring(11,13)+"시"+bbsDTO.getBbsDate().substring(14,16)+"분"  %></td>
					</tr>
					<tr>
						<td>내용</td>
						<td colspan="2" style="min-height: 200px; text-align: left;"><%= bbsDTO.getBbsContent().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>") %></td>						
					</tr>									
				</tbody>
			</table>
			<a href="bbs_view.jsp" class="btn btn-primary">목록</a>		
			<%
				if(userID != null && userID.equals(bbsDTO.getUserID())){
			%>
			<a href="bbs_modify_view.jsp?bbsID=<%= bbsID %>" class="btn btn-primary">수정</a>
			<a onclick="return confirm('정말로 삭제하시겠 습니까?')" href="delete.do" class="btn btn-primary">삭제</a>
			<%
				}
			%>	
			<a href="write_view.jsp" class="btn btn-primary pull-right">글쓰기</a>
		</div>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>