<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>  multiboard</title>

    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<link rel="stylesheet" href="../css/admin.css">
<link rel="stylesheet" href="../css/multiboard.css">

    <script src="./js/jquery-3.7.0.min.js"></script>
	<style type="text/css">
	
	div.minimalistBlack {
  border: 3px solid #000000;
  width: 100%;
  text-align: center;
  border-collapse: collapse;
}
.divTable.minimalistBlack .divTableCell, .divTable.minimalistBlack .divTableHead {
  border: 1px solid #000000;
  padding: 5px 4px;
}
.divTable.minimalistBlack .divTableBody .divTableCell {
  font-size: 13px;
}
.divTable.minimalistBlack .divTableHeading {
  background: #CFCFCF;
  background: -moz-linear-gradient(top, #dbdbdb 0%, #d3d3d3 66%, #CFCFCF 100%);
  background: -webkit-linear-gradient(top, #dbdbdb 0%, #d3d3d3 66%, #CFCFCF 100%);
  background: linear-gradient(to bottom, #dbdbdb 0%, #d3d3d3 66%, #CFCFCF 100%);
  border-bottom: 3px solid #000000;
}
.divTable.minimalistBlack .divTableHeading .divTableHead {
  font-size: 15px;
  font-weight: bold;
  color: #000000;
  text-align: center;
}
.minimalistBlack .tableFootStyle {
  font-size: 14px;
  font-weight: bold;
  color: #000000;
  border-top: 3px solid #000000;
}
.minimalistBlack .tableFootStyle {
  font-size: 14px;
}
/* DivTable.com */
.divTable{ display: table; }
.divTableRow { display: table-row; }
.divTableHeading { display: table-header-group;}
.divTableCell, .divTableHead { display: table-cell;}
.divTableHeading { display: table-header-group;}
.divTableFoot { display: table-footer-group;}
.divTableBody { display: table-row-group;}
	
	</style>
	<script type="text/javascript">
	
	function createboard() {
		
		
		  <c:forEach items="${boardlist }" var="l">
	         <button onclick="location.href='${l.b_url }'">${l.b_catename }</button>
	       
	         
	         </c:forEach>
		
	}
	
	</script>
</head>
<body>
<%@ include file="menu.jsp" %>
 <!-- Masthead-->
    
        
      
<div class="container">
		<%@ include file="menu.jsp" %>
		<div class="main">
			<div class="article">			
				<h1>메인영역</h1>
				
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
  
  
  
  <c:forEach items="${view }" var="row">

    <div class="divTableRow">
      <div class="divTableCell col-2">${row.b_no }</div>
      <div class="divTableCell col-2">${row.mb_cate }</div>
      <div class="divTableCell col-2">${row.b_catename }</div>
      <div class="divTableCell col-3">${row.b_url }</div>
      <div class="divTableCell col-3">${row.b_comment }</div>
      <button type="submit" class="divTableCell col-2">게시판삭제 </button>
      </div>

  </c:forEach>
				</div>
					<!-- 새로 입력하는 form입니다 -->
				
			</div>
		</div>
	</div>
 




<!-- 새로 입력하는 form입니다 -->
<div class="">
	<form action="./multiboard" method="post">
	
	<input type="text" name="name" required="required" placeholder="게시판 이름 입력"	>
	
	<input type="text" name="comment" required="required" placeholder="참고를 남겨주세요"	>
	<input type="text" name="cateNum" required="required" placeholder="게시판번호를 입력해주세요"	>
	<button type="submit" onclick="createboard(this)"> 저장 </button>
	</form>


</div>

 
</body>
</html>