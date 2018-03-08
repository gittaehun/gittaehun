<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<html>
<head>
<title>언타이틀</title>
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
</head>
<body>

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
			<div>
				<ul class="actions vertical">
					<li><a href="login.jsp" class="button big fit">Log In</a></li>
				</ul>
			</div>
			<!-- 회원로그인끝 -->

			<!-- 메뉴바 아이템 -->
			<div>
				<ul class="links">
					<li><a href="edit.jsp">
							<h3>회원 정보</h3>
							<p>수정 & 탈퇴</p>
					</a></li>
					<li><a href="#">
							<h3>배송 현황</h3>
							<p>배송중인 물품 추적</p>
					</a></li>
					<li><a href="cart.jsp">
							<h3>장바구니</h3>
							<p>찜해놓은 물건</p>
					</a></li>
				</ul>
			</div>
			<!-- 메뉴바끝 -->
		</div>

		<!-- 메인화면시작 -->
		<div id="main">

			<!-- 첫번째 게시판 -->
			<div id="i_air" class="post">
				<header>
					<div class="title">
						<h2>
							<a href="#">주문 하기</a>
						</h2>
					</div>

				</header>

				<!-- 글작성 -->
				<table class="table table-striped"
					style="max-width: 100%; margin: auto">
					<tbody>
						<form method="post" encType="multiplart/form-data">
							<tr>
								<th style="text-align: center; vertical-align: middle">판매 제품</th>
								<td>
									<div class="row">
										<div class="col-md-2">
											<select id="product_select" name="product_select" class="form-control">
												<option value="value1">--선택--</option>
												<option value="value2">항공</option>
												<option value="value3">숙박</option>
												<option value="value4">렌트</option>
												<option value="value5">맛집</option>
												<option value="value6">패키지</option>
											</select>
										</div>
									</div>
								</td>
							</tr>
							
							<tr>
								<th style="text-align: center; vertical-align: middle">상품 이름</th>
								<td><input type="text" placeholder="상품 이름 을 입력하세요. "
									id="product_name" name="product_name" class="form-control" /></td>
							</tr>
							
							<tr>
								<th style="text-align: center; vertical-align: middle">상품 수량</th>
								<td><input type="number" id="product_quantity" name="product_quantity" style="width:150px">개</td>
							</tr>
							
							<tr>
								<th style="text-align: center; vertical-align: middle">상품 가격</th>
								<td><input type="number" id="product_price" name="product_price" style="width:150px">원(1개)</td>
							</tr>
							
						<!-- <tr>
								<th style="text-align: center; vertical-align: middle">메인 이미지</th>
								<td><input type="file" id="product_image" name="product_image"/></td>
							</tr> -->
							<tr>
								<th style="text-align: center; vertical-align: middle">이미지</th>
								<td>
<!-- 									<div class="btn-toolbar">
									</div>
 -->									 <a class="logo"><img src="../images/main1.jpg" alt="" style="width:300px;height:200px"/></a>
								</td>
							</tr>
							
							<tr>
								<th style="text-align: center; vertical-align: middle">상품 내용</th>
								<td>
									<textarea cols="10" rows="10" placeholder="내용을 입력하세요. "
									id="product_contents" name="product_contents" class="form-control" style="resize:none;font-size:x-large;"></textarea>
								</td>
							</tr>
							</form>
						</tbody>
				</table>

			
				<!-- 글작성 끝 -->
				<footer style="margin: 10 0px 0px 0px; outline: none">
					<a href="buy.jsp" style="margin-left: auto">
						<input type="button" value="주문" class="btn" />
					</a>
					<a href="board.jsp">
						<input type="button" value="취소 " class="btn" style="margin-left: 5px" />
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
						<a href="https://samsungcreativesquare.com/Apply/apply_main/"
							class="image"><img src="../images/poster1.jpg" alt="" /></a>
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
						target="_blank"><span class="label">Facebook</span></a></li>
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