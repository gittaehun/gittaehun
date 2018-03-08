<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<html>
<head>
<title>언타이틀</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="../css/CartCss/bootstrap.min.css" />
<link rel="stylesheet" href="../css/CartCss/main.css" />
<link rel="stylesheet" href="../css/CartCss/side.css" />
<link rel="stylesheet" href="../css/css/font-awesome.css" />
<link rel="stylesheet" href="../css/css/font-awesome.min.css" />
<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->


<!-- Scripts -->
<script src="../css/CartCss/js/jquery.min.js"></script>
<script type="text/javascript" src="../css/CartCss/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../css/CartCss/js/bootstrap.js"></script>
<script src="../css/CartCss/js/skel.min.js"></script>
<script src="../css/CartCss/js/util.js"></script>
<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
<script src="../css/CartCss/js/main.js"></script>
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
					<li><a href="main.jsp">HOME</a></li>
					<li><a href="air.jsp">AIRPLANE</a></li>
					<li><a href="ticket.jsp">E-TICKET</a></li>
					<li><a href="hotel.jsp">HOTEL</a></li>
					<li><a href="board.jsp">BOARD</a></li>
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

			<!-- 장바구니 메인 -->
			<div id="i_air" class="post">
				<header>
					<div class="title">
						<h2>
							<a href="#">장바구니</a>
						</h2>
						<p>담아놓은 물품들</p>
					</div>
					<img src="../images/main4.jpg" alt="" style="box-shadow:10px 10px 20px 5px grey"/>
				</header>
				
				<!-- 카트시작 -->
				<div class="row">
					<div class="col-sm-12 col-md-10 col-md-offset-1">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>물품명</th>
									<th>갯수</th>
									<th class="text-center">개당가격</th>
									<th class="text-center">금액</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="col-sm-8 col-md-6">
										<div class="media">
											<a class="thumbnail pull-left" href="#" style="margin-top:18px"> <img
												class="media-object"
												src="http://icons.iconarchive.com/icons/custom-icon-design/flatastic-2/72/product-icon.png"
												style="width: auto; height: auto; ">
											</a>
											<div class="media-body" style="text-align:center; padding-top:25px">
												<h5 class="media-heading">
													<a href="#">브랜드이름</a>
												</h5>
												<span>Status: </span><span class="text-success"><strong>In
														Stock</strong></span>
											</div>
										</div>
									</td>
									<td class="col-sm-1 col-md-1" style="vertical-align:middle">
										<input type="text" class="form-control"
										id="value" value="3">
									</td>
									<td class="col-sm-1 col-md-1 text-center" style="padding-top:55px"><strong>$4.87</strong></td>
									<td class="col-sm-1 col-md-1 text-center" style="padding-top:55px"><strong>$14.61</strong></td>
									<td class="col-sm-1 col-md-1">
										<a href="#"><input type="button" class="btn btn-default active" value="수정" style="margin-top:12px"></a>
										<a href="#"><input type="button" class="btn btn-danger active" value="삭제" style="margin-top:5px"></a>
									</td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td><h3>총합</h3></td>
									<td class="text-right"><h3>
											<strong>$31.53</strong>
										</h3></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td>
										<a href="#"><input type="button" class="btn btn-default active" value="쇼핑계속하기"></a>
									</td>
									<td>
										<a href="cash.jsp"><input type="button" class="btn btn-success active" value="결제하기"></a>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<!-- 카트 끝 -->
				
				<br>
			</div>
			
			<footer>
				<!-- Footer -->
				<div id="footer" style="text-align:center">
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

			</footer>
		</div>




	</div>




</body>
</html>