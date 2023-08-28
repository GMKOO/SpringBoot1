<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<link rel="stylesheet" href="../css/admin.css">
<link rel="stylesheet" href="../css/multiboard.css">


<style type="text/css">

h1{
margin-left: 50px;
}

.container{
	margin-left: 50px;
}
.div-table{
	margin: 0 auto;
	display: table;
	width: 900px;
	height: auto;
}

.div-row{
	display: table-row;
	height: 30px;
	line-height: 30px;
}

.div-cell{
	display: table-cell;
	border-bottom: 1px solid gray;
	text-align: center;
}
.table-head{
	background-color: gray;
	font-weight: bold;
	text-align: center;
}

.red { 
background-color: red;
}
.yellow {

background-color: yellow;
}

</style>
<script type="text/javascript">

function gradeCh(mno,name,value) {
	
	//alert(mno+name+"!");
	
	//let select =document.getElementByName("grade");
	//let selectName = select.options[select.selectedIndex].text;
	//let selectValue = select.options[select.selectedIndex].value;
	
	//alert(selectName + " : " + selectValue );
	
	//function handleGradeChange(mNo, mName) {
	 //   var selectElement = document.getElementById('grade');
	   // var selectedValue = selectElement.value; // 선택된 옵션의 값


	
	 //   alert('Selected grade value: ' + selectedValue + '\nMember No: ' + mno + '\nMember Name: ' + name);
	   
	   if(confirm(name + "님의 등급을 변경하시겠습니까?")){
			location.href="./gradeChange?mno=" + mno + "&grade=" + value;   
	   }
	   
	}
	
	
	
	
	



</script>
</head>
<body>


	<%@ include file="menu.jsp"%>
	<h1>회원게시판</h1>
	<div class="container">
		<table class="">
			<thead>
				<tr class="row">
					<th class="col-1">회원번호</th>
		
					<th class="col-1">mbti</th>
					<th class="col-1">이름</th>
					<th class="col-2">주소</th>
					<th class="col-2">가입일</th>
					<th class="col-1">아이디</th>
					<th class="col-1">성별</th>
					<th class="col-1">생일</th>
					<th class="col-1">등급</th>

				</tr>
			</thead>


			<c:forEach items="${memberList}" var="row">
			
			 <c:if test="${row.m_grade lt 5 }">
				<tr class="1 red">
		</c:if>
		<c:if test="${row.m_grade gt 5 }">
		<tr class="1 yellow">
			</c:if>
		
					<td class="col-1">${row.m_no}</td>
				
					<td class="col-1">${row.m_mbti}</td>
					<td class="col-1">${row.m_name}</td>
					<td class="col-2">${row.m_addr}</td>
					<td class="col-2">${row.m_joindate}</td>
					<td class="col-1">${row.m_id}</td>
					<td class="col-1"><c:if test="${row.m_gender eq 1 }">♂ </c:if><c:if test="${row.m_gender eq 0 }">♀</c:if></td>
					<td class="col-1">${row.m_birth}</td>
					<td class="col-1"> 
					<select id="grade" onchange="gradeCh(${row.m_no},'${row.m_name}', this.value)">
					<optgroup label="로그인불가" >
						<option value="0" <c:if test="${row.m_grade eq 0 }">selected ="selected" </c:if> >강퇴</option>
						<option value="1" <c:if test="${row.m_grade eq 1 }">selected ="selected" </c:if>>탈퇴 </option>
						<option value="2" <c:if test="${row.m_grade eq 2 }">selected ="selected" </c:if>>징계 </option>
						<option value="3" <c:if test="${row.m_grade eq 3 }">selected ="selected" </c:if>>유배 </option>
						<option value="4" <c:if test="${row.m_grade eq 4 }">selected ="selected" </c:if>>징역 </option>
						</optgroup>
						<optgroup label="로그인가능">
						<option value="5" <c:if test="${row.m_grade eq 5 }">selected ="selected" </c:if>>평민 </option>
						
						</optgroup>
						<optgroup label="관리자들">
						<option value="6" <c:if test="${row.m_grade eq 6 }">selected ="selected" </c:if>>일반관리자 </option>
						<option value="7" <c:if test="${row.m_grade eq 7 }">selected ="selected" </c:if>>게시판관리자 </option>
						<option value="8" <c:if test="${row.m_grade eq 8 }">selected ="selected" </c:if>>가입자관리자 </option>
						<option value="9" <c:if test="${row.m_grade eq 9 }">selected ="selected" </c:if>>최고관리자 </option>
						</optgroup>
					
					</select>
					</td>
				</tr>
			</c:forEach>


		</table>
	</div>
</body>
</html>