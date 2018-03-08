<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import = "java.util.List, java.util.Map" %>
<%@ page import="java.util.StringTokenizer"%>
<%
	List<Map<String,Object>> carOrderList = null;
	String start_date = "";
	String start_time = "";
	String end_date = "";
	String end_time = "";
	if(request.getAttribute("carOrderList")!=null){
		carOrderList = (List<Map<String,Object>>)request.getAttribute("carOrderList");	
		StringTokenizer st = new StringTokenizer(carOrderList.get(0).get("RENT_TAKE").toString(),"/");
		start_date = st.nextToken();
		start_time = st.nextToken();
		st = new StringTokenizer(carOrderList.get(0).get("RENT_RETURN").toString(),"/");
		end_date = st.nextToken();
		end_time = st.nextToken();
	}
%>
<html>
<head>
<title>CAR RENTAL 예약 확인</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="../css/BuyCss/bootstrap.css" />
<link rel="stylesheet" href="../css/BuyCss/bootstrap.min.css" />
<link rel="stylesheet" href="../css/BuyCss/main.css" />
<link rel="stylesheet" href="../css/BuyCss/buy.css" />
<link rel="stylesheet" href="../css/BuyCss/font-awesome/css/font-awesome.css" />
<link rel="stylesheet" href="../css/BuyCss/font-awesome/css/font-awesome.min.css" />

<!-- Scripts -->
<script src="../css/BuyCss/js/jquery.min.js"></script>
<script type="text/javascript" src="../css/BuyCss/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../css/BuyCss/js/bootstrap.js"></script>
<script src="../css/BuyCss/js/skel.min.js"></script>
<script src="../css/BuyCss/js/util.js"></script>
<script src="../css/BuyCss/js/main.js"></script>
<script src="../css/BuyCss/js/buy.js"></script>
<script src="../css/BuyCss/js/comment.js"></script>
</head>
<body>
<!-- ------------------------------ 전체 메인 단 시작 ------------------------------ -->
<div class="wrapper">
<!-- ---------- 상단 메뉴 바 시작 ---------- -->
<header id="header">
	<h1><a href="./main.jsp">convenience</a></h1>
	<div class="links dropdown">
		<ul>
			<li><a href="./main.jsp">HOME</a></li>
			<li><a href="./getAirList.kos">AIRPLANE</a></li>
			<li><a href="./getCarList.kos">CAR RENTAL</a></li>
			<li><a href="./getHotelList.kos">HOTEL</a></li>
			<li><a href="./getBoardList.kos">BOARD</a></li>
		</ul>
	</div>
		
	<!-- ---------- 상단 메뉴 바 시작 (오른쪽 검색 & 회원정보 & 메뉴) --------- -->
	<div class="main">
		<ul>
			<li class="search"><a class="fa-search" href="#search">검색</a>
				<form id="search" method="get" action="#">
					<input type="text" name="query" placeholder="검색" style="margin-top: 10px" />
				</form></li>
			<li class="menu"><a class="fa-bars" href="#menu">메뉴</a></li>
		</ul>
	</div>
	<!-- ---------- 상단 메뉴 바 끝 (오른쪽 검색 & 회원정보 & 메뉴) --------- -->
		
	<!-- ---------- (오른쪽 검색 & 회원정보 & 메뉴) 시작 ---------- -->
	<div id="menu">
		<!-- ---------- 회원 로그인 시작 ---------- -->
		<div>
			<ul class="actions vertical">
				<li><a href="login.jsp" class="button big fit">Log In</a></li>
			</ul>
		</div>
		<!-- ---------- 회원 로그인 끝 ---------- -->
		<!-- ---------- 메뉴바 시작 ---------- -->
		<div>
			<ul class="links">
				<li>
					<a href="edit.jsp"><h3>회원 정보</h3><p>수정 & 탈퇴</p></a>
				</li>
				<li>
					<a href="#"><h3>배송 현황</h3><p>배송중인 물품 추적</p></a>
				</li>
				<li>
					<a href="cart.jsp"><h3>장바구니</h3><p>찜해놓은 물건</p></a>
				</li>
			</ul>
		</div>
		<!-- ---------- 메뉴바 끝 ---------- -->
	</div>
	<!-- ---------- (오른쪽 검색 & 회원정보 & 메뉴) 끝 ---------- -->
</header>
<!-- ---------- 상단 메뉴 바 끝 ---------- -->

