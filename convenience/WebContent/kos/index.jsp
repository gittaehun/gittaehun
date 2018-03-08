<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "java.util.Map, java.util.HashMap" %>
<%
   Map<String,Object> mem_info= new HashMap<String,Object>();
   if(session.getAttribute("result")!=null){
      mem_info = (Map<String,Object>)session.getAttribute("result");
   }
   String target = request.getParameter("target");
   if(target == null){
	   target="main";
   }
%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <title>땡처리 메인</title>
      <meta charset="utf-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1" />
      <!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
      <link rel="stylesheet" href="../css/css/bootstrap.css" />
      <link rel="stylesheet" href="../css/css/bootstrap.min.css" />
      <link rel="stylesheet" href="../css/css/main.css" />
      <link rel="stylesheet" href="../css/css/font-awesome.min.css" />
      <link rel="stylesheet" href="../css/css/font-awesome.css" />
      
   </head>
   <body>
      <!-- Wrapper -->
         <div id="wrapper">

            <!-- Header -->
               <header id="header">
                  <h1><a href="./main.jsp">convenience</a></h1>
                  <div class="links">
                     <ul>
                        <li><a href="main.jsp">HOME</a></li>
                        <li><a href="#i_air" >AIRPLANE</a></li>
                        <li><a href="#i_ticket">CAR RENTAL</a></li>
                        <li><a href="#i_hotel">HOTEL</a></li>
                        <li><a href="#i_board">BOARD</a></li>
                     </ul>
                  </div>
                  
                  <!-- 검색 -->
                  <div class="main">
                     <ul>
                        <li class="search">
                           <a class="fa-search" href="#search">검색</a>
                           <form id="search" method="get" action="#">
                              <input type="text" name="query" placeholder="검색" style="margin-top:10px"/>
                           </form>
                        </li>
                        <li class="menu">
                           <a class="fa-bars" href="#menu">메뉴</a>
                        </li>
                     </ul>
                  </div>
                  <!-- 검색끝 -->
                  
               </header>

            <!-- 메뉴안 -->
               <div id="menu">

                  <!-- 회원로그인 -->
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
                           <li><a href="./reserveSearch.kos">
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

                  <!-- Actions -->

               </div>

            <!-- Main -->
            <%if("main".equals(target)){ %>
               <div id="main">
				 <jsp:include page="./main.jsp" flush="false"/>
               </div>
			<%} %>
            <!-- 사이드쪽 메뉴 -->
               <div id="sidebar">

                  <!-- 사이트 로고 -->
                     <div id="intro">
                        <a href="#" class="logo"><img src="../images/logo.jpg" alt="" /></a>
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

               </div><!-- 사이드바 라인 끝 -->

         </div><!-- warpper 끝 -->
      
      
      <!-- 스크립트 -->
         <script src="../css/js/jquery.min.js"></script>
         <script src="../css/js/skel.min.js"></script>
         <script src="../css/js/util.js"></script>
         <!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
         <script src="../css/js/main.js"></script>
         <script src="../css/js/smoothscroll.js"></script>

   </body>
</html>
