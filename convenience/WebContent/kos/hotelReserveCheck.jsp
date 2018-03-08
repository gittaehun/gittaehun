<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.Map, java.util.HashMap, java.util.List, java.util.ArrayList, kos.util.KoreanConversion, java.text.*" %>
<%
Map<String,Object> mem_info= new HashMap<String,Object>();
if(session.getAttribute("result")!=null){
	mem_info = (Map<String,Object>)session.getAttribute("result");
}
List<Map<String, Object>> reserveList = new ArrayList<Map<String,Object>>();
Map<String, Object> reserveMap = new HashMap<String, Object>();
if(reserveList != null){
	reserveList = (List<Map<String, Object>>) request.getAttribute("reserveList");
}
if(reserveList.size()>0){
	reserveMap = reserveList.get(0);
}
out.print(reserveList.size());
%>
<html>
<head>
<title>호텔 예약(결제) 페이지</title>
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
		<%
      if(session.getAttribute("result")!=null){
      //Map<String,Object> rMap = (Map<String,Object>) session.getAttribute("result");
     %>
                     <div>
                        <ul class="actions vertical">
                           <li><%=mem_info.get("USER_NAME")%>님 환영합니다.</li>
                        </ul>
                        <ul class="actions vertical">
                           <li>
                           <a href="./logout.kos"class="button big fit">Log Out</a></li>
                        </ul>
                     </div>
   <% } else{%>
                     <div>
                        <ul class="actions vertical">
                           <li><a href="login.jsp" class="button big fit">Log In</a></li>
                        </ul>
                     </div>
   <% } %>
                  <!-- 회원로그인끝 -->

                  <!-- Links -->
                     <div>
                        <ul class="links">
                     <%
                        if(session.getAttribute("result")!=null){
                     %>         
                           <li>
                              <a href="edit.jsp">
                                 <h3>회원 정보</h3>
                                 <p>수정 & 탈퇴</p>
                              </a>
                           </li>
                           <li><a href="#">
                                 <h3>주문 현황</h3>
                                 <p>주문 내역</p>
                           </a></li>
                           <% } else { %>
                           <li>
                           <a href="login.jsp?command=open">
                                 <h3>회원가입</h3>
                           </a></li>
                     <% } %>                           
                           <li>
                              <a href="cart.jsp">
                                 <h3>장바구니</h3>
                                 <p>찜해놓은 물건</p>
                              </a>
                           </li>
                        </ul>
                     </div>
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
			<!-- ---------- 항공 스케줄 정보 시작 ---------- -->
			<h1>호텔 예약 정보</h1>
			<%if(reserveList.size()>0){ %>
			<table class="table table-bordered">
				<tr>
					<th colspan="2"><b>객실 정보</b></th>
				</tr>
				<tr>
					<td style="width:20%;">호텔명</td>
					<td style="width:80%;"><%=reserveMap.get("HOTEL_NAME") %></td>
				</tr>
				<tr>
					<td style="width:20%;">투숙기간</td>
					<td style="width:80%;"><%=reserveMap.get("HOTEL_SDATE") %> ~ <%=reserveMap.get("HOTEL_EDATE") %></td>
				</tr>
				<tr>
					<td style="width:20%;">호텔 주소</td>
					<td style="width:80%;"><%=reserveMap.get("HOTEL_ADDRESS") %></td>
				</tr>
			</table>
			<br>
			
			<table class="table table-bordered">
				<tr>
					<td colspan="2"><b>요금정보</b></td>
				</tr>
				<tr>
					<td style="width:20%;">결제 금액</td>
					<td style="width:80%;"><%=reserveMap.get("TOTAL_PRICE") %></td>
				</tr>
			</table>
			
			<table class="table table-bordered">
				<tr>
					<td colspan="4"><b>예약자 정보</b></td>
				</tr>
				<tr>
					<td style="width:15%">예약자명</td>
					<td style="width:35%"><%=reserveMap.get("HOTEL_RNAME") %></td>
					<td style="width:15%">휴대폰번호</td>
					<td style="width:35%"><%=reserveMap.get("HOTEL_RHP1") %></td>
				</tr>
				<tr>
					<td style="width:15%">이메일</td>
					<td style="width:35%"><%=reserveMap.get("HOTEL_REMAIL") %></td>
					<td style="width:15%">비상연락처</td>
					<td style="width:35%"><%=reserveMap.get("HOTEL_RHP2") %></td>
				</tr>
			</table>
			<%} %>
			<br>
			
			<table class="table table-bordered">
				<tr>
					<td colspan="2"><b>결제 상태</b></td>
				</tr>
				<tr>
					<td style="width:20%;">결제 상태</td>
					<!-- 고정값 -->
					<td style="width:80%;"><font color="blue">결제 완료</font></td>
				</tr>
			</table>
			<br>
			
			<!-- ---------- 버튼 처리 시작 ---------- -->
			<p align="center">
				<a href="./main.jsp"><button class="btn btn-large btn-default btn-block" type="button" style="font-size:large;">메인 화면</button></a>
			</p>
			<!-- ---------- 버튼 처리 끝 ---------- -->
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