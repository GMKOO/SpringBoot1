<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin post</title>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<link rel="stylesheet" href="../css/admin.css">
<link rel="stylesheet" href="../css/multiboard.css">
    <script src="../js/jquery-3.7.0.min.js"></script>
</head>
	<script type="text/javascript">

	/*
	$(function(){
			$(".title").click(function(){
				let mbno =$(this).siblings(".mbno").text();
				let parent = $(this).parent();
				
				let div =  "<div class='div-row'>본문 내용 찍을자리 </div>";
				parent.append(div);
				
			});
	});
	*/
		
/*
	$(function(){
		$(".title").click(function(){
		
			let serchid =
		
		$(".div-sell").append(serchid);
*/


$(function(){
	$(".title").click(function(){
		var mbno = $(this).siblings(".mb_no").text();
        var mbdetail = $(this).parent().siblings("." + mbno);
        var mb_content = $(this).parent().siblings(".mb_detail").children();
        if (mb_content.is(":visible")) {
        	$.ajax({
				url: "./detail",
				type: "get",
				data: {mbno:mbno},
				dataType: "json",
				success: function (data) {
					mb_content.hide();
				},
				error: function (error) {
					alert("에러발생");
				}
			});
        }
        
       	if (!(mb_content.is(":visible"))) {
			$.ajax({
				url: "./detail",
				type: "get",
				data: {mbno:mbno},
				dataType: "json",
				success: function (data) {
					mbdetail.html('<td colspan="7" class="mb_content">' + data.content + '</td>');
				},
				error: function (error) {
					alert("에러발생");
				}
			});
        }
        
        
	});
});

	
	</script>

 
 <div class="container">
		<%@ include file="menu.jsp" %>
		<div class="main">
			<div class="article">			
			
				<c:choose>
				
   				 <c:when test="${list[0].count gt 0}">
     				   <h1>게시글 관리 ${list[0].count}개 글이 있음</h1>
    				</c:when>
    			<c:otherwise>
        <!-- 여기에 0개가 아닐 때의 내용을 작성 --> 
        <h1>게시판에 등록된 글이 없습니다.</h1>
    			</c:otherwise>
					</c:choose>
				
				

		
				
				<div class="boardlist">
			<button onclick="location.href='./post?cate=0'">전체보기</button>
				
				<c:forEach items="${boardList }" var="b">
					<button onclick="location.href='./post?cate=${b.mb_cate}'">${b.b_catename }</button>
				</c:forEach>
					
					<form action="./post" method="get">
					<select name="searchN">
						<option value="title">제목</option>
						<option value="content">내용</option>
						<option value="nick">글쓴이</option>
						<option value="id">ID</option>
					</select>
					<input type="text" name ="searchV" required="required">
					<input type="hidden" name="cate" value="${param.cate }">
					<button type="submit">검색</button>
					</form>
					
					</div>
					
					
						
					
					<div class="div-table">
						<div class="div-row table-head">
							<div class="div-cell table-head">번호</div>
							<div class="div-cell table-head">카테고리</div>
							<div class="div-cell table-head">제목</div>
							<div class="div-cell table-head">글쓴이</div>
							<div class="div-cell table-head">날짜</div>
							<div class="div-cell table-head">읽음수</div>
							<div class="div-cell table-head">삭제</div>
						</div>
						<c:forEach items="${list }" var="row">
						<div class="div-row"> <c:if test ="${row.mb_del eq 0 }"> gray</c:if>
							<div class="mb_no">${row.mb_no }</div>
							<div class="div-cell">${row.b_catename}</div>
							<div class="div-cell title">${row.mb_title }</div>
							<div class="div-cell">${row.m_name }(${row.m_id})</div>
							<div class="div-cell">${row.mb_date }</div>
							<div class="div-cell">${row.mb_read }</div>
							<div class="div-cell">${row.mb_del }</div>
						</div>
						<div class="${row.mb_no } mb_detail"> </div>
						</c:forEach>
					</div>
					<!-- 새로 입력하는 form입니다 -->
				
			</div>
		</div>
	</div>
 
 


</body>
</html>