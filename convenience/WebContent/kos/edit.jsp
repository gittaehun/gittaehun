<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.Map, java.util.HashMap" %>	
<%
	Map<String,Object> mem_info= new HashMap<String,Object>();
	if(session.getAttribute("result")!=null){
		mem_info = (Map<String,Object>)session.getAttribute("result");
	}
%>	
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>언타이틀</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="../css/editCss/edit.css" />
<link rel="stylesheet" href="../css/editCss/bootstrap.css" />
<link rel="stylesheet" href="../css/editCss/bootstrap.min.css" />
<link rel="stylesheet" href="../css/editCss/style.css" />
<link rel="stylesheet" href="../css/editCss/main.css" />
<link rel="stylesheet" href="../css/css/font-awesome.min.css" />
<link rel="stylesheet" href="../css/css/font-awesome.css" />
<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->

<!-- Scripts -->
<script src="../css/editCss/js/jquery.min.js"></script>
<script type="text/javascript" src="../css/editCss/js/bootstrap.min.js"></script>
<script src="../css/editCss/js/skel.min.js"></script>
<script src="../css/editCss/js/util.js"></script>
<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
<script src="../css/editCss/js/main.js"></script>
<script type="text/javascript">
	function memUpdate(){
 		$("#f_info").attr("method","post");
		$("#f_info").attr("action","./memUpdate.kos");
		$("#f_info").submit();
	}
	function pwCheck(){
		if($("#pw").val() == "" || $("#pw1").val()==""){
			$("#pw_check").modal();
		}
		else{
			$("#update_alert").modal();
		}
	}
/* 	$("#update_alert").modal('show').css({
			'margin-top' : function(){
				return ($(this).height()/2);
			},	
			'margin-left' : function(){
				return ($(this).width()/2);
			}
	}); */
</script>
</head>
<body>

<!-- alert창 -->
<div class="modal" id="update_alert" tabindex="-1" role="dialog" style="padding:10% 40%">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-body" align="center">
				<h1>재로그인이 필요합니다.</h1>&nbsp;
				<button type="button" class="btn" data-dismiss="modal" onCLick="memUpdate()">확인</button>
			</div>
		</div>
	</div>
</div>
<div class="modal" id="pw_check" tabindex="-1" role="dialog" style="padding:10% 40%">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-body" align="center">
				<h1>비밀번호를 입력해주세요</h1>&nbsp;
				<button type="button" class="btn" data-dismiss="modal">확인</button>
			</div>
		</div>
	</div>
</div>
<!-- alert창 -->


<script type="text/javascript">
	$(document).ready(function(){
		var u_id = "<%=mem_info.get("USER_ID")%>";
		var u_name = "<%=mem_info.get("USER_NAME")%>";
		var u_nickname = "<%=mem_info.get("USER_NICK")%>";
		var u_hp = "<%=mem_info.get("USER_HP")%>";
		var u_address = "<%=mem_info.get("USER_ADDRESS")%>";
		var u_gender = "<%=mem_info.get("USER_GENDER")%>";
		
		$("#id").attr('value',u_id);
		$("#name").attr('value',u_name);
		$("#nickname").attr('value',u_nickname);
		$("#hp").attr('value',u_hp);
		$("#address").attr('value',u_address);
		
		if("남" == u_gender){
			$("#gender").val(2);
		}
		else{
			$("#gender").val(3);
		}
		
	});
