<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.List, java.util.Map" %>
<%
   String result = request.getParameter("result");
   String check = request.getParameter("check");
   String login = request.getParameter("login");
   String command = request.getParameter("command");
   
   
   String in_nick    = "";
   String in_name    = "";
   String in_mobile  = "";
   String in_address = "";
   String in_gender  = "";
   String in_id="";
   
   Cookie input_info[] = request.getCookies();
   if(input_info != null){
	   for(int i=0; i<input_info.length; i++){
	   	  if("in_nick".equals(input_info[i].getName())){
	   		  in_nick = input_info[i].getValue();
	   	  }
	   	  else if("in_id".equals(input_info[i].getName())){
	   		  in_id = input_info[i].getValue();
	   	  }
	   	  else if("in_name".equals(input_info[i].getName())){
	   		  in_name = input_info[i].getValue();
	   	  }
	   	  else if("in_mobile".equals(input_info[i].getName())){
	   		  in_mobile = input_info[i].getValue();
	   	  }
	   	  else if("in_address".equals(input_info[i].getName())){
	   		  in_address = input_info[i].getValue();
	   	  }
	   	  else if("in_gender".equals(input_info[i].getName())){
	   		  in_gender = input_info[i].getValue();
	   	  }
	   }
   }
%>
    
<html>
    <head>

    <!-- /.javascript files -->
    <script src="../css/loginCss/js/jquery.js"></script>
    <script src="../css/loginCss/js/bootstrap.min.js"></script>
    <script src="../css/loginCss/js/bootstrap.js"></script>
    <script src="../css/loginCss/js/custom.js"></script>
    <script src="../css/loginCss/js/jquery.sticky.js"></script>
    <script src="../css/loginCss/js/wow.min.js"></script>
    <script src="../css/loginCss/js/owl.carousel.min.js"></script>
    
     <!-- 회원가입 CSS -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="../css/loginCss/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="../css/loginCss/css/main.css">
    <link rel="stylesheet" href="../css/loginCss/css/font-awesome.min.css">
    <link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>

    <!-- 타이틀 -->
    <title>Final Project</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!-- CSS 엑세스 -->
    <link href="../css/loginCss/css/mycss.css" rel="stylesheet">
    <link href="../css/loginCss/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="../css/loginCss/css/font-awesome.min.css" rel="stylesheet">
    <link href="../css/loginCss/fonts/icon-7-stroke/css/pe-icon-7-stroke.css" rel="stylesheet">
    <link href="../css/loginCss/css/animate.css" rel="stylesheet" media="screen">
    <link href="../css/loginCss/css/owl.theme.css" rel="stylesheet">
    <link href="../css/loginCss/css/owl.carousel.css" rel="stylesheet">
    <!-- 컬러 -->
    <link href="../css/loginCss/css/css-index.css" rel="stylesheet" media="screen">

	<!-- 구글 폰트 -->
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Lato:100,300,400,700,900,100italic,300italic,400italic,700italic,900italic" />

	    <script type="text/javascript">
	    // alert창 메시지
	    function openAlert(){
	    	$("#dlg_sign").modal('hide');
	    	$("#l_alert").modal('show');
	    }
	    function openAlert2(){
	    	$("#l_alert2").modal('show');
	    }
	    
	    // 비밀번호 체크
        function passwordCheck() {
         var pw1 = $('#pass').val();
         var pw2 = $('#pass1').val();
         
         if(pw1 != pw2) {
            $('#pwcheckmessage').html('비밀번호가 일치하지 않습니다');
         } else {
            $('#pwcheckmessage').html('');
         }
      	}  
	    
      function login(){
         $("#f_header").attr("method","post");
         $("#f_header").attr("action","./login.kos");
         $("#f_header").submit();
      }
      function join(){
		 var check='<%=check%>';
		 
		 var j_id = $('#id').val();
		 var j_pass1 = $('#pass').val();
		 var j_pass2 = $('#pass1').val();
		 var j_nick = $('#nick').val();
		 var j_name = $('#name').val();
		 var j_mobile = $('#mobile').val();
		 var j_address = $('#address').val();
		 
		 if(check == 'null' || check=='NO'){
		 	$("#al_msg").val('중복검사 필요');
			openAlert();
		}
		 
		 if(j_pass1 != j_pass2){
			$("#al_msg").val('비밀번호 불일치');
			openAlert();
			return;
		 }
		 
		 if(j_id=='' || j_pass1=='' || j_pass2=='' || j_nick=='' || j_name =='' || j_mobile =='' || j_address==''){
			$("#al_msg").val('모두 입력해주세요.');
			openAlert();
			return;
		 }
		 
		 if(check=='OK' && j_id!='' && j_pass1!='' && j_pass2!='' && j_nick!=''
				 && j_name !='' && j_mobile !='' && j_address!='')
		 { 
		     $("#f_join").attr("method","post");
	         $("#f_join").attr("action","./reg.kos");
	         $("#f_join").submit();
		 }
		 
		 
      }        
      function loginCheck(){
          $("#dlg_check").modal('show');
          $("#id_check").attr("value",$("#id").val());
      }
      function loginCheck2(){
    	 var i_id = $("#id_check").val();
    	 $("#f_join").attr("method","post");
         $("#f_join").attr("action","./checkMember.kos");
         $("#f_join").submit();    	  
      }
      
      function modalOpen(){
         $("#dlg_sign").modal('show'); 
      }
      
      function inputInfo(){
		  var in_id = '<%=in_id%>';
    	  var in_nick = '<%=in_nick%>';
          var in_name = '<%=in_name%>';
          var in_mobile = '<%=in_mobile%>';
          var in_address = '<%=in_address%>';
          var in_gender = '<%=in_gender%>';
          if(in_id != '' && in_id !=null){
	    		$("#id").attr('value',in_id);
          }           
          if(in_nick != ''){
	    		$("#nick").attr('value',in_nick);
          }           
          if(in_name != ''){
	    		$("#name").attr('value',in_name);
          }           
          if(in_mobile != ''){
	    		$("#mobile").attr('value',in_mobile);
          }           
          if(in_address != ''){
	    		$("#address").attr('value',in_address);
          }           
          if(in_gender == '남자'){
  			$("#gender").val('남자');
          }           
          else if(in_gender == '여자'){
  			$("#gender").val('여자');
          }	  
      }
      function clearInfo(){
	   	 $('#dlg_sign').on('hidden.bs.modal', function (e) {
	   	    console.log('modal close');
	   	    $(this).find('#f_join')[0].reset()
	   	});
      }
      
    </script>
    </head>

    <body data-spy="scroll" data-target="#navbar-scroll">
	
	<!-- alert 창 -->
	<div class="modal" id="l_alert" tabindex="-1" role="dialog" style="padding:10% 40%">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-body" align="center">
				<input type="text" id="al_msg" style="border:0; text-align:center;"><br>
				<button type="button" class="btn" data-dismiss="modal" onClick="modalOpen()">확인</button>
			</div>
		</div>
	</div>
	</div>

	<div class="modal" id="l_alert2" tabindex="-1" role="dialog" style="padding:10% 40%">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-body" align="center">
				<input type="text" id="al_msg2" style="border:0; text-align:center;"><br>
				<button type="button" class="btn" data-dismiss="modal">확인</button>
			</div>
		</div>
	</div>
	</div>
	<!-- alert 창 끝 -->
   <%
   if("open".equals(command)){
	%>
	   <script type="text/javascript"> 
	   		$(document).ready(function(){
			   modalOpen();   
	   		});
	   </script>
   <%
   }
   if(input_info!=null && input_info.length>0){
	%>
	   <script type="text/javascript"> 
	   		$(document).ready(function(){
			   inputInfo();   
	   		});
	   </script>
   <%
   }
   else{
   %>
	   <script type="text/javascript"> 
	   		$(document).ready(function(){
			   clearInfo();   
	   		});
	   </script>
   
   <%	   
   }
   
         if(result!=null && "1".equals(result)){
            	
   %>
         <script type="text/javascript">
			$("#al_msg2").val('회원가입 성공!');
			openAlert2();
			clearInfo();   
         </script>
   <%
   		
         }
   %>
         <%
            if(check!=null){
               if("OK".equals(check)){
         %>
         <script type="text/javascript">
			$("#al_msg").val('사용 가능한 아이디');
			openAlert();
	    	$("#dlg_sign").modal('hide');
        </script>
         <%
               }else if("NO".equals(check)){
         %>
         <script type="text/javascript">
			$("#al_msg").val('중복된 아이디');
			openAlert();
	    	$("#dlg_sign").modal('hide');
		</script>
		           
         <%          
            }
         }
         if(login!=null){
        	 if("fail".equals(login)){
         %>
		<script type="text/javascript">
			$("#al_msg2").val('로그인 실패');
			openAlert2();
		</script>
		<%
        	}
		}
		%>
        <!-- /.preloader -->
       <div id="preloader"></div>
        <div id="top"></div>

        <!-- /.parallax full screen background image -->
        <div class="fullscreen landing parallax" style="background-image:url('../css/loginCss/images/bg.jpg');" data-img-width="2000" data-img-height="1333" data-diff="100">
            <div class="overlay">
                <div class="container">
                    <div class="row">
                        <div class="col-md-7">
                            <!-- /.logo -->
                            <div class="logo wow fadeInDown" style="margin-top:25%"><a href="./main.jsp"><img src="../css/loginCss/images/logos.gif" alt="logo"></a></div>

                            <!-- 메인화면 -->
                            <h1 class="wow fadeInLeft">
                                  <I> <b>Convenience</b> </I>   
                            </h1>

                            <!-- 소개글 -->
                            <div class="landing-text wow fadeInUp">
                                <p>Join us & Enjoy!
                                                             
                                </p>
                                   
                            </div>              

                            <!-- 팀원 -->
                           <!--  <div class="head-btn wow fadeInLeft">
                                <a href="" class="btn-primary">프로젝트 팀원</a>
                            </div> -->

                        </div> 

                        <!-- 로그인폼 -->
                        <div class="col-md-5" style="margin-top:5%">
                            <div class="signup-header wow fadeInUp">
                                <h3 class="form-title text-center"><B>로그인 & 회원가입</B></h3>
                                <form class="form-header" role="form" method="POST" id="f_header">
                                    <input type="hidden" name="id" value="ID">
                                    <input type="hidden" name="pw" value="PassWord">
                                    <div class="form-group">
                                        <input class="form-control input-lg" name="u_id" id="u_id" type="text" placeholder="ID" required>
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control input-lg" name="u_pw" id="u_pw" type="password" placeholder="PW" required>
                                    </div>
                                    <div class="form-group last">
                                        <input type="button" onClick="login()" class="btn btn-warning btn-block btn-lg" value="로그인">
                                    </div>
                                    <p class="privacy text-center">
                                    <a class="line" data-toggle="modal" data-target="#dlg-sign" href="#dlg_sign" onClick="modalOpen()">회원가입하기</a>
                                    <!-- <a href="pwsearch.jsp">비밀번호찾기</a> -->
                                    </p>
                                </form>
                            </div>            

                        </div>
            </div>   
                    </div>
                </div> 
            </div> 
        
        <!-- 회원가입창 -->
      <div id="dlg_sign" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
      <div class="modal-dialog main-login main-center" role="document">
      <div class="modal-body">
      <div class="row">
      <!-- form 시작 -->
      <form id="f_join" class="form-horizontal" method="post">
         <!-- 아이디 -->
            <label for="name" class="cols-sm-2 control-label">아이디</label>
            <div class="cols-sm-10">
               <div class="input-group">
                  <span class="input-group-addon">
                  <i class="fa fa-user fa" aria-hidden="true"></i></span> 
                  <input style="width:222px" type="text" class="form-control" name="id" id="id" placeholder="아이디" />
                  <input type="button" class="btn active" value="check" onClick="loginCheck()">
               </div>
            </div>

         <!-- 비밀번호 -->
            <label for="email" class="cols-sm-2 control-label">비밀번호</label>
            <div class="cols-sm-10">
               <div class="input-group">
                  <span class="input-group-addon"><i class="fa fa-key fa" aria-hidden="true"></i></span> 
                  <input onkeyup="passwordCheck();" type="password" class="form-control" name="pass" id="pass" minlength="6" placeholder="비밀번호" />
                     
               </div>
            </div>

         <!-- 비밀번호 확인 -->
            <label for="email" class="cols-sm-2 control-label">비밀번호 확인</label>
            <div class="cols-sm-10">
               <div class="input-group">
                  <span class="input-group-addon"><i class="fa fa-key fa"   aria-hidden="true"></i></span> 
                  <input onkeyup="passwordCheck();" type="password" class="form-control" name="pass1" id="pass1" minlength="6" placeholder="비밀번호 확인" />
               </div>
            </div>
            <h5 id="pwcheckmessage" style="color: red;"></h5>
         <!-- 닉네임 -->
            <label for="nick" class="cols-sm-2 control-label">닉네임</label>
            <div class="cols-sm-10">
               <div class="input-group">
                  <span class="input-group-addon"><i class="fa fa-comment fa" aria-hidden="true"></i></span> 
                  <input type="text" class="form-control" name="nick" id="nick" placeholder="닉네임" />
               </div>
            </div>
            
         <!-- 이름 -->
            <label for="name" class="cols-sm-2 control-label">이름</label>
            <div class="cols-sm-10">
               <div class="input-group">
                  <span class="input-group-addon"><i class="fa fa-child fa" aria-hidden="true"></i></span> 
                  <input type="text" class="form-control" name="name" id="name" placeholder="이름" />
               </div>
            </div>

         <!-- 핸드폰 -->
            <label for="mobile" class="cols-sm-2 control-label required">핸드폰</label>
            <div class="cols-sm-10">
               <div class="input-group">
                  <span class="input-group-addon">
                  <i class="fa fa-phone" aria-hidden="true"></i></span> 
                  <input type="text" class="form-control" name="mobile" id="mobile" placeholder="핸드폰 번호" />
               </div>
            </div>
            
         <!-- 주소 -->
            <label for="nationality" class="cols-sm-2 control-label">주소</label>
            <div class="cols-sm-10">
               <div class="input-group">
                  <span class="input-group-addon">
                  <i class="fa fa-globe fa-lg" aria-hidden="true"></i></span> 
                     <input type="text" class="form-control" name="address" id="address"   placeholder="주소" />
               </div>
            </div>

         <!-- 성별 -->
            <label for="country" class="cols-sm-2 control-label">성별</label>
            <div class="cols-sm-10">
               <div class="input-group">
                  <span class="input-group-addon">
                  <i class="fa fa-group fa-lg" aria-hidden="true"></i></span> 
                     <select   class="form-control" name="gender" id="gender">
                     <option>-select-</option>
                     <option>남자</option>
                     <option>여자</option>
                  </select>
               </div>
            </div>
         <div class="form-group ">
            <input type="button" class="btn btn-primary btn-lg btn-block login-button" value="가입" onClick="join()">
            <input type="button" data-dismiss="modal" class="btn btn-default btn-lg btn-block" value="취소" onClick="clearInfo()">
         </div>
      </form>
      </div>
      </div> 
      </div>
      </div> <!-- 맨처음 div -->
      
      <!-- 중복검사 창 -->
      <div id="dlg_check" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
      <div class="modal-dialog main-login main-center" role="document">
      <div class="modal-body">
      <div class="row">
      <!-- form 시작 -->
      <form id="f_check" class="form-horizontal" method="post">
         <!-- 아이디 -->
            <label for="name" class="cols-sm-2 control-label">아이디</label>
            <div class="cols-sm-10">
               <div class="input-group">
                  <span class="input-group-addon">
                  <i class="fa fa-user fa" aria-hidden="true"></i></span> 
                  <input style="width:222px" type="text" class="form-control" name="id_check" id="id_check" placeholder="아이디" />
                  <input type="button" class="btn active" value="check" onClick="loginCheck2()">
               </div>
            </div>
         <div class="form-group ">
            <input type="button" data-dismiss="modal" class="btn btn-default btn-lg btn-block cancle-button" value="취소">
            <h5 id="pwcheckmessage" style="color: red;"></h5>
         </div>
      </form>
      </div>
      </div> 
      </div>
      </div>
      <!-- 중복검사 창 -->
<!-- 
  		<script type="text/javascript">
           	new WOW().init();
		</script>  -->
        
    </body>
</html>