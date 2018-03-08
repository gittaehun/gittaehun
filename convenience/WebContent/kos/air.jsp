<%@ page language="java" contentType="text/html; charset=EUC-KR"
   pageEncoding="EUC-KR"%>
<%@ page import = "java.util.List, java.util.ArrayList, java.util.Map, java.util.HashMap, kos.util.*" %>
<%
int size = 0;
   List<Map<String,Object>> airList = new ArrayList<Map<String,Object>>();
   if(request.getAttribute("airList") != null){
      airList = (List<Map<String,Object>>) request.getAttribute("airList");
      size = airList.size();
   }
   Map<String,Object> mem_info= new HashMap<String,Object>();
   if(session.getAttribute("result")!=null){
      mem_info = (Map<String,Object>)session.getAttribute("result");
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
<title>언타이틀</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="../css/AirCss/bootstrap.min.css" />
<link rel="stylesheet" href="../css/AirCss/main.css" />
<link rel="stylesheet" href="../css/AirCss/air.css" />
<link rel="stylesheet" href="../css/css/font-awesome.css" />
<link rel="stylesheet" href="../css/css/font-awesome.min.css" />

<!-- Scripts -->
<script src="../css/AirCss/js/jquery.min.js"></script>
<script type="text/javascript" src="../css/AirCss/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../css/AirCss/js/bootstrap.js"></script>
<script src="../css/AirCss/js/skel.min.js"></script>
<script src="../css/AirCss/js/util.js"></script>
<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
<script src="../css/AirCss/js/main.js"></script>
<script src="../css/AirCss/js/air.js"></script>
<script type="text/javascript">
   function airView(val){
      var air_no = val;      
       // alert("air_no +"+val);
      location.href="./getAirList.kos?air_no="+val
   }
   
   function airIns(){
      location.href="./airWrite.jsp"
   }
   function alertLogin(){
      $("#login_alert").modal();
   }
</script>
</head>
<body>
<!-- alert창 -->
   <div class="modal" id="login_alert" tabindex="-1" role="dialog" style="padding:10% 40%">
      <div class="modal-dialog modal-sm">
         <div class="modal-content">
            <div class="modal-body" align="center">
               <h1>로그인이 필요합니다.</h1>&nbsp;
               <button type="button" class="btn" data-dismiss="modal" onClick="./getAirList.kos">확인</button>
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
         <!-- 메뉴바끝 -->



      </div>

            <!-- 메인화면시작 -->
      <div id="main">

         <!-- 장바구니 메인 -->
         <div id="i_hotel" class="post">
            <header>
               <div class="title">
                  <h2>
                     <a href="#">AIR</a>
                  </h2>
                  <p>싼 가격에 떠나는 여행</p>
                  <p>Have a good shopping.</p>
               </div>
               <img src="../images/main1.jpg" alt="" style="width:50%; box-shadow:10px 10px 20px 5px grey"/>
            </header>
             
            <!-- 글작성 -->
            <table class="table table-striped" style="max-width:100%; margin:auto">
               <tbody>
                  <form id="f_air">
                        <thead>
                        <tr>
                           <th style="width: 25%; text-align: center"><label
                              style="margin: auto">사진</label></th>
                           <th style="width: 20%; text-align: center"><label
                              style="margin: auto">제목</label></th>
                           <th style="width: 10%; text-align: center"><label
                              style="margin: auto">가격</label></th>
                           <th style="width: 15%; text-align: center"><label
                              style="margin: auto">작성자</label></th>
                           <th style="width: 15%; text-align: center"><label
                              style="margin: auto">작성일</label></th>
                           <th style="width: 10%; text-align: center"><label
                              style="margin: auto">조회수</label></th>
                        </tr>
                      </thead>
                        <!-- 게시물 -->
                        <!-- <tr>
                           <td>
                              <div class="media">
                                 <a class="thumbnail pull-center" href="#" style="margin:0 auto; border:0"> <img
                                    class="media-object"
                                    src="http://icons.iconarchive.com/icons/custom-icon-design/flatastic-2/72/product-icon.png"
                                    style="width: 150px; height: 150px; ">
                                 </a>
                              </div>
                           </td>
                           <td>
                              <div class="media">
                                 <div class="media-body" style="text-align:center; vertical-align:middle">
                                    <br><br><br>
                                    <a href="buy.jsp" style="text-decoration:none">상품 설명 및 제목</a>
                                 </div>
                              </div>
                           </td>
                           <td style="width:10%; text-align:center; padding-top:75px"><label>69,000원</label></td>
                           <td style="width:10% ; text-align:center; padding-top:75px"><label>작성자</label></td>
                           <td style="width:15%; text-align:center; padding-top:75px"><label>2017-06-08</label></td>
                           <td style="width:5%; text-align:center; padding-top:75px"><label>10</label></td>
                        </tr> -->
                        
                        <!-- 반복 할 tr 시작 -->
                        <% 
                           if(airList !=null && airList.size()>0){ 
                             for(int i=0; i<airList.size(); i++){
                        %>
<%-- src="D:/dev_kosmo/FinalProject/WebContent/pds/<%=airList.get(i).get("IMG1")%>" --%>
<%-- src="../pds/<%=airList.get(i).get("IMG1")%>" --%>
                         <tr>
                           <td>
                              <div class="media">
                                 <a class="thumbnail pull-center" href="#" style="margin:0 auto; border:0"> <img
                                    class="media-object"
                                    src="../pds/<%=airList.get(i).get("IMG1")%>"
                                    style="width: 150px; height: 150px;"/>
                                 </a>
                              </div>
                           </td>
                              <td>
                              <font size="3"><br><br><br></font> <!--글씨 공백을 위한 br 폰트 -->
                              <a href="#" style="text-decoration:none" onClick="airView(<%=airList.get(i).get("AIR_NO")%>)">
                                 <b><%=airList.get(i).get("AIR_PNAME")%>
                                 </b>
                              </a>
                           </td>
                           <td style="width:10%; text-align:center; padding-top:75px"><label><%=airList.get(i).get("AIR_PRICE") %></label></td>
                           <td style="width:10% ; text-align:center; padding-top:75px"><label><%=airList.get(i).get("USER_ID") %></label></td>
                           <td style="width:15%; text-align:center; padding-top:75px"><label><%=airList.get(i).get("AIR_WRITEDATE") %></label></td>
                           <td style="width:5%; text-align:center; padding-top:75px"><label><%=airList.get(i).get("AIR_HIT") %></label></td>
                        </tr>
                        <% 
                           }
                        } else{ %>
                        <!-- 반복 할 tr 끝 -->
                         <tr>
                           <td colspan="6">
                              <h1><label align="center">등록된 게시물이 없습니다.</label></h1>   
                           </td>
                        </tr>
                        <%} %>
                        
                        <!-- 게시물끝 -->
                        </form>
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
               <% if(session.getAttribute("result") != null){ %>
               <a href="javascript:airIns()" style="margin-left:auto"><input type="button" class="btn" value="등록"/></a>
               <% } else{%>
               <a href="javascript:alertLogin()" style="margin-left:auto"><input type="button" class="btn" value="등록"/></a>
               <%}%>
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