</script>
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
		<!-- 검색끝 --> </header>

		<!-- 메뉴안 -->
		<div id="menu">
	<%
		if(session.getAttribute("result")!=null){
	%>
			<!-- 회원로그아웃 -->
			<div>
				<ul class="actions vertical">
					<li><a href="./logout.kos" class="button big fit">Log Out</a></li>
				</ul>
			</div>
			<!-- 회원로그아웃끝 -->
	<%
		} else{
	%>
			<!-- 회원로그아웃 -->
			<div>
				<ul class="actions vertical">
					<li><a href="./login.jsp" class="button big fit">Log In</a></li>
				</ul>
			</div>
			<!-- 회원로그아웃끝 -->
	<%
		}
	%>
	
			<!-- Links -->
			<div>
				<ul class="links">
					<li><a href="edit.jsp">
							<h3>회원 정보</h3>
							<p>수정 & 탈퇴</p>
					</a></li>
	<%
		if(session.getAttribute("result")!=null){
	%>			
					<li><a href="#">
							<h3>주문 현황</h3>
							<p>주문 내역</p>
					</a></li>
	<% } else { %>
					<li><a href="#">
							<h3>회원가입</h3>
					</a></li>
	<% } %>
					<li><a href="cart.jsp">
							<h3>장바구니</h3>
							<p>찜해놓은 물건</p>
					</a></li>
				</ul>
			</div>

			<!-- Actions -->


		</div>

		<!-- Main -->
		<div id="main">
			<div id="i_edit" class="post">
				<header>
				<div class="title">
					<h2>
						<a href="#">회원정보</a>
					</h2>
					<p>회원정보에 관한 수정</p>
				</div>
				</header>

				<!-- 회원정보 수정라인 -->
				<footer>
				<form class="form-horizontal" id="f_info" name="f_info">
					<fieldset>
						<!-- 아이디 -->
						<input type="hidden" id="id" name="n_id">

						<!-- 이름-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="id">이름</label>
							<div class="col-md-4">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="fa fa-user fa" aria-hidden="true"></i></span> <input id="name"
										name="n_name" type="text" placeholder="이름" class="form-control"
										required="">
								</div>
							</div>
						</div>
						<!-- 비밀번호 -->
						<div class="form-group">
							<label class="col-md-4 control-label" for="pw">비밀번호</label>
							<div class="col-md-4">
								<div class="input-group">
									<span class="input-group-addon"> <i class="fa fa-key fa"
										aria-hidden="true"></i></span> <input id="pw" name="n_pw"
										type="password" placeholder="비밀번호" class="form-control"
										required>
								</div>
							</div>
						</div>
						<!-- 비밀번호 확인-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="pw1">비밀번호 확인</label>
							<div class="col-md-4">
								<div class="input-group">
									<span class="input-group-addon"> <i class="fa fa-key fa"
										aria-hidden="true"></i></span> <input id="pw1" name="pw1"
										type="password" placeholder="비밀번호 확인" class="form-control"
										required>
								</div>
							</div>
						</div>
						<!-- 닉네임-->
						<div class="form-group">
							<label class="col-md-4 control-label" for="nickname">닉네임</label>
							<div class="col-md-4">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="fa fa-comment fa" aria-hidden="true"></i></span> <input
										id="nickname" name="n_nickname" type="text" placeholder="닉네임"
										class="form-control" required="">
								</div>
							</div>
						</div>
						<!-- 핸드폰 -->
						<div class="form-group">
							<label class="col-md-4 control-label" for="hp">핸드폰</label>
							<div class="col-md-4">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="fa fa-phone fa" aria-hidden="true"></i></span> <input id="hp"
										name="n_hp" type="text" placeholder="핸드폰" class="form-control"
										required="">
								</div>
							</div>
						</div>
						<!-- 성별 -->
						<div class="form-group">
							<label class="col-md-4 control-label" for="gender">성별</label>
							<div class="col-md-2"  style="width:20%">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="fa fa-group fa-lg" aria-hidden="true"></i></span> <select
										id="gender" name="n_gender" class="form-control">
										<option value="1">- 선택 -</option>
										<option value="2">남자</option>
										<option value="3">여자</option>
									</select>
								</div>
							</div>
						</div>
						<!-- 주소 -->
						<div class="form-group">
							<label class="col-md-4 control-label" for="address">주소</label>
							<div class="col-md-4">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="fa fa-home fa" aria-hidden="true"></i></span> <input
										id="address" name="n_address" type="text" placeholder="주소"
										class="form-control" required="">
								</div>
							</div>
						</div>
						<!-- 버튼 -->
						<div class="form-group">
							<label class="col-md-4"></label>
							<div class="col-md-6">
							<a href="#" data-toggle="modal" onClick="pwCheck()">
								<input type="button" class="btn btn-primary" value="수정">
							</a>
								 <a href="main.jsp"><input type="button"
									class="btn btn-danger" value="취소"></a>
							</div>
						</div>
						<!-- 버튼끝 -->

					</fieldset>
				</form>
				</footer>
			</div>


		</div>

		<!-- 사이드쪽 메뉴 -->
		<div id="sidebar">
			<!-- Intro -->
			<div id="intro">
				<a href="main.jsp" class="logo"><img src="../images/logo.jpg"
					alt="" /></a>
				<header>
				 <h2>convenience</h2>
                 <p>땡처리 사이트</p>
				</header>
			</div>

			<!-- Mini Posts -->
			<div>
				<div class="mini-posts">

					<!-- Mini Post -->
					<article class="mini-post"> <a
						href="https://samsungcreativesquare.com/Apply/apply_main/"
						class="image"><img src="../images/poster1.jpg" alt="" /></a> </article>
				</div>
			</div>


			<!-- About -->
			<div class="blurb">
				<h2>Final Project</h2>
				 <p> 김정현, 김태훈 </p>
                 <p> 이진만, 유태준</p>
                 <p> 박석영, 박혜웅</p>
			</div>

			<!-- Footer -->
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
		</div>
	</div>

</body>
</html>