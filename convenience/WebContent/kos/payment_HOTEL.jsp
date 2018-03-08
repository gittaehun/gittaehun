<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.Map, java.util.HashMap, java.util.List, java.util.ArrayList, kos.util.KoreanConversion, java.text.*" %>
<%
Map<String,Object> mem_info= new HashMap<String,Object>();
if(session.getAttribute("result")!=null){
	mem_info = (Map<String,Object>)session.getAttribute("result");
}
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
<script type="text/javascript">
	function doReservation(){
		var accept = $('input[name=radio1]:checked').val();
		alert(accept);
		if(accept=="ok"){
		var hotel_rname = $("#booker").val();
		var total_price="<%= NumberFormat.getInstance().format(Integer.parseInt(request.getParameter("total_price"))) %>";
		var hp1 = $("#hp1").val();
		var hp2 = $("#hp2").val();
		var hp3 = $("#hp3").val();
		var hp = hp1+"-"+hp2+"-"+hp3;
		var mail1=$("#mail1").val();
		var mail2=$("#mail2").val();
		var mail = mail1+"@"+mail2;
		var phone1 = $("#phone1").val();
		var phone2 = $("#phone2").val();
		var phone3 = $("#phone3").val();
		var ephone = phone1+"-"+phone2+"-"+phone3;
		var user_id = "<%=mem_info.get("USER_ID") %>";
		var hotel_pno = <%=request.getParameter("hotel_pno") %>;
		//alert(hotel_rname+", "+total_price+", "+hp+", "+mail+", "+ephone+", "+user_id+", "+hotel_pno);
		location.href="./reservationIns.kos?hotel_rname="+hotel_rname+"&total_price="+total_price+"&hp="+hp+"&mail="+mail+"&ephone="+ephone+"&user_id="+user_id+"&hotel_pno="+hotel_pno;
		}else{
			alert("약관에 동의 해주십시오.");
		}
	}
	
	function acceptChoice(){
		
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
			<table class="table table-bordered">
				<tr>
					<th colspan="2"><b>객실 정보</b></th>
				</tr>
				<tr>
					<td style="width:20%;">호텔명</td>
					<td style="width:80%;"><%=request.getParameter("hotel_name") %></td>
				</tr>
				<tr>
					<td style="width:20%;">예약상태</td>
					<td style="width:80%;">대기예약</td>
				</tr>
				<tr>
					<td style="width:20%;">투숙기간</td>
					<td style="width:80%;"><%=KoreanConversion.toKor(request.getParameter("hotel_sdate")) %> ~ <%=KoreanConversion.toKor(request.getParameter("hotel_edate")) %></td>
				</tr>
			</table>
			<br>
			
			<table class="table table-bordered">
				<tr>
					<td colspan="2"><b>요금정보</b></td>
				</tr>
				<tr>
					<td style="width:20%;">객실요금</td>
					<td style="width:80%;"><%=KoreanConversion.toKor(request.getParameter("hotel_price")) %>원</td>
				</tr>
				<tr>
					<td style="width:20%;">요금합계</td>
					<td style="width:80%;"><%= NumberFormat.getInstance().format(Integer.parseInt(request.getParameter("total_price"))) %>원</td>
				</tr>
			</table>
			
			<table class="table table-bordered">
				<tr>
					<td colspan="4"><b>예약자 정보</b></td>
				</tr>
				<tr>
					<td style="width:15%">예약자명</td>
					<td style="width:35%">
						<input type="text" id="booker" name="booker" style="width:200px;height:30px">
					</td>
					<td style="width:15%">휴대폰번호</td>
					<td style="width:35%">
						<div class="row">
						  <div class="col-xs-4">
						    <select class="form-control" id="hp1" style="width:80px">
									<option>010</option>
									<option>011</option>
									<option>016</option>
									<option>017</option>
									<option>019</option>
							</select>
						  </div>
						  <div class="col-xs-3">
						    <input type="text" id="hp2" class="form-control">
						  </div>
						  <div class="col-xs-3">
						    <input type="text" id="hp3" class="form-control">
						  </div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="width:15%">이메일</td>
					<td style="width:35%">
						<div class="row">
						  <div class="col-xs-5">
						    <input type="text" id="mail1" class="form-control">
						  </div>
						  <div class="col-xs-1">
						  	@
						  </div>
						  <div class="col-xs-5">
						    <input type="text" id="mail2" class="form-control">
						  </div>
						</div>
					</td>
					<td style="width:15%">비상연락처</td>
					<td style="width:35%">
						<div class="row">
						  <div class="col-xs-3">
						    <input type="text" id="phone1" class="form-control">
						  </div>
						  <div class="col-xs-3">
						    <input type="text" id="phone2" class="form-control">
						  </div>
						  <div class="col-xs-3">
						    <input type="text" id="phone3" class="form-control">
						  </div>
						</div>
						</div>
					</td>
				</tr>
			</table>
			<!-- ---------- 유의 사항 시작 ---------- -->
			<table class="table table-bordered">
				<tr>
					<td><b>이용규정동의</b></td>
				</tr>
				<tr>
					<td align="center"><img src="../images/payment_HOTEL.jpg" class="img-responsive" alt="Responsive image"></td>
				</tr>
			</table>
					<div class="checks small" align="center">
						<input type="radio" id="radio1" name="radio1" checked="checked" value="ok">
						<label for="radio1">동의함</label>
						<input type="radio" id="radio2" name="radio1" value="no">
						<label for="radio2">동의하지 않음</label>
					</div>
			<!-- ---------- 유의 사항 끝 ---------- -->
			<br>
			<!-- ---------- 예약 & 취소 버튼 시작 ---------- -->
			<p align="center">
				<!-- <input type="button" class="btn btn-success active" value="예약하기" onclick="doReservation()"> -->
				<a href="#"><button class="btn btn-default btn-lg" type="button" onclick="doReservation()">예약 하기</button></a>
				<a href="main.jsp"><button class="btn btn-default btn-lg" type="button">취소 하기</button></a>
			</p>
			<!-- ---------- 예약 & 취소 버튼 시작 ---------- -->
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