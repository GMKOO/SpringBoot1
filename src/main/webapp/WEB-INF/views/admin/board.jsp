<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="http://cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<link rel="stylesheet" href="../css/admin.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style type="text/css">
.viewInfo{ 
	font-weight: bolder; 
	margin-bottom: 10px;
}
.viewId, .viewDate{
	background-color: #c0c0c0;
	width : 50%;
	height:30px;
	line-height:30px;
	float: left;
	margin-bottom: 10px;
}
.viewbody{
	clear:both;
	width: 100%;
	margin-top:20x;
	height: 300px;
}
.modal-body button {
	float:right;	
}
.viewData{
	background-color: #c0c0c0;
	margin-bottom: 5px;
}
</style>
<script type="text/javascript">
function linkPage(pageNo){location.href = './board?pageNo='+pageNo;}
function boardDetail(no){
	$.ajax({
		url: "./boardView",
		data: { "no" : no, "pageNo" : 1},
		method: "POST",
		dataType: "json"
	}).done(function(data) {
		var result = data.result;
		if(result != null){	
			$("#staticBackdropLabel").text(result.mb_no + "번 글");
			$(".viewNo").val(result.mb_no);
			$(".viewInfo").text("제목_"+result.mb_title);
			$(".viewId").text("글쓴이_"+result.mname + "(" +result.mid+")");
			$(".viewDate").text("날짜_"+result.mb_date + " IP_"+result.mb_ip);
			$(".viewbody").html(result.mb_content);
			$(".viewData").text("댓글_" + result.commentCount + " 좋아요_" + result.mb_like + " 읽음_" + result.mb_load);
			$("#view").modal("show");
		}
	}).fail(function(xhr, status, errorThrown) {alert("오류 발생");});
	
}
$(function(){
	$("#reset").click(function(){location.href="./board"});
	$(".selectCate").on("change", function(){location.href="./board?mb_cate="+$(".selectCate").val();});
	$(".postDel").click(function(){
		if(confirm($(".viewNo").val()+"글을 삭제하시겠습니까?")){
			location.href="postDel?mb_no="+$(".viewNo").val()+"&pageNo=1";
		}
	});
	$(".search").click(function(){
		var search=$("#search").val();
		if(search != ""){
			if(confirm("다음 검색어로 찾으시겠습니까? 검색어 : "+search)){
				location.href="./board?search="+search;
			}
		}else{alert("검색어를 입력하세요.");$("#search").focus();}
	});
});
</script>
<title>board</title>
</head>
<body>
<div class="continer">
	<div class="header">
		
		<div class="menu">
			<!-- <div class="logo">logo</div> -->
			<div class="menu-item" onclick="url('admin')"><i class="xi-home-o xi-2x"></i> 홈</div>
			<div class="menu-item" onclick="url('setupBoard')"><i class="xi-layout-o xi-2x"></i> 보드 관리</div>
			<div class="menu-item" onclick="url('board')"><i class="xi-paper-o xi-2x"></i> 게시글 관리</div>
			<div class="menu-item" onclick="url('member')"><i class="xi-user-o xi-2x"></i> 회원 관리</div>
			<div class="menu-item" onclick="url('comment')"><i class="xi-comment-o xi-2x"></i> 댓글 관리</div>
			<div class="menu-item" onclick="url('message')"><i class="xi-message-o xi-2x"></i> 메시지 관리</div>
			<div class="menu-item" onclick="url('notice')"><i class="xi-bell-o xi-2x"></i> 공지사항 관리</div>
			<div class="menu-item" onclick="url('logout')"><i class="xi-flag-o  xi-2x"></i> 포세이돈 로그아웃</div>
			<div class="menu-item" onclick="url('system')" style="position: absolute; bottom: 0;"><i class="xi-info-o xi-2x"></i> 시스템 정보</div>
		</div>
