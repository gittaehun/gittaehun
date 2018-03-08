package kos.member;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import kos.controller.Action;
import kos.controller.ActionForward;
import kos.util.KoreanConversion;
import kos.vo.MemberVO;

public class MemberServlet implements Action {
	Logger logger = Logger.getLogger(MemberServlet.class);
	String mode = null;
	public MemberServlet(){
		
	}
	public MemberServlet(String mode){
		this.mode = mode;
	}
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		MemberLogic memLogic = new MemberLogic();
		if("insert".equals(mode)){
			logger.info("insert 호출");
			MemberVO mVO = null;
			mVO = new MemberVO();
			int result =0;
			
			String id 		= req.getParameter("id");
			String pass 	= req.getParameter("pass");
			String nick 	= KoreanConversion.toKor(req.getParameter("nick"));
			String mobile 	= req.getParameter("mobile");
			String name 	= KoreanConversion.toKor(req.getParameter("name"));
			String address 	= KoreanConversion.toKor(req.getParameter("address"));
			String gender 	= KoreanConversion.toKor(req.getParameter("gender"));
			if("남자".equals(gender)){
				gender="남";
			}
			else{
				gender="여";
			}
			
			mVO.setId(id);
			mVO.setPass(pass);
			mVO.setNick(nick);
			mVO.setName(name);
			mVO.setMobile(mobile);
			mVO.setAddress(address);
			mVO.setGender(gender);
			
			result = memLogic.memberInsert(mVO);
	
			if(result==1){//입력성공시
				logger.info("입력성공");
				
				Cookie input_info[] = req.getCookies();
	            if(input_info !=null && input_info.length>0){
	       			for(int i=0;i<input_info.length;i++){
	       				//if(!input_info[i].getName().equals("JSESSIONID")){
		       				Cookie cookie = new Cookie(input_info[i].getName(),null);
		       				cookie.setMaxAge(0);
		       			//	cookie.setPath("/");
		       				res.addCookie(cookie);
		       				logger.info(input_info[i].getName());
		       				logger.info(input_info[i].getValue());
	       				//} 
	       			}
	       		}
				
				forward.setRedirect(true);
				forward.setPath("./login.jsp?result="+result);
			}else{//입력실패시
				
			}
			
			
		}
		else if("check".equals(mode)){
			logger.info("check 호출");
			String result =null;
			String check =null;

			String id = req.getParameter("id");

			//Cookie in_id = new Cookie("in_id",id);
			Cookie in_id    = null;
			Cookie in_nick    = null;
			Cookie in_name    = null;
			Cookie in_mobile  = null;
			Cookie in_address = null;
			Cookie in_gender  = null;

			if(id!=null){
				in_id = new Cookie("in_id",id);
			}
			if(req.getParameter("nick")!=null){
				in_nick = new Cookie("in_nick",KoreanConversion.toKor(req.getParameter("nick")));
			}
			if(req.getParameter("name")!=null){
				in_name = new Cookie("in_name",KoreanConversion.toKor(req.getParameter("name")));
			}
			if(req.getParameter("mobile")!=null){
				in_mobile = new Cookie("in_mobile",KoreanConversion.toKor(req.getParameter("mobile")));
			}
			if(req.getParameter("address")!=null){
				in_address = new Cookie("in_address",KoreanConversion.toKor(req.getParameter("address")));
			}
			if(!KoreanConversion.toKor(req.getParameter("gender")).equals("-select-")){
				logger.info(KoreanConversion.toKor(req.getParameter("gender")));
				in_gender = new Cookie("in_gender",KoreanConversion.toKor(req.getParameter("gender")));
			}

			in_id.setMaxAge(10*60);
			res.addCookie(in_id);
			
			if(in_nick !=null){
				in_nick.setMaxAge(10*60);
				res.addCookie(in_nick);
			}
			if(in_name !=null){
				in_name.setMaxAge(10*60);
				res.addCookie(in_name);
			}
			if(in_mobile !=null){
				in_mobile.setMaxAge(10*60);
				res.addCookie(in_mobile);
			}
			if(in_address !=null){
				in_address.setMaxAge(10*60);
				res.addCookie(in_address);
			}
			if(in_gender !=null){
				in_gender.setMaxAge(10*60);
				res.addCookie(in_gender);
			}
			
			result = memLogic.checkID(id);
			if(result!=null && !"".equals(result)){
				logger.info("중복됨");
				check="NO";
				forward.setRedirect(true);
				forward.setPath("./login.jsp?check="+check);
			}else{
				logger.info("중복안됨");
				check="OK";
				forward.setRedirect(true);
				forward.setPath("./login.jsp?check="+check);
				}
			}
		else if("login".equals(mode)){
			logger.info("login 호출");
			
			HttpSession session = req.getSession();
			
			Map<String,Object> result = null;
			Map<String,String> info = new HashMap<String,String>();
			String id = req.getParameter("u_id");
			String pw = req.getParameter("u_pw");
			
			logger.info("id : "+ id +", pw : "+pw);
			
			if(id != null && pw != null){
				info.put("mem_id", id);
				info.put("mem_pw", pw);
			}
			result = memLogic.login(info);

			if(result != null){
				logger.info("로그인 성공");
				session.setAttribute("result", result);
				forward.setPath("./main.jsp");
				forward.setRedirect(false);
			}else{
				logger.info("로그인 실패");
				forward.setPath("./login.jsp?login=fail");
				forward.setRedirect(true);
			}
		}
		else if("logout".equals(mode)){
			logger.info("logout 호출");
			HttpSession session = req.getSession();
			session.invalidate();
			forward.setPath("./main.jsp");
			forward.setRedirect(true);
		}
		else if("memUpdate".equals(mode)){
			logger.info("memUpdate 호출");
			int result = 0;
			String gender= null;
			HttpSession session = req.getSession();

			Map<String,Object> mem_info= new HashMap<String,Object>();
		
			logger.info(req.getParameter("n_id"));
			logger.info(KoreanConversion.toKor(req.getParameter("n_name")));
			logger.info(req.getParameter("n_pw"));
			logger.info(KoreanConversion.toKor(req.getParameter("n_nickname")));
			logger.info(req.getParameter("n_hp"));
			logger.info(req.getParameter("n_gender"));
			logger.info(KoreanConversion.toKor(req.getParameter("n_address")));
			
			mem_info.put("n_id", req.getParameter("n_id"));
			mem_info.put("n_name", KoreanConversion.toKor(req.getParameter("n_name")));
			mem_info.put("n_pw", req.getParameter("n_pw"));
			mem_info.put("n_nick", KoreanConversion.toKor(req.getParameter("n_nickname")));
			mem_info.put("n_hp", req.getParameter("n_hp"));
			mem_info.put("n_address", KoreanConversion.toKor(req.getParameter("n_address")));
		
			if(Integer.parseInt(req.getParameter("n_gender"))==2){
				gender="남";
			}
			else{
				gender="여";
			}
			mem_info.put("n_gender", gender);
			
			result = memLogic.memUpdate(mem_info);
			if(result==1){
				logger.info("수정 성공");
				forward.setPath("./logout.kos");
				forward.setRedirect(true);
			}else if(result ==0){
				logger.info("수정 실패");
			}
		}
		return forward;
	}
	
	
}