<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import = "java.util.List, java.util.Map, java.util.ArrayList"%>
<%
	List<Map<String,Object>> carList = new ArrayList<Map<String,Object>>();
	carList = (List<Map<String,Object>>)request.getAttribute("carList");
%>
<html>
<head>
<title>CAR RENTAL</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="../css/TicketCss/bootstrap.min.css" />
<link rel="stylesheet" href="../css/TicketCss/main.css" />
<link rel="stylesheet" href="../css/css/font-awesome.css" />
<link rel="stylesheet" href="../css/css/font-awesome.min.css" />
<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
<!-- Scripts -->
<script src="../css/TicketCss/js/jquery.min.js"></script>
<script type="text/javascript" src="../css/TicketCss/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../css/TicketCss/js/bootstrap.js"></script>
<script src="../css/TicketCss/js/skel.min.js"></script>
<script src="../css/TicketCss/js/util.js"></script>
<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
<script src="../css/TicketCss/js/main.js"></script>
<script type="text/javascript">
	function carBuy(){
		if($("#sel").val() == '-선택하세요-')
		{
			$("#al_msg").val('차 종을 선택해주세요 ');
	    	$("#check_alert").modal('show');
			return;
		}
		
		var startDate = $("#start_date").val();
		var endDate = $("#end_date").val();
		
		if(startDate>endDate){
			$("#al_msg").val('반납일을 확인해 주세요 ');
	    	$("#check_alert").modal('show');
			return;
		}
		
		if($("#start_date").val() == ''||$("#start_time").val() == '' 
			|| $("#end_date").val() == '' || $("#end_time").val() ==''){
			$("#al_msg").val('모두 입력해주세요 ');
	    	$("#check_alert").modal('show');
			return ;
		}
		
		
		$("#f_rent").attr("method","post");
		$("#f_rent").attr("action","./carBuy.kos");
		$("#f_rent").submit();
	}
	function tt(){
		$("#h_name").attr('value',$("#sel").val());
		//alert($("#h_name").val());
	}
</script>
</head>
<body>
<!-- alert 창 -->
	<div class="modal" id="check_alert" tabindex="-1" role="dialog" style="padding:10% 40%">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-body" align="center">
				<input type="text" id="al_msg" style="border:0; text-align:center;"><br>
				<button type="button" class="btn" data-dismiss="modal">확인</button>
			</div>
		</div>
	</div>
	</div>
