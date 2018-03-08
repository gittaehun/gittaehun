<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import = "java.util.Map, java.util.HashMap" %>
<% Map<String,Object> mem_info= new HashMap<String,Object>();
   if(session.getAttribute("result")!=null){
      mem_info = (Map<String,Object>)session.getAttribute("result");
   }	
%>	
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

<script type="text/javascript">
	function boardIns(){
 		$("#f_boardIns").attr("method","post");
		$("#f_boardIns").attr("action","./boardIns.kos");
		$("#f_boardIns").submit();
/* 		alert($("#content").val());
		alert($("#subject").val());
		alert($("#content").val()); */
	}
	function boardUpdate(){
 		$("#f_boardUpd").attr("method","post");
		$("#f_boardUpd").attr("action","./boardUpdate.kos");
		$("#f_boardUpd").submit();
/* 		alert($("#content").val());
		alert($("#subject").val());
		alert($("#content").val()); */
	}
</script>

</head>
<body>
		<%if(mem_info.size()>0){ %>
	<script type="text/javascript">
		$(document).ready(function(){
			var u_id = "<%=mem_info.get("USER_ID")%>";
			$("#user_id").attr("value",u_id);
		});
	</script>
		<%}%>

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
		</div>

		<!-- 메인화면시작 -->
		<div id="main">

			<!-- 첫번째 게시판 -->
			<%if("insert".equals(request.getParameter("command"))){ %>
			<div id="i_air" class="post">
				<header>
					<div class="title">
						<h2>
							<a href="#">글 작성</a>
						</h2>
					</div>

				</header>
								<!-- 글작성 -->
				<table class="table table-striped"
					style="max-width: 100%; margin: auto">
					<tbody>
						<form id = "f_boardIns">
						<input type="hidden" id="hb_no" name="b_no" value="0">
						<input type="hidden" id="hb_group" name="b_group" value="0">
						<input type="hidden" id="hb_step" name="b_step" value="0">
						<input type="hidden" id="hb_hit" name="b_hit" value="0">
							<input type="hidden" id="user_id" name="user_id">
<!-- 							<tr>
								<th style="text-align: center; vertical-align: middle">카테고리</th>
								<td>
									<div class="row">
										<div class="col-md-2">
											<select id="mySelect" class="form-control">
												<option value="value1">- 선택 -</option>
												<option value="value2">자유게시판</option>
												<option value="value3">Q & A</option>
											</select>
										</div>
									</div>
								</td>
							</tr> -->
							<tr>
								<th style="text-align: center; vertical-align: middle">제목</th>
								<td><input type="text" placeholder="제목을 입력하세요. "
									id="subject" name="subject" class="form-control" /></td>
							</tr>
							<tr>
								<th style="text-align: center; vertical-align: middle">내용</th>
								<td><textarea cols="10" rows="15" placeholder="내용을 입력하세요. "
									 id="content" name="content" class="form-control" style="resize: none"></textarea></td>
							</tr>
							<tr>
								<th style="text-align: center; vertical-align: middle">비밀번호</th>
								<td><input type="password" placeholder="비밀번호를 입력하세요" name="board_pw"
									id="board_pw" class="form-control" /></td>
							</tr>
						</form>
					</tbody>
				</table>
				<!-- 글작성 끝 -->
				<footer style="margin: 10 0px 0px 0px; outline: none">
					<a href="#" style="margin-left: auto" onClick="boardIns()"><input type="button"
						value="등록" class="btn" /></a> <a href="./getBoardList.kos"><input type="button"
						value="목록 " class="btn" style="margin-left: 5px"></a>
				</footer>
			</div>
			<br>
		</div>
			<%}else if("update".equals(request.getParameter("command"))){ 
				String board_no = request.getParameter("board_no");
				String board_title = request.getParameter("board_title");
				String board_contents = request.getParameter("board_contents");
			%>
			<div id="i_air" class="post">
				<header>
					<div class="title">
						<h2>
							<a href="#">글 수정</a>
						</h2>
					</div>

				</header>
				<!-- 글작성 -->
				<table class="table table-striped"
					style="max-width: 100%; margin: auto">
					<tbody>
						<form id = "f_boardUpd">
						<input type="hidden" id="hb_no" name="b_no" value="<%=request.getParameter("board_no")%>">
							<input type="hidden" id="user_id" name="user_id">
<!-- 							<tr>
								<th style="text-align: center; vertical-align: middle">카테고리</th>
								<td>
									<div class="row">
										<div class="col-md-2">
											<select id="mySelect" class="form-control">
												<option value="value1">- 선택 -</option>
												<option value="value2">자유게시판</option>
												<option value="value3">Q & A</option>
											</select>
										</div>
									</div>
								</td>
							</tr> -->
							<tr>
								<th style="text-align: center; vertical-align: middle">제목</th>
								<td><input type="text" value="<%=board_title %>"
									id="subject" name="subject" class="form-control" /></td>
							</tr>
							<tr>
								<th style="text-align: center; vertical-align: middle">내용</th>
								<td><textarea cols="10" rows="15" 
									 id="content" name="content" class="form-control" style="resize: none"><%=board_contents %></textarea></td>
							</tr>
							<tr>
								<th style="text-align: center; vertical-align: middle">비밀번호</th>
								<td><input type="password" placeholder="비밀번호를 입력하세요" name="board_pw"
									id="board_pw" class="form-control" /></td>
							</tr>
						</form>
					</tbody>
				</table>
				<!-- 글작성 끝 -->
				<footer style="margin: 10 0px 0px 0px; outline: none">
					<a href="#" style="margin-left: auto" onClick="boardUpdate()"><input type="button"
						value="수정" class="btn" /></a> <a href="./getBoardList.kos"><input type="button"
						value="목록 " class="btn" style="margin-left: 5px"></a>
				</footer>
			</div>
			<br>
		</div>
			<%} %>
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