<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import = "java.util.Map" %>
<%
	Map<String,Object> mem_info = (Map<String,Object>)session.getAttribute("result");
    Map<String,Object> air_info = (Map<String,Object>)request.getAttribute("airInfo");
%>
<html>
<head>
<title>항공 예약(결제) 페이지</title>
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
<script type="text/javascript">
	function setReq(){
		$("#req").attr('value','');
	}
	function airReserve(){
		if($("#air_gender").val() == '' || $("#air_kname").val() == ''
			|| $("#req").val() == ''	|| $("#air_ename").val() == ''
			|| $("#air_contact").val() == '' || $("#air_mail").val() == ''
			|| $("#air_birth").val() == ''){
			
			alert("모두 입력해 주세요");
			return;
		}
		
		$("#f_air").attr("method","post");
		$("#f_air").attr("action","./airOrder.kos");
		$("#f_air").submit();
	}
</script>
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
							<input type="text" name="query" placeholder="검색"
								style="margin-top: 10px" />
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
			<div class="table-responsive">
			<!-- ---------- 항공 스케줄 정보 시작 ---------- -->
			<form id="f_air">
			<input type="hidden" name="air_tprice" value="<%=air_info.get("total") %>">
			<input type="hidden" name="air_no" value="<%=air_info.get("air_no")%>">
			<table class="table table-bordered">
				<tr>
					<td align="center" style="width:33%; font-size:large;">가는날 항공 편 : <%=air_info.get("air_name") %></td>
					<td align="center" style="width:33%; font-size:large;">가는날 날짜 : <%=air_info.get("start_date") %></td>
					<td align="center" style="width:33%; font-size:large;"><%=air_info.get("start_point") %> >>>>> <%=air_info.get("end_point") %></td>
				</tr>
				<tr>
					<td align="center" colspan="3"><img src="../images/payment_AIR.jpg" class="img-responsive" alt="Responsive image"></td>
				</tr>
				<tr>
					<td align="center" colspan="3" style="font-size:large;">출발 시간 : <%=air_info.get("start_time") %>&nbsp;&nbsp;&nbsp; 소요 시간 : <%=air_info.get("air_time") %></td>
				</tr>
				<tr>
					<td colspan="3"></td> <!-- 줄바꿈용 -->
				</tr>
				<tr>
					<td align="center" style="width:33%; font-size:large;">오는날 항공 편 :  <%=air_info.get("air_name") %></td>
					<td align="center" style="width:33%; font-size:large;">오는날 날짜 : <%=air_info.get("end_date") %></td>
					<td align="center" style="width:33%; font-size:large;"><%=air_info.get("end_point") %> >>>>> <%=air_info.get("start_point") %></td>
				</tr>
				<tr>
					<td align="center" colspan="3"><img src="../images/payment_AIR.jpg" class="img-responsive" alt="Responsive image"></td>
				</tr>
				<tr>
					<td align="center" colspan="3" style="font-size:large;">출발 시간 : <%=air_info.get("end_time") %>&nbsp;&nbsp;&nbsp; 소요 시간 : <%=air_info.get("air_time") %></td>
				</tr>
			</table>
			<!-- ---------- 항공 스케줄 정보 끝 ---------- -->
				<tr>
					<td></td> <!-- 줄바꿈용 -->
				</tr>
			<!-- ---------- 항공 요금 정보 시작 ---------- -->
			<table class="table table-bordered">
				<tr>
					<td align="center" colspan="2" style="font-size:x-large;">- 항공 요금 정보 -</td>
				</tr>
				<tr>
					<td align="center" style="width:33%; font-size:large;">인원수 : <%=air_info.get("count") %>명</td>
					<td align="center" style="width:33%; font-size:large;">항공 요금 : <%=air_info.get("old_price")%></td>
				</tr>
				<tr>
					<td></td>
					<td align="center" style="font-size:large;">요금 합계 : <%=air_info.get("total") %>원</td>
				</tr>
			</table>
			<!-- ---------- 항공 요금 정보 끝 ---------- -->
				<tr>
					<td></td> <!-- 줄바꿈용 -->
				</tr>
			<!-- ---------- 예약자 정보 시작 ---------- -->	
			<table class="table table-bordered">
				<tr>
					<td align="center" colspan="2" style="font-size:x-large;">- 예약자 정보 -</td>
				</tr>
				<tr>
					<td align="center" style="width:50%; font-size:large;">이 름 / 한글 : <input type="search" name="air_kname" value="<%=mem_info.get("USER_NAME")%>"></td>
					<td align="center" style="width:50%; font-size:large;">이 름 / 영문 : <input type="search" name="air_ename"></td>
				</tr>
				<tr>
					<td align="center" style="width:50%; font-size:large;">연 락 처 &nbsp;&nbsp;&nbsp;&nbsp;: <input type="search" name="air_contact" value="<%=mem_info.get("USER_HP")%>"></td>
 					<td align="center" style="width:50%; font-size:large;">이 메 일 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: <input type="search" name="air_mail"></td>
				</tr>
				<tr>
					<td align="center" style="width:50%; font-size:large;">생년월일&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : <input type="date" name="air_birth"></td>
					<td align="center" style="width:50%; font-size:large;">성별 : <input type="search" name="air_gender" style="width:10%; height:10%" value="<%=mem_info.get("USER_GENDER") %>"></td>
				</tr>
				<tr>
     				<td colspan="2" align="center" style="width:50%; font-size:large;">주소  : <%=mem_info.get("USER_ADDRESS") %></td>
				</tr>
				<tr>
					<td align="center" colspan="2" style="font-size:large;">요청 사항
					<input id="req" name="req" type="text" value="요청사항 입력" onClick="setReq()"></td>
				</tr>
			</table>
			</form>
			<!-- ---------- 예약자 정보 끝 ---------- -->
				<tr>
					<td></td> <!-- 줄바꿈용 -->
					<td></td> <!-- 줄바꿈용 -->
				</tr>
			<!-- ---------- 유의 사항 시작 ---------- -->
			<table class="table table-bordered">
				<tr>
					<td align="center" colspan="3"><img src="../images/note.jpg" class="img-responsive" alt="Responsive image"></td>
				</tr>
			</table>
			<!-- ---------- 유의 사항 끝 ---------- -->
				<tr>
					<td></td> <!-- 줄바꿈용 -->
				</tr>
			<!-- ---------- 예약 & 취소 버튼 시작 ---------- -->
			<p align="center">
				<a href="javascript:airReserve()"><button class="btn btn-default btn-lg" type="button">예약 하기</button></a>
				<a href="./getAirList.kos"><button class="btn btn-default btn-lg" type="button">취소 하기</button></a>
			</p>
			<!-- ---------- 예약 & 취소 버튼 시작 ---------- -->
			</div>
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