<!-- alert창  -->
	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Header -->
		<header id="header">
			<h1>
				<a href="./main.jsp">convenience</a>
			</h1>
			<div class="links dropdown">
				<ul>
					<li><a href="./main.jsp">HOME</a></li>
					<li><a href="./getAirList.kos">AIRPLANE</a></li>
					<li><a href="./getCarList.kos">CAR RENTAL</a></li>
					<li><a href="./getHotelList.kos">HOTEL</a></li>
					<li><a href="./getBoardList.kos">BOARD</a></li>
				</ul>
			</div>

			<!-- 검색 -->
			<div class="main">
				<ul>
					<li class="search"><a class="fa-search" href="#search">검색</a>
						<form id="search" method="get" action="#">
							<input type="text" name="query" placeholder="검색"
								style="margin-top: 10px" />
						</form></li>
					<li class="menu"><a class="fa-bars" href="#menu">메뉴</a></li>
				</ul>
			</div>
			<!-- 검색끝 -->

		</header>

		<!-- 메뉴안 -->
		<div id="menu">

			<!-- 회원로그인 -->
			<div>
				<ul class="actions vertical">
					<li><a href="login.jsp" class="button big fit">Log In</a></li>
				</ul>
			</div>
			<!-- 회원로그인끝 -->

			<!-- 메뉴바 아이템 -->
			<div>
				<ul class="links">
					<li><a href="edit.jsp"><h3>회원 정보</h3><p>수정 & 탈퇴</p></a></li>
					<li><a href="reserve.jsp"><h3>예약 & 결제 현황</h3><p>상품구매 현황</p></a></li>
					<li><a href="cart.jsp"><h3>장바구니</h3><p>찜해놓은 물건</p></a></li>
				</ul>
			</div>
			<!-- 메뉴바끝 -->

		</div>

		<!-- ---------- 렌탈 화면 시작 ---------- -->
		<div id="main">
			<div id="i_ticket" class="post">
				<header>
					<div class="title">
						<h2>
							<a href="#">CAR RENTAL</a>
						</h2>
						<p>자동차 렌탈 (대여)</p>
						<p>안전운행 을 하시면 행복한 상상이 늘어 납니다.</p>
					</div>
					<img src="../images/main2.jpg" alt="" style="width:50%; box-shadow:10px 10px 20px 5px grey"/>
				</header>
				<!-- ---------- 렌탈 요금 & 예약 화면 시작 ---------- -->
				<label class="text-info" style="font-size:x-large;">렌탈 요금 계산 및 예약</label>
				<div class="row-fluid">
					<!-- ---------- 렌탈카 선택 시작 ---------- -->
					<form id="f_rent">
					<table>
						<tr>
							<td align="center"><a class="btn btn-danger disabled" style="font-size:large;width:100;">렌 탈 카</a></td>
							<td style="width:780;">
							<input id="h_name" name="h_name" type="hidden">
							<input id="h_price" name="h_price" type="hidden">
								<select class="form-control" id="sel" onChange="tt()">
									<option>-선택하세요-</option>
								<%
									if(carList !=null){
										for(int i=0; i<carList.size(); i++){
									%>
									<option><%=carList.get(i).get("RENT_KIND") %></option>
								<%	
										}
									}
								%>
								</select>
							</td>
						</tr>
						</form>
					</table>
					<!-- ---------- 렌탈카 선택 끝 ---------- -->
					<!-- ---------- 인수시간 선택 시작 ---------- -->
					<table>
						<tr>
							<td align="center"><a class="btn btn-danger disabled" style="font-size:large;">인수 날짜</a></td>
							<td style="width:110;">
								<input id="start_date" name="start_date" type="date" style="width:300px" placeholder="인수 날짜 선택">
							</td>
							<td align="center"><a class="btn btn-danger disabled" style="font-size:large;">인수 시간</a></td>
							<td>
								<input id="start_time" name="start_time" type="time" style="width:300px" placeholder="인수 시간 입력">
							</td>
						</tr>
					</table>
					<!-- ---------- 인수시간 선택 끝 ---------- -->
					
					<!-- ---------- 반납시간 선택 시작 ---------- -->
					<table>
						<tr>
							<td align="center"><a class="btn btn-danger disabled" style="font-size:large;">반납 날짜</a></td>
							<td style="width:110;">
								<input id="end_date" name="end_date" type="date" style="width:300px" placeholder="반납 날짜 선택">
							</td>
							<td align="center"><a class="btn btn-danger disabled" style="font-size:large;">반납 시간</a></td>
							<td>
								<input id="end_time" name="end_time" type="time" style="width:300px" placeholder="반납 시간 입력">
							</td>
						</tr>
					</table>
					<!-- ---------- 반납시간 선택 끝 ---------- -->
					
					<!-- ---------- 요금계산 버튼 시작 ---------- -->
					<p align="right">
						<a href="javascript:carBuy()"><button class="btn btn-large btn-success" type="button" style="font-size:large;">요금 계산</button></a>
					</p>
					<!-- ---------- 요금계산 버튼 끝 ---------- -->
				</div>
				<!-- ---------- 렌탈 요금  & 예약 화면 끝 ---------- -->
			</div>
		</div>
		<!-- ---------- 렌탈 화면 끝 ---------- -->

	
					<!-- 사이드쪽 메뉴 -->
					<div id="sidebar">

						<!-- 사이트 로고 -->
							<div id="intro">
								<a href="main.jsp" class="logo"><img src="../images/logo.jpg" alt="" /></a>
								<header>
									<h2>convenience</h2>
                           			<p>땡처리 사이트</p>
								</header>
							</div>
						<!-- 사이트 로고 끝 -->

						<!-- 광고창 -->
							<div>
								<div class="mini-posts">

									<!-- Mini Post -->
										<article class="mini-post">
							 <a href="javascript:window.open('http://www.naver.com')" class="image"><img src="../images/poster2.jpg" alt=""/></a>

										</article>
								</div>
							</div>
						<!-- 광고창 끝 -->

						<!-- 팀원소개 -->
							<div class="blurb">
								<h2>Final Project</h2>
								 <p> 김정현, 김태훈 </p>
                        		 <p> 이진만, 유태준</p>
                        		 <p> 박석영, 박혜웅</p>
							</div>
						<!-- 팀원소개 끝 -->

						<!-- 푸터라인 -->
							<div id="footer">
								<ul class="icons">
									<li><a href="https://twitter.com/" class="fa-twitter" target="_blank"><span class="label">Twitter</span></a></li>
									<li><a href="https://www.facebook.com/" class="fa-facebook" target="_blank"><span class="label">Facebook</span></a></li>
									<li><a href="https://www.instagram.com/" class="fa-instagram" target="_blank"><span class="label">Instagram</span></a></li>
									<li><a href="https://www.google.co.kr/" class="fa-google" target="_blank"><span class="label">Google</span></a></li>
								</ul>
								<p class="copyright">&copy; Copyright.  <a href="http://www.ikosmo.co.kr/" target="_blank">KOSMO</a>.</p>
							</div>
						<!-- 푸터라인 끝 -->

		</div><!-- 사이드끝 -->

</div><!-- wrapper 끝 -->



</body>
</html>