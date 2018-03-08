<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import ="java.util.Map, java.util.HashMap, java.util.List, java.util.ArrayList" %>
<%
	List<Map<String,Object>> rentList = new ArrayList<Map<String,Object>>();
	Map<String,Object> rentInfo = new HashMap<String,Object>();
	if(request.getAttribute("rentList")!=null && request.getAttribute("rentInfo")!=null){
		rentList = (List<Map<String,Object>>)request.getAttribute("rentList");	
		rentInfo = (Map<String,Object>)request.getAttribute("rentInfo");	
	}
%>
<html>
<head>
<title>CAR RENTAL 요금 계산 및 예약</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="../css/BuyCss/bootstrap.css" />
<link rel="stylesheet" href="../css/BuyCss/bootstrap.min.css" />
<link rel="stylesheet" href="../css/BuyCss/main.css" />
<link rel="stylesheet" href="../css/BuyCss/buy.css" />
<link rel="stylesheet" href="../css/BuyCss/font-awesome/css/font-awesome.css" />
<link rel="stylesheet" href="../css/BuyCss/font-awesome/css/font-awesome.min.css" />

<!-- Scripts -->
<script src="../css/BuyCss/js/jquery.min.js"></script>
<script type="text/javascript" src="../css/BuyCss/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../css/BuyCss/js/bootstrap.js"></script>
<script src="../css/BuyCss/js/skel.min.js"></script>
<script src="../css/BuyCss/js/util.js"></script>
<script src="../css/BuyCss/js/main.js"></script>
<script src="../css/BuyCss/js/buy.js"></script>
<script src="../css/BuyCss/js/comment.js"></script>