<script>function url(link){location.href='./'+link;}</script>
	</div>
	<div class="main">
		<div class="article">
		<h1>게시글 관리</h1>
		<i class="xi-eye"></i> 글보기 가능 <i class="xi-eye-off-o"></i> 삭제된 글
		<br>
		<button type="button" class="btn btn-primary" onclick="location.href='./board.do?mb_cate=1'">메인게시판</button> <button type="button" class="btn btn-primary" onclick="location.href='./board.do?mb_cate=4'">자유게시판</button> <button type="button" class="btn btn-primary" onclick="location.href='./board.do?mb_cate=2'">공지사항</button> <button type="button" class="btn btn-primary" onclick="location.href='./board.do?mb_cate=3'">문의사항</button> 
		<input type="text" id="search"><button class="search">검색</button>
		<button class="btn btn-primary" id="reset">검색 초기화</button>
			<div class="tableDiv">
			<table class="table">
			<thead>
			<tr>
				<th>번호</th>
				<th>
				<select name="cate" class="selectCate">
					<option value="0">카테고리 선택
					<option value="1">메인게시판<option value="2">자유게시판<option value="3">공지사항<option value="4">문의사항
				</select>
				</th>
				<th>제목</th>
				<th>댓글</th>
				<th>읽은 수</th>
				<th>좋아요</th>
				<th>글쓴이</th>
				<th>날짜</th>
			</tr>
			</thead><tbody>
			<tr >
				<td>209</td>
				<td>메인게시판</td>
				<td class="title">
					<a onclick="boardDetail(209)">
					<i class="xi-eye"></i>오늘 수업 조금만 하면 안되나여</a></td>
				<td>1</td>
				<td>11</td>
				<td>0</td>
				<td>포세이돈</td>
				<td>2023-07-04 10:18:37</td>
			</tr>
			<tr >
				<td>208</td>
				<td>메인게시판</td>
				<td class="title">
					<a onclick="boardDetail(208)">
					<i class="xi-eye"></i>강남역 침수로 인해</a></td>
				<td>0</td>
				<td>17</td>
				<td>8</td>
				<td>포세이돈</td>
				<td>2023-06-29 14:02:21</td>
			</tr>
			<tr >
				<td>207</td>
				<td>메인게시판</td>
				<td class="title">
					<a onclick="boardDetail(207)">
					<i class="xi-eye"></i>홍</a></td>
				<td>0</td>
				<td>22</td>
				<td>10</td>
				<td>홍길동</td>
				<td>2023-06-29 14:01:44</td>
			</tr>
			<tr >
				<td>206</td>
				<td>메인게시판</td>
				<td class="title">
					<a onclick="boardDetail(206)">
					<i class="xi-eye"></i>오늘 수업</a></td>
				<td>4</td>
				<td>126</td>
				<td>60</td>
				<td>포세이돈</td>
				<td>2023-06-29 09:10:49</td>
			</tr>
			<tr >
				<td>205</td>
				<td>메인게시판</td>
				<td class="title">
					<a onclick="boardDetail(205)">
					<i class="xi-eye"></i>너를 닮은 꽃을 봤어</a></td>
				<td>3</td>
				<td>93</td>
				<td>50</td>
				<td>포세이돈</td>
				<td>2023-06-29 09:09:46</td>
			</tr>
			<tr >
				<td>204</td>
				<td>메인게시판</td>
				<td class="title">
					<a onclick="boardDetail(204)">
					<i class="xi-eye"></i>윤승현 강사님 멋있어요</a></td>
				<td>1</td>
				<td>355</td>
				<td>301</td>
				<td>포세이돈</td>
				<td>2023-06-29 09:07:51</td>
			</tr>
			<tr >
				<td>203</td>
				<td>자유게시판</td>
				<td class="title">
					<a onclick="boardDetail(203)">
					<i class="xi-eye"></i>2023년 6월 29일...</a></td>
				<td>0</td>
				<td>68</td>
				<td>30</td>
				<td>포세이돈</td>
				<td>2023-06-29 09:06:13</td>
			</tr>
			<tr >
				<td>202</td>
				<td>메인게시판</td>
				<td class="title">
					<a onclick="boardDetail(202)">
					<i class="xi-eye"></i> 하하하하</a></td>
				<td>0</td>
				<td>136</td>
				<td>110</td>
				<td>포세이돈</td>
				<td>2023-06-29 09:05:28</td>
			</tr>
			<tr >
				<td>201</td>
				<td>메인게시판</td>
				<td class="title">
					<a onclick="boardDetail(201)">
					<i class="xi-eye"></i>게시판1입니다</a></td>
				<td>0</td>
				<td>39</td>
				<td>20</td>
				<td>포세이돈</td>
				<td>2023-03-06 14:33:08</td>
			</tr>
			<tr >
				<td>200</td>
				<td>공지사항</td>
				<td class="title">
					<a onclick="boardDetail(200)">
					<i class="xi-eye"></i>게시판3입니다</a></td>
				<td>0</td>
				<td>14</td>
				<td>0</td>
				<td>포세이돈</td>
				<td>2023-03-06 14:32:50</td>
			</tr>
			<tr >
				<td>199</td>
				<td>공지사항</td>
				<td class="title">
					<a onclick="boardDetail(199)">
					<i class="xi-eye"></i>2022-12-09 추가사항</a></td>
				<td>0</td>
				<td>9</td>
				<td>0</td>
				<td>포세이돈</td>
				<td>2022-12-09 09:36:26</td>
			</tr>
			<tr >
				<td>198</td>
				<td>문의사항</td>
				<td class="title">
					<a onclick="boardDetail(198)">
					<i class="xi-eye"></i>문의사항</a></td>
				<td>0</td>
				<td>46</td>
				<td>17</td>
				<td>포세이돈</td>
				<td>2022-12-07 11:46:03</td>
			</tr>
			<tr style="background-color: #c0c0c0">
				<td>197</td>
				<td>문의사항</td>
				<td class="title">
					<a onclick="boardDetail(197)"><i class="xi-eye-off-o"></i>문의사항</a></td>
				<td>0</td>
				<td>61</td>
				<td>0</td>
				<td>포세이돈</td>
				<td>2022-12-07 11:42:29</td>
			</tr>
			<tr >
				<td>196</td>
				<td>메인게시판</td>
				<td class="title">
					<a onclick="boardDetail(196)">
					<i class="xi-eye"></i>2022-12-06_진행사항</a></td>
				<td>0</td>
				<td>41</td>
				<td>20</td>
				<td>포세이돈</td>
				<td>2022-12-06 11:56:57</td>
			</tr>
			<tr style="background-color: #c0c0c0">
				<td>195</td>
				<td>자유게시판</td>
				<td class="title">
					<a onclick="boardDetail(195)"><i class="xi-eye-off-o"></i>sadasd</a></td>
				<td>0</td>
				<td>1</td>
				<td>0</td>
				<td>포세이돈</td>
				<td>2022-12-05 09:47:36</td>
			</tr>
			</tbody>
		</table>
		</div>
		<!-- 페이징 -->
		<div id="paging">
			<a class="xi-step-backward" href="#" onclick="linkPage(1); return false;"></a>&#160;<a class="xi-backward" href="#" onclick="linkPage(1); return false;"></a>&#160;<strong>1</strong>&#160;<a href="#" onclick="linkPage(2); return false;">2</a>&#160;<a href="#" onclick="linkPage(3); return false;">3</a>&#160;<a href="#" onclick="linkPage(4); return false;">4</a>&#160;<a href="#" onclick="linkPage(5); return false;">5</a>&#160;<a href="#" onclick="linkPage(6); return false;">6</a>&#160;<a href="#" onclick="linkPage(7); return false;">7</a>&#160;<a href="#" onclick="linkPage(8); return false;">8</a>&#160;<a href="#" onclick="linkPage(9); return false;">9</a>&#160;<a href="#" onclick="linkPage(10); return false;">10</a>&#160;<a class="xi-forward" href="#" onclick="linkPage(11); return false;"></a>&#160;<a class="xi-step-forward" href="#" onclick="linkPage(14); return false;"></a>&#160;

		</div>
		</div>
	</div>
	
<!-- view Modal --><!-- data-bs-backdrop="static" -->
<div class="modal fade" id="view"  data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h2 class="modal-title fs-5" id="staticBackdropLabel"></h2>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
      	<div class="viewNo"></div>
      	<div class="viewInfo"></div>
      	<div class="viewId"></div>
      	<div class="viewDate"></div>
        <div class="viewbody"></div>
        <div class="viewData"></div>
        <button class="btn btn-danger postDel">글 숨기기</button>        
      </div>
    </div>
  </div>
</div>
</div>
</body>
</html>