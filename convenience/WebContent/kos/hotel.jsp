<%@ page language="java" contentType="text/html; charset=EUC-KR"
   pageEncoding="EUC-KR"%>
<%@ page import="java.util.Map, java.util.HashMap, java.util.List, java.util.ArrayList" %>
<%@ page import="java.io.*,java.net.*, kos.util.KoreanConversion, kos.util.*" %>
<%
int size = 0;
Map<String,Object> mem_info= new HashMap<String,Object>();
if(session.getAttribute("result")!=null){
   mem_info = (Map<String,Object>)session.getAttribute("result");
}
List<Map<String, Object>> hotelList = new ArrayList<Map<String, Object>>();
if(request.getAttribute("hotelList")!=null){
hotelList = (List<Map<String, Object>>) request.getAttribute("hotelList");
size = hotelList.size();
}
int pagecount = 0;//총 페이지 수
int pagesize = 2;//한페이지에 뿌려줄 글 갯수
int mypage = 0;//현재 바라보고 있는 페이지
if(request.getParameter("page") != null){
   mypage = Integer.parseInt(request.getParameter("page"));
}
String imagePath = "../images/";
%>
<html>
<head>
<title>호텔 리스트</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="../css/HotelCss/bootstrap.min.css" />
<link rel="stylesheet" href="../css/HotelCss/main.css" />
<link rel="stylesheet" href="../css/HotelCss/air.css" />
<link rel="stylesheet" href="../css/css/font-awesome.css" />
<link rel="stylesheet" href="../css/css/font-awesome.min.css" />
<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->


<!-- Scripts -->
<script src="../css/HotelCss/js/jquery.min.js"></script>
<script type="text/javascript" src="../css/HotelCss/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../css/HotelCss/js/bootstrap.js"></script>
<script src="../css/HotelCss/js/skel.min.js"></script>
<script src="../css/HotelCss/js/util.js"></script>
<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
<script src="../css/HotelCss/js/main.js"></script>
<script src="../css/HotelCss/js/air.js"></script>
<script type="text/javascript">
   function buyHotel(hotel_no){
      location.href="getHotelList.kos?cmd=buy&hotel_no="+hotel_no;
   }
   function hotelIDCheck(){
      var id="<%=mem_info.get("USER_ID") %>";      
      location.href="./hotelIDCheck.kos?user_id="+id;
   }
</script>
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
         <div id="i_hotel" class="post">
            <header>
               <div class="title">
                  <h2>
                     <a href="#">HOTEL</a>
                  </h2>
                  <p>저렴한 숙박을 위하여</p>
                  <p>Have a good shopping.</p>
               </div>
               <img src="../images/main3.jpg" alt="" style="width:50%; box-shadow:10px 10px 20px 5px grey"/>
            </header>
            
            <!-- 글작성 -->
            <table class="table table-striped" style="max-width:100%; margin:auto">
                     <tbody>
                        
                        <%
                        if(hotelList.size()>0){
                           for(int i=mypage*pagesize;i<(mypage*pagesize)+pagesize;i++){
                              if(i == size) break;
                           Map<String, Object> hotelMap = new HashMap<String, Object>();
                           hotelMap = hotelList.get(i);                           
                        %>
                        
                        <!-- 게시물 -->
                        <tr>
                           <td>
                              <div class="media">
                                 <a class="thumbnail pull-left" href="#" onclick="buyHotel(<%=hotelMap.get("HOTEL_PNO") %>)" style="margin:0 auto; border:0"> <img
                                    class="media-object"
                                    src="../pds/<%=hotelMap.get("IMG1") %>"
                                    style="width: 180px; height: 180px; ">
                                 </a>
                                 <div class="media-body" style="text-align:left; vertical-align:middle">
                                 <table class="table table-condensed">
                                    <tr height="20px">
                                       <td width="150px">호텔명</td>
                                       <td><a href="#" onclick="buyHotel(<%=hotelMap.get("HOTEL_PNO") %>)" style="text-decoration:none"><%=hotelMap.get("HOTEL_NAME") %></a></td>
                                    </tr>
                                    <tr>
                                       <td width="150px">가격</td>
                                       <td><%=hotelMap.get("HOTEL_PRICE") %>원</td>
                                    </tr>
                                    <tr>
                                       <td width="150px">판매자</td>
                                       <td><%=hotelMap.get("USER_ID") %></td>
                                    </tr>
                                    <tr>
                                       <td width="150px">지역</td>
                                       <td><%=hotelMap.get("HOTEL_ADDRESS") %></td>
                                    </tr>
                                    <tr>
                                       <td width="150px">조회수</td>
                                       <td><%=hotelMap.get("HOTEL_HIT") %></td>
                                    </tr>
                                 </table>

                                 </div>
                              </div>
                           </td>
                        </tr>
                        
                        <!-- 게시물끝 -->
                        <%
                           }
                        }else{ %>
                        <tr>
                           <td>
                              <div class="media">
                                 <div class="media-body" style="text-align:left; vertical-align:middle">
                                 <table class="table table-condensed">
                                    <tr height="20px">
                                       <td align="center" width="150px"><h1>등록된 글 이 없습니다</h1></td>
                                    </tr>
                                 </table>
                                 </div>
                                 </div>
                                 
                     <%} %>
                     </tbody>
                  </table>
            <!-- 글작성 끝 -->
                        <!----------------------------- 페이징 ---------------------------->
      <table width=700 BGCOLOR=000000 border=0 cellpadding=0 cellspacing=0 align="center">
      <tr  bgcolor="#7baad6" height=5>
          <td colspan=5 align="center" valign="bottom">
            <img src="<%=imagePath%>j_b04.gif" border="0" align="left" hspace="0" vspace="0">
            <img src="<%=imagePath%>j_b05.gif" border="0" align="right" hspace="0" vspace="0">         
          </td>
      </tr>
      <tr BGCOLOR=FFFFFF>
         <td width=180 height=30 valign=middle align=right>
           <a href="#"><img src="<%=imagePath%>m_bt10.gif" border=0 align=absmiddle></a>
         </td>
         <td width=520  align=center valign=middle height=30>
            <%
            String pagePath = "./getHotelList.kos";
            PageBar pg = new PageBar(pagesize, size, mypage, pagePath);
            out.print(pg.getPageBar());
            %>   
         </td>
         <td align="right">
                  <%if(session.getAttribute("result")!=null){ %>
               <a href="#" style="margin-left:auto"><input type="button" onclick="hotelIDCheck()" class="btn" value="등록"/></a>
                  <%} %>
         </td>
      </tr>
      </table>
            <br>
         </div>
         </div><!-- 메인 끝 -->

   
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
                           <li><a href="https://twitter.com/" class="fa-twitter" target="_blank"><span class="label">Twitter</span></a></li>
                           <li><a href="https://www.facebook.com/" class="fa-facebook" target="_blank"><span class="label">Facebook</span></a></li>
                           <li><a href="https://www.instagram.com/" class="fa-instagram" target="_blank"><span class="label">Instagram</span></a></li>
                           <li><a href="https://www.google.co.kr/" class="fa-google" target="_blank"><span class="label">Google</span></a></li>
                        </ul>
                        <p class="copyright">&copy; Copyright.  <a href="http://www.ikosmo.co.kr/" target="_blank">KOSMO</a>.</p>
                     </div>
                  <!-- 푸터라인 끝 -->

      </div><!-- 사이드끝 -->

</div><!-- wrapper 끝 -->
</body>
</html>