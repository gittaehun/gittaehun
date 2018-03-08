<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<html>
<head>
<title>예약 & 결제 (확인)</title>
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
		
		<label>예약 조회</label>
		<table class="table table-bordered-hover">
			<tr>
				<td align="right" style="width:33%;height:33%; font-size:large;">기간별 조회</td>
				<td align="center" style="width:15%;height:33%;">
					<input type="date" style="width:160px;height:30px">
				</td>
				<td align="center" style="width:4%;height:33%;"> ~
				</td>
				<td align="center" style="width:15%;height:33%;">
					<input type="date" style="width:160px;height:30px">
				</td>
				<td align="left" style="width:33%;height:33%;">
					<button class="btn btn-default btn-sm" type="button" style="width:70;height:30">검색</button>
				</td>
			</tr>
 		</table>
 		<br>
 		<br>
 		<label>예약 조회</label>
		<table class="table table-bordered">
			<tr>
				<td align="center" style="width:20%;">예약일</td>
				<td align="center" style="width:20%;">예약번호</td>
				<td align="center" style="width:60%;">여정</td>
			</tr>
			<tr>
				<td align="center" style="width:20%;">2018/08/15</td>
				<td align="center" style="width:20%;">01236547890</td>
				<td align="center" style="width:60%;"><a href="./reserve_payment.jsp">인천 <<-->> 제주</a></td>
			</tr>
		</table>
		
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