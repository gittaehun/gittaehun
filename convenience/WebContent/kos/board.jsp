<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import = "java.util.Map, java.util.HashMap, java.util.List, java.util.ArrayList, kos.util.*" %>
<%
	int size = 0;
	List<Map<String,Object>> boardList = new ArrayList<Map<String,Object>>();
	if(request.getAttribute("boardList")!=null){
		
	boardList = (List<Map<String,Object>>)request.getAttribute("boardList");
	size = boardList.size();//전체 레코드 갯수	
	}
	//out.print("boardList.size() : "+boardList.size());\
   Map<String,Object> mem_info= new HashMap<String,Object>();
   if(session.getAttribute("result")!=null){
      mem_info = (Map<String,Object>)session.getAttribute("result");
   }
 
	int pagecount = 0;//총 페이지 수
	int pagesize = 5;//한페이지에 뿌려줄 글 갯수
	int mypage = 0;//현재 바라보고 있는 페이지
	
	if(request.getParameter("page") != null){
		mypage = Integer.parseInt(request.getParameter("page"));
	}
	String imagePath = "../images/";
%>	
	
<html>
<head>
<title>언타이틀</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="../css/BoardCss/bootstrap.min.css" />
<link rel="stylesheet" href="../css/BoardCss/board.css" />
<link rel="stylesheet" href="../css/BoardCss/main.css" />
<link rel="stylesheet" href="../css/css/font-awesome.css" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css"
	rel='stylesheet' type='text/css'>

<!-- Scripts -->
<script src="../css/BoardCss/js/jquery.min.js"></script>
<script src="../css/BoardCss/js/board.js"></script>
<script type="text/javascript" src="../css/BoardCss/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../css/BoardCss/js/bootstrap.js"></script>
<script src="../css/BoardCss/js/skel.min.js"></script>
<script src="../css/BoardCss/js/util.js"></script>
<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
<script src="../css/BoardCss/js/main.js"></script>
<script type="text/javascript">
	function boardView(val){
		location.href="./boardView.kos?b_no="+val
	}
</script>
</head>
<body>
	

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
			<!-- 메뉴바끝 -->



		</div>

		<!-- 메인화면시작 -->

		<div id="main">
			<!-- 첫번째 게시판 -->
			<div id="i_air" class="post">
				<header>
					<div class="title">
						<h2>
							<a href="#">자유게시판</a>
						</h2>
						<p>자유롭게 이야기하는 게시판</p>
					</div>
					<img src="../images/main4.jpg" alt=""
						style="width: 50%; box-shadow: 10px 10px 20px 5px grey" />
				</header>

				<!-- 글작성 -->
				<table class="table table-striped"
					style="max-width: 100%; height: 50%; margin: auto">
					<tbody>
						<form method="post" encType="multiplart/form-data">
							<thead>
								<tr>
									<th style="width: 5%; text-align: center"><label
										style="margin: auto">No</label></th>
									<th style="width: 50%; text-align: center"><label
										style="margin: auto">제목</label></th>
									<th style="width: 10%; text-align: center"><label
										style="margin: auto">작성자</label></th>
									<th style="width: 25%; text-align: center"><label
										style="margin: auto">작성일</label></th>
									<th style="width: 10%; text-align: center"><label
										style="margin: auto">조회수</label></th>
								</tr>
							</thead>
							<tbody>
					<!-- 반복 생성 할 <tr>  -->
					<% if(boardList.size()>0){
						//int num = size-(pagesize*((mypage+1)-1));	 
						for(int i=mypage*pagesize;i<(mypage*pagesize)+pagesize;i++){
							if(i == size) break;
					%>
								<tr style="height: 100%">
						            <td style="text-align: center">
						            <label style="margin: auto">
						           	 	<%=boardList.get(i).get("BOARD_NO") %>
						            </label>
						            </td>
									<td>
									<a href="#" onClick="boardView(<%=boardList.get(i).get("BOARD_NO")%>)" style="text-decoration: none;">
										<b><%=boardList.get(i).get("BOARD_TITLE") %></b>
									</a>
									</td>
									<td style="text-align: center">
									<label style="margin: auto">
										<%=boardList.get(i).get("USER_ID") %>
									</label>
									</td>
									<td style="text-align: center">
										<label style="margin: auto">
										<%=boardList.get(i).get("BOARD_DATE") %>
										</label></td>
									<td style="text-align: center">
									<label style="margin: auto">
										<%=boardList.get(i).get("BOARD_HIT") %>
									</label></td>
									<td></td>
								</tr>
								<% 
							}		
						}else{ 
						%>
								<tr style="height: 100%">
						            <td colspan="5" style="text-align: center">
						            	<h1>등록된 글이 없습니다.</h1>
						            </td>
								</tr>
						
						<%} %>
					<!-- 반복 생성 할 <tr>  -->
							</tbody>
						</form>
					</tbody>
				</table>
				<!-- 글작성 끝 -->
				<!-- <footer style="margin: 10 0px 0px 0px">
					<ul class="pagination pagination-large" style="">
						<li><a href="#" rel="prev">&laquo;</a></li>
						<li class="active"><span>1</span></li>
						<li><a href="http://bootsnipp.com/search?q=table&page=1">2</a></li>
						<li><a href="#" rel="next">&raquo;</a></li>
					</ul> -->
						
		<!----------------------------- 페이징 ---------------------------->
		<table width=700 BGCOLOR=000000 border=0 cellpadding=0 cellspacing=0 align="center">
		<tr  bgcolor="#7baad6" height=5>
		    <td colspan=5 align="center" valign="bottom">
		      <img src="<%=imagePath%>j_b04.gif" border="0" align="left" hspace="0" vspace="0">
		      <img src="<%=imagePath%>j_b05.gif" border="0" align="right" hspace="0" vspace="0">	      
		    </td>
		</tr>
		<tr BGCOLOR=FFFFFF>
			<td width=10>&nbsp;</td>
			<td width=180 height=30 valign=middle align=right>
			  <a href="#"><img src="<%=imagePath%>m_bt10.gif" border=0 align=absmiddle></a>
			</td>
			<td width=520  align=center valign=middle height=30>
<%
	String pagePath = "./getBoardList.kos";
	PageBar pg = new PageBar(pagesize, size, mypage, pagePath);
	out.print(pg.getPageBar());
%>	
			</td>
			<td width=10>&nbsp;</td>
			<td align="right">
					<%if(session.getAttribute("result") != null){ %>
					<a href="write.jsp?command=insert" style="margin-left: auto"><input
						type="button" class="btn" value="글쓰기" /></a>
					<%} %>
			</td>
		</tr>
		</table> 
				<!-- </footer> -->
			</div>
			<!-- 게시판끝 -->
		</div>
		<!-- main 끝  -->


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
		<!-- 사이드끝 -->
	</div>
	<!-- wrapper 끝 -->
</body>
</html>