<!-- -------------------- 중단 내용 삽입부 시작 -------------------- -->
<div class="main">
<div class="content-wrapper">
	<div class="post">
		<div class="container-fluid">
		<!-- -------------------- 중단 내용 적용 부 시작 -------------------- -->
		<!-- ---------- 렌탈카 확인 시작 ---------- -->
		<table>
			<tr>
				<td align="center" style="font-size:x-large;color:blue;">주문번호</td>
				<td colspan="3"><input type="text" disabled="disabled" value="<%=carOrderList.get(0).get("RENT_PNO")%>"></td>
			</tr>
			<tr>
			<tr>
				<td align="center" style="font-size:x-large;color:blue;">렌탈카 업체</td>
				<td colspan="3"><input type="text" disabled="disabled" value="<%=carOrderList.get(0).get("RENT_NAME")%>"></td>
			</tr>
			<tr>
				<td align="center" style="font-size:x-large;color:blue;">렌 탈 카</td>
				<td colspan="3"><input type="text" disabled="disabled" value="<%=carOrderList.get(0).get("RENT_KIND")%>"></td>
			</tr>
			<tr>
				<td align="center" style="width:17%;font-size:x-large;color:blue;">인수 날짜</td>
				<td style="width:33%"><input type="text" disabled="disabled" value="<%=start_date%>"></td>
				<td align="center" style="width:17%;font-size:x-large;color:blue;">인수 시간</td>
				<td style="width:33%"><input type="text" disabled="disabled" value="<%=start_time%>"></td>
			</tr>
			<tr>
				<td align="center" style="width:17%;font-size:x-large;color:blue;">반납 날짜</td>
				<td style="width:33%"><input type="text" disabled="disabled" value="<%=end_date%>"></td>
				<td align="center" style="width:17%;font-size:x-large;color:blue;">반납 시간</td>
				<td style="width:33%"><input type="text" disabled="disabled" value="<%=end_time%>"></td>
			</tr>
			<tr>
				<td align="center" style="font-size:x-large;color:blue;">요청사항</td>
				<td colspan="3"><input type="text" disabled="disabled" value="<%=carOrderList.get(0).get("RENT_REQUEST")%>"></td>
			</tr>
			<tr>
				<td align="center" style="width:17%;font-size:x-large;color:blue;">금 액</td>
				<td style="width:33%"><input type="text" disabled="disabled" value="<%=carOrderList.get(0).get("RENT_TPRICE").toString().trim()%>"></td>
				<td>원</td>
				<td style="width:33%;font-size:x-large;color:red;">(종합 보험 가입 - 자차 포함)</td>
			</tr>
		</table>
		<!-- ---------- 렌탈카 확인 끝 ---------- -->
		<div class="page-header" align="center">
				<label class="text-danger" style="font-size:x-large;">※무단 연장(근무 시간에 연장 요청 안함)시 상당 금액의 벌금이 부과 됩니다.</label>
		</div>
		<!-- ---------- 예약자 시작 ---------- -->
<!-- 		<table>
			<tr>
				<td align="center" style="font-size:x-large;color:blue;">예약자 명</td>
				<td><input type="text" disabled="disabled"></td>
				<td align="center" style="font-size:x-large;color:blue;">인수 , 반납</td>
				<td><input type="text" value="렌탈카 (사무실)" disabled="disabled"></td>
			</tr>
			<tr>
				<td align="center" style="font-size:x-large;color:blue;">전화 번호 1</td>
				<td><input type="text" disabled="disabled"></td>
				<td align="center" style="font-size:x-large;color:blue;">전화 번호 2</td>
				<td><input type="text" disabled="disabled"></td>
			</tr>
			<tr>
				<td align="center" style="font-size:x-large;color:blue;">기타 요청</td>
				<td colspan="3"><input type="text" disabled="disabled"></td>
			</tr>
			<tr>
				<td align="center" style="font-size:x-large;color:blue;">운전자 나이</td>
				<td><input type="text" disabled="disabled"></td>
				<td colspan="2"></td> 빈공간 처리용
			</tr>
		</table> -->
		<!-- ---------- 예약자 끝 ---------- -->
		<div class="page-header" align="center">
			<label class="text-danger" style="font-size:x-large;">※전연령렌트카는 없음. 만21세미만, 면허취득 1년이하 대여불가능.</label>
			<label class="text-danger" style="font-size:x-large;"><< 예약 >> 완료 확인</label>
		</div>
		<!-- ---------- 버튼 처리 시작 ---------- -->
		<p align="center">
			<a href="./main.jsp"><button class="btn btn-large btn-default btn-block" type="button" style="font-size:large;">메인 화면</button></a>
		</p>
		<!-- ---------- 버튼 처리 끝 ---------- -->
		<!-- -------------------- 중단 내용 적용 부 끝 -------------------- -->
		</div>
	</div>
</div>
</div>
<!-- -------------------- 중단 내용 삽입부 끝 -------------------- -->

<!-- ---------- 하단 메뉴 시작 ---------- -->
<footer>
	<div id="footer" style="text-align: center">
		<ul class="icons">
			<li>
				<a href="https://twitter.com/" class="fa-twitter" target="_blank"><span class="label">Twitter</span></a>
			</li>
			<li>
				<a href="https://www.facebook.com/" class="fa-facebook" target="_blank"><span class="label">Facebook</span></a>
			</li>
			<li>
				<a href="https://www.instagram.com/" class="fa-instagram" target="_blank"><span class="label">Instagram</span></a>
			</li>
			<li>
				<a href="https://www.google.co.kr/" class="fa-google" target="_blank"><span class="label">Google</span></a>
			</li>
		</ul>
		<p class="copyright">
			&copy; Copyright. <a href="http://www.ikosmo.co.kr/" target="_blank">KOSMO</a>.
		</p>
	</div>
</footer>
<!-- ---------- 하단 메뉴 끝 ---------- -->
</div>
<!-- ------------------------------ 전체 메인 단 끝 ------------------------------ -->
</body>
</html>