<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.Map, java.util.HashMap" %>
<%
   Map<String,Object> mem_info= new HashMap<String,Object>();
   if(session.getAttribute("result")!=null){
      mem_info = (Map<String,Object>)session.getAttribute("result");
   }
 %>
<html>
<head>
<title>항공사 티켓 등록 페이지</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="../css/WriteCss/bootstrap.css" />
<link rel="stylesheet" href="../css/WriteCss/bootstrap.min.css" />
<link rel="stylesheet" href="../css/WriteCss/write.css" />
<link rel="stylesheet" href="../css/WriteCss/main.css" />
<link rel="stylesheet" href="../css/WriteCss/font-awesome.css" />

<!-- Scripts -->
<script src="../css/WriteCss/js/jquery.min.js"></script>
<script type="text/javascript" src="../css/WriteCss/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../css/WriteCss/js/bootstrap.js"></script>
<script src="../css/WriteCss/js/skel.min.js"></script>
<script src="../css/WriteCss/js/util.js"></script>
<script src="../css/WriteCss/js/main.js"></script>
<script src="../css/WriteCss/js/write.js"></script>
<script type="text/javascript">
	function air_Ins(){
		$("#airIns").attr("method","post");
		$("#airIns").attr("action","./airIns.kos");
		$("#airIns").submit();
	}
    function openAlert(){
    	$("#check_alert").modal('show');
    }
	function check(){
		 var air_name = $('#air_name').val();
		 var product_name = $('#product_name').val();
		 var product_quantity = $('#product_quantity').val();
		 var product_price = $('#product_price').val();
		 var air_accountholder = $('#air_accountholder').val();
		 var img_s_0 = $('#img_s_0').val();
		 var img_s_1 = $('#img_s_1').val();
		 var img_s_2 = $('#img_s_2').val();
		 var product_content = $('#product_content').val();
		 var air_city = $('#air_city').val();
		 var start_date = $('#start_date').val();
		 var end_date = $('#end_date').val();
		 var product_delay = $('#product_delay').val();
		 var start_point = $('#start_point').val();
		 var end_point = $('#end_point').val();
		 var air_time = $('#air_time').val();
		 var start_time = $('#start_time').val();
		 var end_time = $('#end_time').val();
		 
		 if(air_name=='-선택하세요-' ||  product_name =='' 
	     || product_quantity =='' || product_price=='' || product_delay ==''
		 || img_s_0 =='' || img_s_1 =='' || img_s_2==''
		 || product_content =='' || air_city =='' || start_date==''
		 || end_date =='' || start_point=='' || end_point ==''
		 || air_time =='' || start_time=='' || end_time==''){
			$("#al_msg").val('모두 입력해주세요.');
			openAlert();
			return;
		 }
		 else{
			 air_Ins();
		 }
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

	<!-- 리모트컨트롤 -->

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
		  <%
		     if(session.getAttribute("result")!=null){
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
		</div>

		<!-- 메인화면시작 -->
		<div id="main">

			<!-- 첫번째 게시판 -->
			<div id="i_air" class="post">
				<header>
					<div class="title">
						<h2>
							<a href="#">글 작 성</a>
						</h2>
					</div>
				</header>
	<!---------------------- 글작성 시작 -------------------- -->		
	 		<table class="table table-striped" style="max-width: 100%; margin: auto">
				<tbody>
					<form role="form" id="airIns" method="post" enctype="multipart/form-data" >
							<tr>
								<th style="text-align: center; vertical-align: middle">항공사</th>
								<td>
							    <select id="air_name" name="air_name" class="form-control" style="font-size:large;width:300;height:50">
									<option>-선택하세요-</option>
									<option>가자미</option>
									<option>코스모</option>
									<option>자바</option>
									<option>오라클</option>
									<option>와우</option>
								</select>
								</td>
							</tr>
							<tr>
								<th style="text-align: center; vertical-align: middle">상품 이름</th>
								<td>
								<input type="text" placeholder="상품 이름 을 입력하세요."
									id="product_name" name="product_name" /></td>
							</tr>
							<tr>
								<th style="text-align: center; vertical-align: middle">상품 수량</th>
								<td><input type="number" id="product_quantity" name="product_quantity" style="width:150px" min="1">매</td>
							</tr>
							<tr>
								<th style="text-align: center; vertical-align: middle">상품 가격</th>
								<td><input type="number" id="product_price" name="product_price" style="width:150px" min="1" >원(1매)</td>
							</tr>
							<tr>
								<th style="text-align: center; vertical-align: middle">메인 상품 이미지</th>
								<td><input type="file" id="img_s_0" name="img_s_0"  ></td>
							</tr>
							<tr>
								<th style="text-align: center; vertical-align: middle">상세 상품 이미지</th>
								<td>
									<input type="file" id="img_s_1" name="img_s_1" />
									<br>
									<input type="file" id="img_s_2" name="img_s_2" />
								</td>
							</tr>
							<tr>
								<th style="text-align: center; vertical-align: middle">상품 내용</th>
								<td>
									<textarea cols="10" rows="10" placeholder="상품 내용을 입력하세요."
									id="product_content" name="product_content" style="resize:none;font-size:x-large;"></textarea>
								</td>
							</tr>
						 	<tr>
								<th style="text-align: center; vertical-align: middle">여행 도시</th>
								<td><input type="text" placeholder="도시명 입력." id="air_city" name="air_city" style="width:150px" ></td>
							</tr>
						 	<tr>
								<th style="text-align: center; vertical-align: middle">여행 날짜</th>
								<td>
								<input type="date" placeholder="시작날짜1" id="start_date" name="start_date" style="width:140px" >
								　	~　 
								<input type="date" placeholder="시작날짜2" id="end_date" name="end_date" style="width:140px"  >
								</td>
							</tr>
							<tr>
								<th style="text-align: center; vertical-align: middle">여행 기간</th>
								<td><input type="number" placeholder="여행기간 입력"id="product_delay" name="product_delay" style="width:150px" min="1" max="20"  > 일</td>
							</tr>
							<tr>
								<th style="text-align: center; vertical-align: middle">적용 구간</th>
								<td>
								<input type="text" placeholder="출발지." id="start_point" name="start_point" style="width:140px" >
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;~
								<input type="text" placeholder="도착지." id="end_point" name="end_point" style="width:140px" >
								</td>
							</tr>
							<tr>
								<th style="text-align: center; vertical-align: middle">여정1 :출발 / 여정 2 : 출발 시간</th>
								<td>
								<input type="time" placeholder="출발시간 입력" id="start_time" name="start_time" style="width:150px" >
								　~　
								<input type="time" placeholder="도착시간 입력" id="end_time" name="end_time" style="width:150px"  >
								</td>
							</tr>
							<tr>
								<th style="text-align: center; vertical-align: middle">소요 시간</th>
							<td>
								<input type="number" placeholder="소요시간 입력" id="air_time" name="air_time" style="width:150px" >
							</td>
							</tr>
						</form>
					</tbody>
				</table>
				<!-- -------------------- 글작성 끝 -------------------- -->
				<footer style="margin: 10 0px 0px 0px; outline: none">
					<a href="javascript:check()" style="margin-left: auto">
						<input type="button" value="등록" class="btn" />
					</a>
					<a href="air.jsp">
						<input type="button" value="목록 " class="btn" style="margin-left: 5px" />
					</a>
				</footer>
			</div>			
			<br>
		</div>
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
					<li><a href="https://twitter.com/" class="fa-twitter"
						target="_blank"><span class="label">Twitter</span></a></li>
					<li><a href="https://www.facebook.com/" class="fa-facebook"
						tar+="_blank"><span class="label">Facebook</span></a></li>
					<li><a href="https://www.instagram.com/" class="fa-instagram"
						target="_blank"><span class="label">Instagram</span></a></li>
					<li><a href="https://www.google.co.kr/" class="fa-google"
						target="_blank"><span class="label">Google</span></a></li>
				</ul>
				<p class="copyright">
					&copy; Copyright. <a href="http://www.ikosmo.co.kr/"
						target="_blank">KOSMO</a>.
				</p>
			</div>
			<!-- 푸터라인 끝 -->
		</div>
	</div>
</body>
</html>