<script type="text/javascript">
	function orderIns(){
		var radioCheck = $("input[name='radio1']:checked").val();
		
		if($("#sel").val() == '[ - 선택 하세요 - ]' 
			|| $("#rname").val() == '' || $("#req").val() == '' 
			|| $("#tel1").val() == '' || $("#tel2").val() ==''){
			$("#al_msg").val('모두 입력해주세요 ');
	    	$("#check_alert").modal('show');
			return ;
		}
		
		
		if(radioCheck == "no") {
			$("#al_msg").val('약관에 동의 해주세요 ');
	    	$("#check_alert").modal('show');
			return;
		}
		$("#f_order").attr("method","post");
		$("#f_order").attr("action","./carOrder.kos");
		$("#f_order").submit();
	}
	function tt(){
		$("#age").attr('value',$("#sel").val());
		//alert($("#age").val());
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
<!-- alert창  -->

<!-- ------------------------------ 전체 메인 단 시작 ------------------------------ -->
<div class="wrapper">
<!-- ---------- 상단 메뉴 바 시작 ---------- -->
<header id="header">
	<h1><a href="./main.jsp">convenience</a></h1>
	<div class="links dropdown">
		<ul>
			<li><a href="./main.jsp">HOME</a></li>
			<li><a href="./getAirList.kos">AIRPLANE</a></li>
			<li><a href="./getCarList.kos">CAR RENTAL</a></li>
			<li><a href="./getHotelList.kos">HOTEL</a></li>
			<li><a href="./getBoardList.kos">BOARD</a></li>
		</ul>
	</div>
		
	<!-- ---------- 상단 메뉴 바 시작 (오른쪽 검색 & 회원정보 & 메뉴) --------- -->
	<div class="main">
		<ul>
			<li class="search"><a class="fa-search" href="#search">검색</a>
				<form id="search" method="get" action="#">
					<input type="text" name="query" placeholder="검색" style="margin-top: 10px" />
				</form></li>
			<li class="menu"><a class="fa-bars" href="#menu">메뉴</a></li>
		</ul>
	</div>
	<!-- ---------- 상단 메뉴 바 끝 (오른쪽 검색 & 회원정보 & 메뉴) --------- -->
		
	<!-- ---------- (오른쪽 검색 & 회원정보 & 메뉴) 시작 ---------- -->
	<div id="menu">
		<!-- ---------- 회원 로그인 시작 ---------- -->
		<div>
			<ul class="actions vertical">
				<li><a href="login.jsp" class="button big fit">Log In</a></li>
			</ul>
		</div>
		<!-- ---------- 회원 로그인 끝 ---------- -->
		<!-- ---------- 메뉴바 시작 ---------- -->
		<div>
			<ul class="links">
				<li>
					<a href="edit.jsp"><h3>회원 정보</h3><p>수정 & 탈퇴</p></a>
				</li>
				<li>
					<a href="#"><h3>배송 현황</h3><p>배송중인 물품 추적</p></a>
				</li>
				<li>
					<a href="cart.jsp"><h3>장바구니</h3><p>찜해놓은 물건</p></a>
				</li>
			</ul>
		</div>
		<!-- ---------- 메뉴바 끝 ---------- -->
	</div>
	<!-- ---------- (오른쪽 검색 & 회원정보 & 메뉴) 끝 ---------- -->
</header>
<!-- ---------- 상단 메뉴 바 끝 ---------- -->

<!-- -------------------- 중단 내용 삽입부 시작 -------------------- -->
<div class="main">
<div class="content-wrapper">
	<div class="post">
		<div class="container-fluid">
		<!-- -------------------- 중단 내용 적용 부 시작 -------------------- -->
		<!-- ---------- 렌탈카 확인 시작 ---------- -->
		<form id="f_order">
		<table>
			<tr>
				<td align="center" style="font-size:x-large;color:blue;">렌탈카 업체</td>
				<td colspan="3"><input type="text" disabled="disabled" value="<%=rentList.get(0).get("RENT_NAME")%>"></td>
			</tr>
			<tr>
				<td align="center" style="font-size:x-large;color:blue;">사업자 번호</td>
				<td colspan="3"><input type="text" disabled="disabled" value="<%=rentList.get(0).get("RENT_LICENSE")%>"></td>
			</tr>
			<tr>
				<td align="center" style="font-size:x-large;color:blue;">렌탈카</td>
				<td colspan="3"><input type="text" name="rent_kind" value="<%=rentInfo.get("rent_kind")%>"></td>
			</tr>
			<tr>
				<td align="center" style="width:17%;font-size:x-large;color:blue;">인수 날짜</td>
				<td style="width:33%"><input type="text" name="start_date" value="<%=rentInfo.get("start_date")%>"></td>
				<td align="center" style="width:17%;font-size:x-large;color:blue;">인수 시간</td>
				<td style="width:33%"><input type="text" name="start_time" value="<%=rentInfo.get("start_time")%>"></td>
			</tr>
			<tr>
				<td align="center" style="width:17%;font-size:x-large;color:blue;">반납 날짜</td>
				<td style="width:33%"><input type="text" name="end_date" value="<%=rentInfo.get("end_date")%>"></td>
				<td align="center" style="width:17%;font-size:x-large;color:blue;">반납 시간</td>
				<td style="width:33%"><input type="text" name="end_time" value="<%=rentInfo.get("end_time")%>"></td>
			</tr>
			<tr>
				<td align="center" style="width:17%;font-size:x-large;color:blue;">금 액</td>
				<td style="width:33%"><input type="text" name="tprice" value="<%=rentInfo.get("price")%>" ></td>
				<td>원</td>
				<td style="width:33%;font-size:x-large;color:red;">(종합 보험 가입 - 자차 포함)</td>
			</tr>
		</table>
		<!-- ---------- 렌탈카 확인 끝 ---------- -->
		<div class="page-header">
				<label class="text-danger" style="font-size:x-large;">※무단 연장(근무 시간에 연장 요청 안함)시 상당 금액의 벌금이 부과 됩니다.</label>
		</div>
		<input type="hidden" id="age" name="age">
		<input type="hidden" name="rent_no" value="<%=rentList.get(0).get("RENT_NO")%>">
		<!-- ---------- 예약자 시작 ---------- -->
		<table>
			<tr>
				<td align="center" style="font-size:x-large;color:blue;">예약자 명</td>
				<td><input type="text" id="rname" name="rname"></td>
				<td align="center" style="font-size:x-large;color:blue;">인수 , 반납</td>
				<td><input type="text" disabled="disabled" value="렌탈카 (사무실)"></td>
			</tr>
			<tr>
				<td align="center" style="font-size:x-large;color:blue;">전화 번호 1</td>
				<td><input type="text" id="tel1" name="tel1" ></td>
				<td align="center" style="font-size:x-large;color:blue;">전화 번호 2</td>
				<td><input type="text" id="tel2" name="tel2" ></td>
			</tr>
			<tr>
				<td align="center" style="font-size:x-large;color:blue;">기타 요청</td>
				<td colspan="3"><input type="text" id="req" name="request" ></td>
			</tr>
			<tr>
				<td align="center" style="font-size:x-large;color:blue;">운전자 나이</td>
				<td>
					<select class="form-control" id="sel" onChange="tt()">
									<option>[ - 선택 하세요 - ]</option>
									<option>[만] 21 ~ 22 세</option>
									<option>[만] 23 ~ 25 세</option>
									<option>[만] 26 세 이상</option>
								</select>
				</td>
				<td colspan="2"></td> <!-- 빈공간 처리용 -->
			</tr>
		</table>
		</form>
		<!-- ---------- 예약자 끝 ---------- -->
		<div class="page-header">
			<label class="text-danger" style="font-size:x-large;">※전연령렌트카는 없음. 만21세미만, 면허취득 1년이하 대여불가능.</label>
			<label class="text-danger" style="font-size:x-large;">※서비스제공을 위해 필요한 최소한의 개인정보를 동의를 해주셔야 이용하실 수 있습니다.</label>
		</div>
		<!-- ---------- 정보수집 동의서 시작 ---------- -->
		<label style="font-size:x-large;">수집하는 개인정보 항목</label>
		<table class="table table-bordered">
			<tr align="center">
				<td style="width:33%;font-size:large;">수집하는 개인정보 항목</td>
				<td style="width:33%;font-size:large;">수집 및 이용목적</td>
				<td style="width:33%;font-size:large;">보유 및 이용기간</td>
			</tr>
			<tr align="center">
				<td style="width:33%;font-size:large;">
					이름, 아이디, 비밀번호,
					<br>
					생년월일, 휴대폰번호
				</td>
				<td style="width:33%;font-size:large;">
					회원가입서비스제공,
					<br>
					계약이행을 위한 연락,
					<br>
					민원 및 고충처리
				</td>
				<td style="width:33%;font-size:large;">
					<br>
					회원탈퇴 후 5일까지
				</td>
			</tr>
		</table>
		<label style="font-size:large;">회사는 회원가입, 상담, 서비스 신청 등등을 위해 아래와 같은 개인정보를 수집하고 있습니다.</label>
		<label style="font-size:x-large;">- 수집 항목</label>
		<label style="font-size:large;">이름, 비밀번호, 전화번호, 이메일, 접속 로그, 접속 IP 정보, 결제기록</label>
		<label style="font-size:x-large;">- 개인정보 수집 방법</label>
		<label style="font-size:large;">홈페이지(회원가입, 상담게시판, 견적), 전화를 통한 회원가입 개인정보의 수집 및 이용목적</label>
		<label style="font-size:large;">회사는 수집한 개인정보를 다음의 목적을 위해 활용합니다.</label>
		<label style="font-size:x-large;">- 서비스 제공에 관한 계약 이행 및 서비스 제공에 따른 요금정산</label>
		<label style="font-size:large;">콘텐츠 제공, 구매 및 요금 결제</label>
		<label style="font-size:x-large;">- 회원 관리</label>
		<label style="font-size:large;">회원제 서비스 이용에 따른 본인확인, 개인 식별, 불량회원의 부정 이용 방지와</label>
		<label style="font-size:large;">비인가 사용 방지, 가입 의사 확인, 불만처리 등 민원처리, 고지사항 전달</label>
		<label style="font-size:x-large;">- 그외 경우는 사용하지 않습니다</label>
		<label style="font-size:large;">개인정보의 보유 및 이용기간</label>
		<label style="font-size:large;">원칙적으로, 개인정보 수집 및 이용목적이 달성된 후에는 해당 정보를 지체 없이 파기합니다.</label>
		<label style="font-size:large;">단, 관계법령의 규정에 따라 보존할 필요가 있는 경우 회사는 아래와 같이 관계법령에서</label>
		<label style="font-size:large;">정한 일정한 기간 동안 회원정보를 보관합니다.</label>
		<label style="font-size:x-large;">- 보존 항목 : 결제기록</label>
		<label style="font-size:x-large;">- 보존 근거 : 전자상거래 등에서의 소비자보호에 관한 법률</label>
		<label style="font-size:x-large;">- 보존 기간 : 5년</label>
		<label style="font-size:large;">계약 또는 청약철회 등에 관한 기록 : 5년 (전자상거래 등에서의 소비자보호에 관한 법률)</label>
		<label style="font-size:large;">대금결제 및 재화 등의 공급에 관한 기록 : 5년 (전자상거래 등에서의 소비자보호에 관한 법률)</label>
		<label style="font-size:x-large;color:red;">★개인정보의 수집 및 이용에 대한 사항에 동의하십니까?</label>
		<div class="checks small">
			<input type="radio" id="radio1" name="radio1" value="ok">
			<label for="radio1">동의함</label>
			<input type="radio" id="radio2" name="radio1" value="no" checked="checked">
			<label for="radio2">동의하지 않음</label>
		<!-- ---------- 정보수집 동의서 끝 ---------- -->
		<!-- ---------- 예약 문구 & 버튼 처리 시작 ---------- -->
		<label class="text-danger" style="font-size:x-large;">※실시간 사이트가 아니므로 < 예약 > 접수 후 가능여부 안내드립니다. (정보제공 전화번호)</label>
		<p align="center">
			<a href="javascript:orderIns()"><button class="btn btn-large btn-default" type="button" style="width:45%;font-size:large;">예약 확인</button></a>
			<a href="./getCarList.kos"><button class="btn btn-large btn-default" type="button" style="width:45%;font-size:large;">취 소</button></a>
		</p>
		<!-- ---------- 예약 문구 & 버튼 처리 끝 ---------- -->
		<!-- -------------------- 중단 내용 적용 부 끝 -------------------- -->
		</div>
	</div>
</div>
</div>
<!-- -------------------- 중단 내용 삽입부 끝 -------------------- -->

<!-- ---------- 하단 메뉴 시작 ---------- -->
<footer>
	<div id="footer" style="text-align: center">
		<ul class="icons">
			<li>
				<a href="https://twitter.com/" class="fa-twitter" target="_blank"><span class="label">Twitter</span></a>
			</li>
			<li>
				<a href="https://www.facebook.com/" class="fa-facebook" target="_blank"><span class="label">Facebook</span></a>
			</li>
			<li>
				<a href="https://www.instagram.com/" class="fa-instagram" target="_blank"><span class="label">Instagram</span></a>
			</li>
			<li>
				<a href="https://www.google.co.kr/" class="fa-google" target="_blank"><span class="label">Google</span></a>
			</li>
		</ul>
		<p class="copyright">
			&copy; Copyright. <a href="http://www.ikosmo.co.kr/" target="_blank">KOSMO</a>.
		</p>
	</div>
</footer>
<!-- ---------- 하단 메뉴 끝 ---------- -->
</div>
<!-- ------------------------------ 전체 메인 단 끝 ------------------------------ -->
</div>
</body>
</html>