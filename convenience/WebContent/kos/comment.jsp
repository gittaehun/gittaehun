<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List, java.util.Map, java.util.HashMap " %>
<%
List<Map<String,Object>> commentList =(List<Map<String,Object>>) request.getAttribute("commentList");
Map<String,Object> mem_info= new HashMap<String,Object>();
if(session.getAttribute("result")!=null){
   mem_info = (Map<String,Object>)session.getAttribute("result");
}
%>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="../css/ReadCss/bootstrap.min.css" />
<link rel="stylesheet" href="../css/ReadCss/main.css" />
<link rel="stylesheet" href="../css/ReadCss/font-awesome.css" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css"
	rel='stylesheet' type='text/css'>
<%-- <jsp:include page="./commonEasyUI.jsp" flush="false"/> --%>
<script src="../css/ReadCss/js/jquery.min.js"></script>
<script type="text/javascript" src="../css/ReadCss/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../css/ReadCss/js/bootstrap.js"></script>
<script src="../css/ReadCss/js/skel.min.js"></script>
<script src="../css/ReadCss/js/util.js"></script>
<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
<script src="../css/ReadCss/js/main.js"></script>
</head>
<body>
<div id="menu">
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

			<!-- Actions -->

		 </div> 
		<div class="col-sm-10">
		<%if(commentList!=null){
			for(int i=0; i<commentList.size();i++){
				Map<String, Object> comment = new HashMap<String, Object>();
				comment = commentList.get(i);
		%>		
			<div class="panel panel-default">
				<div class="panel-heading">
					<strong><i class="fa fa-user"><%=comment.get("USER_ID") %></i></strong> 
					<span class=" pull-right"><i class="fa fa-clock-o"><%=comment.get("BOARD_DATE") %></i></span>
				</div>
				<div class="panel-body"><%=comment.get("BOARD_CONTENTS") %></div>
			<div id="btn_comment" align="right">
			<%if(mem_info.size()>0){
			if(mem_info.get("USER_ID").equals(comment.get("USER_ID").toString())){ %>
				<!-- <input type="button" value="수정" onClick="commentUpdate()"> -->
				<input type="button" value="삭제" onclick="commentDelete(<%=comment.get("BOARD_NO") %>)">
			<%}
			} %>
			</div>
			</div> 
		<%
			}	
		} 
		
		%>			
		</div>
</body>
</html>