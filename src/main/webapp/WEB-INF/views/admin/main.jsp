<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="http://cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<link rel="stylesheet" href="../css/admin.css">
<style type="text/css">
.article{
	padding:0;
	margin:0;
	width: 100%;
	height: 100vh;
	text-align:center;
	display: flex;
	justify-content:center;  
}
.boxs{
	clear:both;
 	margin:0 auto;
 	margin-top: 15%;
	width: auto;
	min-height: 200px;
	height: 450px;
}
.box{
	float: left;
	margin: 50px;
	height: 100px;
	width: 100px;
	padding: 10px;
	text-align: center;
	font-weight: bolder;
	line-height: 100px;
	border-radius: 50%;
	overflow: hidden;
	transition:0.3s;
	border: 0px;
	display: block;
}
.box:hover{
	font-size: large;
	font-weight: bolder;
	margin: 0px;
	height: 200px;
	width: 200px;
	line-height: 100px;
	transition:0.4s;
	border-radius: 20px;
}
#comment{
	width: 100%; 
	height: 100px;
	padding: 5px;
	line-height: 30px;
	overflow: visible;
	animation: fadeInUp 1s;
	
}
</style>
<title>admin</title>
</head>
<body>
<div class="container">
	<div class="header">
		
		<div class="menu">
			<!-- <div class="logo">logo</div> -->
			<div class="menu-item" onclick="url('admin')"><i class="xi-home-o xi-2x"></i> 홈</div>
			<div class="menu-item" onclick="url('multiboard')"><i class="xi-layout-o xi-2x"></i> 보드 관리</div>
			<div class="menu-item" onclick="url('board')"><i class="xi-paper-o xi-2x"></i> 게시글 관리</div>
			<div class="menu-item" onclick="url('member')"><i class="xi-user-o xi-2x"></i> 회원 관리</div>
			<div class="menu-item" onclick="url('comment')"><i class="xi-comment-o xi-2x"></i> 댓글 관리</div>
			<div class="menu-item" onclick="url('message')"><i class="xi-message-o xi-2x"></i> 메시지 관리</div>
			<div class="menu-item" onclick="url('notice')"><i class="xi-bell-o xi-2x"></i> 공지사항 관리</div>
			<c:if test="true"><div class="menu-item" onclick="url('logout')"><i class="xi-flag-o  xi-2x"></i> 포세이돈 로그아웃</div></c:if>
			<div class="menu-item" onclick="url('system')" style="position: absolute; bottom: 0;"><i class="xi-info-o xi-2x"></i> 시스템 정보</div>
		</div>
<script>function url(link){location.href='./'+link;}</script>
	</div>
	<div class="main">
		<div class="article">
		<div class="boxs">
			<div class="box" style="background-color: #27a776;" onclick="location.href='./multiboard'">
			보드관리<div id="comment">보드를 추가 삭제합니다.</div>
			</div>
			<div class="box" style="background-color: #61BFFC;" onclick="location.href='./board'">
			게시글관리<div id="comment">게시글을 관리합니다</div>
			</div>
			<div class="box" style="background-color: #ADC900;" onclick="location.href='./member'">
			회원관리<div id="comment">회원을 관리합니다</div>
			</div>
			<div class="box" style="background-color: #2dB400;" onclick="location.href='./comment'">
			댓글관리<div id="comment">댓글을 관리합니다</div>
			</div>
			<div class="box" style="background-color: #DD3343;" onclick="location.href='./notice'">
			공지사항<div id="comment">공지사항을 관리합니다</div>
			</div>
			<div class="box"  style="background-color: #FEBC00;" onclick="location.href='./message'">
			메시지관리<div id="comment">회원간 주고받은 메시지를 관리합니다</div>
			</div>
		</div>
		</div>
	</div>
</div>
</body>
</html>