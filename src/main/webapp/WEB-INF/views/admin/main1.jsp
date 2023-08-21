<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/admin1.css">
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<script type="text/javascript">


function url(url) {
	
	location.href="./"+url;
}

</script>
</head>
<body>
	<div class="container">
	<%@ include file="menu.jsp" %>
	<div class="header">
		<div class="menu">
		<div class="menu-item" onclick="url('multiBoard')"><i class="xi-paper-o xi-2x"></i>게시판 관리</div>
		<div class="menu-item" onclick="url('member')"><i class="xi-user-o xi-2x"></i>회원 관리</div>
		<div class="menu-item" onclick="url('comment')"><i class="xi-comment-o xi-2x"></i>댓글 관리</div>
		<div class="menu-item" onclick="url('message')"><i class="xi-message-o xi-2x"></i>메시지 관리</div>
		<div class="menu-item" onclick="url('mail')"><i class="xi-mail-o xi-2x"></i>메일보내기</div>
		<div class="menu-item" onclick="url('notice')"><i class="xi-bell-o xi-2x"></i>공지사항</div>
		<div class="menu-item" onclick="url('logout')"><i class="xi-log-out xi-2x"></i>로그아웃</div>
		
		
		
		</div>
		
		<div class="main">
		<div class="article">
		<h1>메인영역</h1>
		
		<div class="box">
			보드 관리
			<div id="comment"> 보드를 관리 합니다 </div>
		</div>
		
		<div class= "box">
		 공지사항 관리
		 <div id= "comment"> 공지를 쓰고 관리합니다.</div>
		</div>
		
		
		<div class= "box">
		 공지사항 관리
		 <div id= "comment"> 메뉴 관리합니다.</div>
		</div>
	
		<div class= "box">
		 공지사항 관리
		 <div id= "comment"> 몰라 쓰고 관리합니다.</div>
		</div>
		
		
		<div class= "box">
		 공지사항 관리
		 <div id= "comment"> 쓰래 쓰고 관리합니다.</div>
		</div>

		
		
		</div>
	 </div>	
	</div>

</body>
</html>