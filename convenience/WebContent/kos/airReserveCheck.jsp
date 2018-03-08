<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import = "java.util.Map, java.util.List, java.util.HashMap, java.util.ArrayList" %>
<%@page import="java.util.StringTokenizer"%>
<%
	List<Map<String,Object>> airOrderList = new ArrayList<Map<String,Object>>();
	if(request.getAttribute("airOrderList")!=null){
		airOrderList=(List<Map<String,Object>>)request.getAttribute("airOrderList");
	}
	StringTokenizer st = new StringTokenizer(airOrderList.get(0).get("AIR_STARTINGPOINT_TIME").toString(), "/");
	String start_date = st.nextToken();
	String start_time = st.nextToken();
	st = new StringTokenizer(airOrderList.get(0).get("AIR_DESTINATION_TIME").toString(), "/");
	String end_date = st.nextToken();
	String end_time = st.nextToken();
%>
<html>
<head>
<title>예약 & 결제 (조회)</title>
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
					<a href="reserve.jsp"><h3>예약 & 결제 현황</h3><p>상품구매 현황</p></a>
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
		<div class="table-responsive">
			<!-- ---------- 예약자 정보 시작 ---------- -->
			<label style="font-size:x-large;">예약자 정보</label>
			<table class="table table-bordered">
				<tr>
					<td align="center" colspan="2" style="font-size:large;">주문 번호  <%=airOrderList.get(0).get("AIR_PNO") %></td>
				</tr>
				<tr>
					<td align="center" style="width:50%;font-size:large;">이 름 / 한글 : <%=airOrderList.get(0).get("AIR_KNAME") %></td>
					<td align="center" style="width:50%;font-size:large;">이 름 / 영문 : <%=airOrderList.get(0).get("AIR_ENAME") %></td>
				</tr>
				<tr>
					<td align="center" style="width:50%;font-size:large;">생년월일 : <%=airOrderList.get(0).get("AIR_BIRTH") %></td>
					<td align="center" style="width:50%;font-size:large;">연 락 처 : <%=airOrderList.get(0).get("AIR_CONTACT") %></td>
				</tr>
				<tr>
					<td align="center" style="width:50%;font-size:large;">이 메 일 : <%=airOrderList.get(0).get("AIR_EMAIL") %></td>
					<td align="center" style="width:50%;font-size:large;">요청 사항 : <%=airOrderList.get(0).get("AIR_REQUEST") %></td>
				</tr>
			</table>
			<div class="page-header">
				<label class="text-danger" style="font-size:x-large;">※결제 시한내 결제 하지 않을경우 자동취소 됩니다.</label>
			</div>
			<!-- ---------- 예약자 정보 끝 ---------- -->
			
			<!-- ---------- 항공 스케줄 정보 시작 ---------- -->
			<label style="font-size:x-large;">항공 스케줄 & 결제 정보</label>
			<label class="text-info" style="font-size:large;">가는 여정 : <%=airOrderList.get(0).get("AIR_STARTINGPOINT") %> >>> <%=airOrderList.get(0).get("AIR_DESTINATION") %></label>
			<table class="table table-bordered">
				<tr>
					<td align="center" style="width:25%;font-size:large;">항공 정보</td>
					<td align="center" style="width:25%;font-size:large;">인원수</td>
					<td align="center" style="width:25%;font-size:large;">결제예정 금액</td>
					<td align="center" style="width:25%;font-size:large;">결제 상태</td>
				</tr>
				<tr>
					<td align="center" style="width:25%;font-size:large;"><%=airOrderList.get(0).get("AIR_NAME") %></td>
					<td align="center" style="width:25%;font-size:large;"><%=airOrderList.get(0).get("AIR_QUANTITY") %>(명)</td>
					<td align="center" style="width:25%;font-size:large;"><%=airOrderList.get(0).get("AIR_PRICE").toString().trim() %> 원</td>
					<td align="center" style="width:25%;font-size:large;">예약 완료</td>
				</tr>
			</table>
			<div class="page-header">
				<label class="text-success" style="font-size:large;">출발 날짜 : <%=end_date %> / 출발 시간 : <%=end_time %> / 소요 시간 : <%=airOrderList.get(0).get("AIR_TIME") %>시간</label>
			</div>
			<!-- --------------------------------------------------------------------------------------------------- -->
			<label class="text-info" style="font-size:large;">오는 여정 : <%=airOrderList.get(0).get("AIR_DESTINATION") %> >>> <%=airOrderList.get(0).get("AIR_STARTINGPOINT") %></label>
			<table class="table table-bordered">
				<tr>
					<td align="center" style="width:25%;font-size:large;">항공 정보</td>
					<td align="center" style="width:25%;font-size:large;">인원수</td>
					<td align="center" style="width:25%;font-size:large;">결제예정 금액</td>
					<td align="center" style="width:25%;font-size:large;">결제 상태</td>
				</tr>
				<tr>
					<td align="center" style="width:25%;font-size:large;"><%=airOrderList.get(0).get("AIR_NAME") %></td>
					<td align="center" style="width:25%;font-size:large;"><%=airOrderList.get(0).get("AIR_QUANTITY") %>(명)</td>
					<td align="center" style="width:25%;font-size:large;"><%=airOrderList.get(0).get("AIR_PRICE").toString().trim() %> 원</td>
					<td align="center" style="width:25%;font-size:large;">예약 완료</td>
				</tr>
			</table>
			<div class="page-header">
				<label class="text-success" style="font-size:large;">출발 날짜 : <%=end_date %> / 출발 시간 : <%=end_time %> / 소요 시간 : <%=airOrderList.get(0).get("AIR_TIME") %>시간</label>
			</div>
			<!-- ---------- 항공 스케줄 정보 끝 ---------- -->
			<!-- ---------- 메인 화면 돌아가기 버튼 시작 ---------- -->
			<p align="center">
				<a href="main.jsp"><button class="btn btn-large btn-block" type="button" style="font-size:large;">메인 화면 돌아가기</button></a>
			</p>
			<!-- ---------- 메인 화면 돌아가기 버튼 끝 ---------- -->
		</div>
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