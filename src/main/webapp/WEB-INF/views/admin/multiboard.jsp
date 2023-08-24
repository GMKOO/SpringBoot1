<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>  multiboard</title>
    <link href="css/styles.css" rel="stylesheet" />

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
	
	</script>
</head>
<body>
<%@ include file="menu.jsp" %>
 <!-- Masthead-->
    
        
               <h1>게시판관리</h1>
    			
 <div class="divTable minimalistBlack">   		
  <div class="divTableHeading">
    <div class="divTableRow">
    <div class="divTableHead col-2">번호</div>
    <div class="divTableHead col-2">카테코리</div>
    <div class="divTableHead col-2">이름</div>
    <div class="divTableHead col-3">url</div>
    <div class="divTableHead col-3">코멘트</div>
    </div>
  </div>
  
  <c:forEach items="${view }" var="row">
  <div class="divTableBody">
    <div class="divTableRow">
      <div class="divTableCell col-2">${row.b_no }</div>
      <div class="divTableCell col-2">${row.mb_cate }</div>
      <div class="divTableCell col-2">${row.b_catename }</div>
      <div class="divTableCell col-3">${row.b_url }</div>
      <div class="divTableCell col-3">${row.b_comment }</div>
      </div>
    
  </div>
  </c:forEach>
</div>


 
</body>
</html>